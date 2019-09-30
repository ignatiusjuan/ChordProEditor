package de.chordsystem.chordproeditor.view.javafx.controllers;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import de.chordsystem.chordproeditor.model.interfaces.Song;
import de.chordsystem.chordproeditor.parser.ChordProParser;
import de.chordsystem.chordproeditor.userdata.UserData;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controller for ChordProEditController
 * @author IgnatiusJuanPradipta
 * @author engin
 */
public class EditController implements Initializable {
	
	@FXML
	private JFXHamburger jfxHamHide;
	
	@FXML
	private Label lblInsert;
	
	@FXML
	private Label lblAttributes;
	
	@FXML
	private Label lblContents;
        
	@FXML
	private ScrollPane scrollpaneAttributes;

	@FXML
	private ScrollPane scrollpaneContents;
	
    @FXML
    private Button btnInsertTitle;
    
    @FXML
    private Button btnInsertSubtitle;
    
    @FXML
    private Button btnInsertArtist;
    
    @FXML
    private Button btnInsertComposer;
    
    @FXML
    private Button btnInsertLyricist;
    
    @FXML
    private Button btnInsertCopyright;
    
    @FXML
    private Button btnInsertAlbum;
    
    @FXML
    private Button btnInsertYear;
    
    @FXML
    private Button btnInsertKey;
    
    @FXML
    private Button btnInsertTime;
    
    @FXML
    private Button btnInsertTempo;
    
    @FXML
    private Button btnInsertDuration;
    
    @FXML
    private Button btnInsertCapo;

    @FXML
    private Button btnInsertNewSong;
    
    @FXML
    private Button btnInsertTextFont;
    
    @FXML
    private Button btnInsertTextSize;
    
    @FXML
    private Button btnInsertTextColour;
    
    @FXML
    private Button btnInsertChordColour;
    
    @FXML
    private Button btnInsertVerse;

    @FXML
    private Button btnInsertChorus;

    @FXML
    private Button btnInsertGrid;

    @FXML
    private Button btnInsertTab;

    @FXML
    private Button btnInsertComment;

    @FXML
    private Button btnInsertChordDiagram;

    @FXML
    private JFXButton btnFontPlus;

    @FXML
    private JFXButton btnFontMinus;
        
    @FXML
    private JFXButton lblFont;
    
    @FXML
    private JFXTextArea txtAreaEditSong;
    
    @FXML
    private JFXTextArea txtAreaErrorMessage;

    @FXML
    private ImageView ivRedo;
    
    @FXML
    private ImageView ivUndo;

    @FXML
    private ImageView ivRedoGrey;
    
    @FXML
    private ImageView ivUndoGrey;
    
    @FXML
    private ImageView editAsWYSIWYG;
    
    @FXML
    private JFXButton lblEditInWYSIWYG;

    @FXML
    private JFXButton btnSave;
    
    @FXML
    private Label lblLine;
    
    @FXML
    private Label lblLineNumber;
    
    StringProperty txtAreaEditSongProperty = new SimpleStringProperty();
    StringProperty txtAreaErrorMessageProperty = new SimpleStringProperty();
    BooleanProperty hideSidePane = new SimpleBooleanProperty(false);
    
    private String originalText;
    private NewEditorController newEditorController;
    
    //Template Attribute
    private final String templateTitle 			= "{title: insert_title_here}\n";
    private final String templateSubtitle		= "{subtitle: insert_subtitle_here}\n";
    private final String templateArtist 			= "{artist: insert_artist_here}\n";
    private final String templateComposer 		= "{composer: insert_composer_here}\n";
    private final String templateLyricist 		= "{lyricist: insert_lyricist_here}\n";
    private final String templateCopyright 		= "{copyright: insert_copyright_here}\n";
    private final String templateAlbum 			= "{album: insert_album_here}\n";
    private final String templateYear 			= "{year: insert_year_here}\n";
    private final String templateKey 			= "{key: insert_key_here}\n";
    private final String templateTime 			= "{time: insert_time_here}\n";
    private final String templateTempo 			= "{tempo: insert_tempo_here}\n";
    private final String templateDuration 		= "{duration: mm:ss}\n";
    private final String templateCapo 			= "{capo: insert_capo_here}\n";
    private final String templateTextFont 		= "{textfont: insert_textfont_here}\n";
    private final String templateTextSize 		= "{textsize: insert_textsize_here}\n";
    private final String templateTextColour 	= "{textcolour: insert_textcolour_here}\n";
    private final String templateChordColour 	= "{chordcolour: insert_chordcolour_here}\n";
    private final String templateNewSong		= "{new_song}\n";
    
    //Template Contents
    private final String templateVerse 			= "{start_of_verse: verse_name}\n" 	+ 
    											  "chord_and_lyric_\n"				+
    											  "{end_of_verse}\n";
    private final String templateChorus 		= "{start_of_chorus: chorus_name}\n"+ 
			  									  "chord_and_lyric\n"				+
			  									  "{end_of_chorus}\n";
    private final String templateTab			= "{start_of_tab: tab_name}\n"		+ 
    											  "tab_contents\n"					+
    											  "{end_of_chorus}\n";
    private final String templateGrid 			= "{start_of_grid: grid_name}\n"	+ 
			  									  "grid_contents\n"					+
			  									  "{end_of_grid}\n";
    private final String templateComment		= "{comment: comment}";
    private final String templateChordDiagram	= "{chord: chord_name base-fret offset frets x 0 1 2 3 4 fingers x 0 1 2 3 4}";
    
    //Dialog
    private String SAVE_CHORDPRO_FILE_AS				= "Save ChordPro File As";
    private String CHORD_PRO_FILES						= "ChordPro Files";
    private String TEXT_FILES							= "Text Files";
    private String ALL_FILES							= "All Files";
    private String PROJECT_SAVED						= "Project is saved!";
    private String FILES_SAVED_SUCCESSFULLY				= "File saved successfully";
    private String ERROR								= "Error";
    private String BTN_YES								= "Yes";
    private String BTN_NO								= "No";
    private String BTN_CANCEL							= "Cancel";
    private String BTN_OK								= "Ok";
    private String QUESTION_QUIT						= "Quit?";
    private String QUESTION_SAVE						= "Save?";
    private String QUESTION_PROJECT_MODIFIED			= "Current project is modified";
    private String QUIT_CONFIRMATION					= "Quit regardless?";
    private String WYSIWYG_SWITCH_ERROR					= "Cannot switch to WYSIWYG Mode. Check error message.";
    private String ERROR_PARSING_LINE					= "Error on parsing line ";
    
    /**
     * Bind function for left side pane
     */
    private void setLeftSidePaneBind() {
    	lblInsert.visibleProperty().bind(hideSidePane.not());
    	lblAttributes.visibleProperty().bind(hideSidePane.not());
    	lblContents.visibleProperty().bind(hideSidePane.not());
    	scrollpaneAttributes.visibleProperty().bind(hideSidePane.not());
    	scrollpaneContents.visibleProperty().bind(hideSidePane.not());
    	scrollpaneAttributes.disableProperty().bind(hideSidePane);
    	scrollpaneContents.disableProperty().bind(hideSidePane);
    }
    
    /**
     * Function used when Left Hamburger is clicked. The left side pane will be hidden and txtAreaEditSong will be enlarged.
     * @param event
     */
    @FXML
    private void onClickLeftHamburger(MouseEvent event) {
    	if (!hideSidePane.get()) {
    		AnchorPane.setLeftAnchor(txtAreaEditSong, 60.0);
    		AnchorPane.setLeftAnchor(txtAreaErrorMessage, 60.0);
    	} else {
    		AnchorPane.setLeftAnchor(txtAreaEditSong, 190.0);
    		AnchorPane.setLeftAnchor(txtAreaErrorMessage, 190.0);
    	}
    	hideSidePane.set(!hideSidePane.get());
    }
    
    /**
     * Helper function to insert a text to current caret position in TextArea
     */
    private void onClickInsertText(String insertText) {
    	txtAreaEditSong.insertText(txtAreaEditSong.getCaretPosition(), insertText);
    	txtAreaEditSong.requestFocus();
    }
    
    /**
     * Receive the syntax of a song or a file and show it on TextArea
     * @param message
     */
    public void receiveEditText(String message) {
    	String[] lines = message.split("\n");
    	StringBuilder sb = new StringBuilder();
    	if (!ChordProParser.getErrorLines().isEmpty())
    		for (int i : ChordProParser.getErrorLines())
    			sb.append(ERROR_PARSING_LINE + (i+1) + ": " + lines[i] + "\n");
    	txtAreaErrorMessageProperty.set(sb.toString());
    	txtAreaEditSongProperty.set(message);
    	originalText = message;
    }
    
    /**
     * Function is called when user press the save button
     * @param event
     */
    private boolean onClickSaveBtn() {
    	if (newEditorController.filename.isBlank()) {
    		FileChooser fileChooser = new FileChooser();
        	fileChooser.setTitle(SAVE_CHORDPRO_FILE_AS);
        	fileChooser.getExtensionFilters().addAll(
        			new FileChooser.ExtensionFilter(CHORD_PRO_FILES, "*.chopro", "*.crd", "*.cho", "*.chord", "*.pro"),
        			new FileChooser.ExtensionFilter(TEXT_FILES, "*.txt"),
        			new FileChooser.ExtensionFilter(ALL_FILES, "*.*")
        	);
        	
        	File selectedFile = fileChooser.showSaveDialog(null);
        	if (selectedFile != null) {
        		newEditorController.filename = selectedFile.getAbsolutePath();
        	} else {
        		return false;
        	}
    	}
    	if (!newEditorController.filename.isBlank())
    		try {
        		PrintWriter out = new PrintWriter(newEditorController.filename);
        		out.println(txtAreaEditSong.getText());
        		out.close();
        		Alert alert = new Alert(Alert.AlertType.NONE);
    	    	alert.setTitle(PROJECT_SAVED);
    	    	alert.setHeaderText(FILES_SAVED_SUCCESSFULLY);
    	    	((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/Icons/icon 512x512.png/"));
    	    	ButtonType okButton = new ButtonType(BTN_OK, ButtonData.OK_DONE);
    	    	alert.getButtonTypes().setAll(okButton);
        		alert.showAndWait();
        		originalText = txtAreaEditSong.getText();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
    	return true;
    }
    
    /**
     * Function is called when user try to switch edit mode to WYSIWYG
     * @param event
     */
    @FXML
    private void onSwitchToWYSIWYG(MouseEvent event) {
    	ChordProParser cpp = new ChordProParser();
		Song tempSong = cpp.tryParseChordProString(txtAreaEditSong.textProperty().getValue());
		if (ChordProParser.getErrorLines().isEmpty()) {
			newEditorController.updateSong(tempSong);
			btnSave.getScene().getWindow().hide();
		} else {
			announceError(ChordProParser.getErrorLines());
			Alert alert = new Alert(Alert.AlertType.ERROR);
	    	alert.setTitle(ERROR);
	    	alert.setHeaderText(WYSIWYG_SWITCH_ERROR);
	    	((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/Icons/icon 512x512.png/"));
	    	ButtonType okButton = new ButtonType(BTN_OK, ButtonData.OK_DONE);
	    	alert.getButtonTypes().setAll(okButton);
    		alert.showAndWait();
		}
    }
    
    /**
     * Functions to be initialised during first window load
     */
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	editAsWYSIWYG.setOnMouseClicked(this::onSwitchToWYSIWYG);
    	btnSave.setOnAction((e) -> onClickSaveBtn());
    	txtAreaEditSong.setFont(Font.font("monospaced",FontWeight.NORMAL,14));
    	txtAreaEditSong.textProperty().bindBidirectional(txtAreaEditSongProperty);
    	txtAreaEditSong.caretPositionProperty().addListener(new ChangeListener<Object>() {
    		@Override
    		public void changed(ObservableValue<?> observable, Object oldValue, Object newValue) {
    			String text = txtAreaEditSong.getText(0,txtAreaEditSong.getCaretPosition());
    			int lineCounter = 1;
    			for (int i = 0; i < text.length(); i++) {
    				if (text.charAt(i) == '\n')
    					lineCounter++;
    			}
    			lblLineNumber.setText(String.valueOf(lineCounter));
    		}
    	});
    	txtAreaErrorMessage.textProperty().bindBidirectional(txtAreaErrorMessageProperty);
    	
    	btnInsertTitle.setOnAction((e) -> onClickInsertText(templateTitle));
    	btnInsertSubtitle.setOnAction((e) -> onClickInsertText(templateSubtitle));
    	btnInsertArtist.setOnAction((e) -> onClickInsertText(templateArtist));
    	btnInsertComposer.setOnAction((e) -> onClickInsertText(templateComposer));
    	btnInsertLyricist.setOnAction((e) -> onClickInsertText(templateLyricist));
    	btnInsertCopyright.setOnAction((e) -> onClickInsertText(templateCopyright));
    	btnInsertAlbum.setOnAction((e) -> onClickInsertText(templateAlbum));
    	btnInsertYear.setOnAction((e) -> onClickInsertText(templateYear));
    	btnInsertKey.setOnAction((e) -> onClickInsertText(templateKey));
    	btnInsertTime.setOnAction((e) -> onClickInsertText(templateTime));
    	btnInsertTempo.setOnAction((e) -> onClickInsertText(templateTempo));
    	btnInsertDuration.setOnAction((e) -> onClickInsertText(templateDuration));
    	btnInsertCapo.setOnAction((e) -> onClickInsertText(templateCapo));
    	btnInsertTextFont.setOnAction((e) -> onClickInsertText(templateTextFont));
    	btnInsertTextSize.setOnAction((e) -> onClickInsertText(templateTextSize));
    	btnInsertTextColour.setOnAction((e) -> onClickInsertText(templateTextColour));
    	btnInsertChordColour.setOnAction((e) -> onClickInsertText(templateChordColour));
    	btnInsertNewSong.setOnAction((e) -> onClickInsertText(templateNewSong));
    	
    	btnInsertVerse.setOnAction((e) -> onClickInsertText(templateVerse));
    	btnInsertChorus.setOnAction((e) -> onClickInsertText(templateChorus));
    	btnInsertTab.setOnAction((e) -> onClickInsertText(templateTab));
    	btnInsertGrid.setOnAction((e) -> onClickInsertText(templateGrid));
    	btnInsertComment.setOnAction((e) -> onClickInsertText(templateComment));
    	btnInsertChordDiagram.setOnAction((e) -> onClickInsertText(templateChordDiagram));
    	
    	ivRedo.setOnMouseClicked((e) -> {txtAreaEditSong.redo(); txtAreaEditSong.requestFocus();});
    	ivRedo.visibleProperty().bind(txtAreaEditSong.redoableProperty());
    	ivRedo.disableProperty().bind(txtAreaEditSong.redoableProperty().not());
    	ivUndo.setOnMouseClicked((e) -> {txtAreaEditSong.undo(); txtAreaEditSong.requestFocus();});
    	ivUndo.visibleProperty().bind(txtAreaEditSong.undoableProperty());
    	ivUndo.disableProperty().bind(txtAreaEditSong.undoableProperty().not());
    	
    	setLeftSidePaneBind();
    	
    	HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(jfxHamHide);
		transition.setRate(-1);
		jfxHamHide.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
				transition.setRate(transition.getRate()*-1);
		transition.play();
		});
		jfxHamHide.setOnMouseClicked(this::onClickLeftHamburger);
		
		btnFontPlus.setOnMouseClicked((e) -> {
			txtAreaEditSong.setFont(Font.font("monospaced",FontWeight.NORMAL,Math.min(txtAreaEditSong.getFont().getSize() + 2.0, 100.0)));
			txtAreaErrorMessage.setFont(Font.font(txtAreaErrorMessage.getFont().getName(),FontWeight.NORMAL,Math.min(txtAreaErrorMessage.getFont().getSize() + 2.0, 100.0)));
		});
		
		btnFontMinus.setOnMouseClicked((e) -> {
			txtAreaEditSong.setFont(Font.font("monospaced",FontWeight.NORMAL,Math.max(txtAreaEditSong.getFont().getSize() - 2.0, 4.0)));
			txtAreaErrorMessage.setFont(Font.font(txtAreaErrorMessage.getFont().getName(),FontWeight.NORMAL,Math.max(txtAreaErrorMessage.getFont().getSize() - 2.0, 4.0)));
		});
		
		setLanguage(UserData.getLocale());
    }
    
    /**
     * Quit confirmation will be used every time a user try to close EditWindow with edited TextArea.
     */
    private void quitConfirmation() {
    	Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    	alert.setTitle(QUESTION_QUIT);
    	alert.setContentText(QUIT_CONFIRMATION);
    	((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/Icons/icon 512x512.png/"));
    	ButtonType yesButton = new ButtonType(BTN_YES, ButtonData.YES);
    	ButtonType noButton = new ButtonType(BTN_NO, ButtonData.NO);
    	alert.getButtonTypes().setAll(yesButton, noButton);
    	alert.showAndWait().ifPresent(type -> {
    		if (type == yesButton) {
    	        btnSave.getScene().getWindow().hide();
    	        newEditorController.forceNewDocument();
    	    }
    	});
    }
    
    /**
     * Function used when window is requested to be closed
     */
    public void closeWindowEvent(WindowEvent event) {
    	//Check if text same with original
    	if (originalText.equals(txtAreaEditSong.getText())){
    		onSwitchToWYSIWYG(new MouseEvent(null, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
    		if (!ChordProParser.getErrorLines().isEmpty()) {
    			announceError(ChordProParser.getErrorLines());
    			quitConfirmation();
    		}
    	} else {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    	alert.setTitle(QUESTION_PROJECT_MODIFIED);
	    	alert.setContentText(QUESTION_SAVE);
	    	((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("/Icons/icon 512x512.png/"));
	    	ButtonType yesButton = new ButtonType(BTN_YES, ButtonData.YES);
	    	ButtonType noButton = new ButtonType(BTN_NO, ButtonData.NO);
	    	ButtonType cancelButton = new ButtonType(BTN_CANCEL, ButtonData.CANCEL_CLOSE);
	    	alert.getButtonTypes().setAll(yesButton, noButton, cancelButton);
	    	alert.showAndWait().ifPresent(type -> {
    	        if (type == yesButton) {
    	        	if (onClickSaveBtn()) {
	    	        	onSwitchToWYSIWYG(new MouseEvent(null, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
	    	        	if (!ChordProParser.getErrorLines().isEmpty()) {
	    	        		announceError(ChordProParser.getErrorLines());
	    	    			quitConfirmation();
	    	    		}
    	        	}
    	        } else if (type == noButton) {
    	        	txtAreaEditSong.setText(originalText);
    	        	onSwitchToWYSIWYG(new MouseEvent(null, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null));
    	        	if (!ChordProParser.getErrorLines().isEmpty()) {
    	        		announceError(ChordProParser.getErrorLines());
    	    			quitConfirmation();
    	    		}
    	        }
	    	});
    		
    	}
    }
    
    /**
     * Announce error message content with line number and line content
     */
    private void announceError(List<Integer> lineNumber) {
    	String[] lines = txtAreaEditSong.getText().split("\n");
    	txtAreaErrorMessage.setText("");
    	for (int i : lineNumber) {
    		txtAreaErrorMessage.appendText(ERROR_PARSING_LINE + (i+1) + ": " + lines[i] + "\n");
    	}
    }
    
    /**
     * Set the owner of EditWindow, since NewEditorController is the main window, it is the parent of all other window
     * @param newEditorController
     */
    public void setNewEditorController(NewEditorController newEditorController) {
    	this.newEditorController = newEditorController;
    }

    /**
     * Set language of the GUI, with language and country as parameteres
     */
    @SuppressWarnings("unused")
	private void setLanguage(String language, String country) {
    	Locale l = new Locale(language,country);
    	setLanguage(l);
    }
    
    /**
     * Set language of the GUI, with Locale as parameter
     */
    private void setLanguage(Locale l) {
    	ResourceBundle r = ResourceBundle.getBundle("Locale/Language",l);
    	
    	btnInsertTitle.setText(r.getString("SYNTAXEDITOR_BUTTON_TITLE"));
    	btnInsertSubtitle.setText(r.getString("SYNTAXEDITOR_BUTTON_SUBTITLE"));
    	btnInsertArtist.setText(r.getString("SYNTAXEDITOR_BUTTON_ARTIST"));
    	btnInsertComposer.setText(r.getString("SYNTAXEDITOR_BUTTON_COMPOSER"));
    	btnInsertLyricist.setText(r.getString("SYNTAXEDITOR_BUTTON_LYRICIST"));
    	btnInsertCopyright.setText(r.getString("SYNTAXEDITOR_BUTTON_COPYRIGHT"));
    	btnInsertAlbum.setText(r.getString("SYNTAXEDITOR_BUTTON_ALBUM"));
    	btnInsertYear.setText(r.getString("SYNTAXEDITOR_BUTTON_YEAR"));
    	btnInsertKey.setText(r.getString("SYNTAXEDITOR_BUTTON_KEY"));
    	btnInsertTime.setText(r.getString("SYNTAXEDITOR_BUTTON_TIME"));
    	btnInsertTempo.setText(r.getString("SYNTAXEDITOR_BUTTON_TEMPO"));
    	btnInsertDuration.setText(r.getString("SYNTAXEDITOR_BUTTON_DURATION"));
    	btnInsertCapo.setText(r.getString("SYNTAXEDITOR_BUTTON_CAPO"));
    	btnInsertTextFont.setText(r.getString("SYNTAXEDITOR_BUTTON_TEXTFONT"));
    	btnInsertTextSize.setText(r.getString("SYNTAXEDITOR_BUTTON_TEXTSIZE"));
    	btnInsertTextColour.setText(r.getString("SYNTAXEDITOR_BUTTON_TEXTCOLOUR"));
    	btnInsertChordColour.setText(r.getString("SYNTAXEDITOR_BUTTON_CHORDCOLOUR"));
    	btnInsertNewSong.setText(r.getString("SYNTAXEDITOR_BUTTON_NEW_SONG"));
    	
    	btnInsertVerse.setText(r.getString("SYNTAXEDITOR_BUTTON_VERSE"));
    	btnInsertChorus.setText(r.getString("SYNTAXEDITOR_BUTTON_CHORUS"));
    	btnInsertGrid.setText(r.getString("SYNTAXEDITOR_BUTTON_GRID"));
    	btnInsertTab.setText(r.getString("SYNTAXEDITOR_BUTTON_TAB"));
    	btnInsertComment.setText(r.getString("SYNTAXEDITOR_BUTTON_COMMENT"));
    	btnInsertChordDiagram.setText(r.getString("SYNTAXEDITOR_BUTTON_CHORD_DIAGRAM"));

    	lblInsert.setText(r.getString("SYNTAXEDITOR_LABEL_INSERT"));
    	lblAttributes.setText(r.getString("SYNTAXEDITOR_LABEL_ATTRIBUTES"));
    	lblContents.setText(r.getString("SYNTAXEDITOR_LABEL_CONTENTS"));
    	lblFont.setText(r.getString("SYNTAXEDITOR_LABEL_FONT"));
    	lblEditInWYSIWYG.setText(r.getString("SYNTAXEDITOR_LABEL_EDIT_IN_WYSIWYG"));
    	lblLine.setText(r.getString("SYNTAXEDITOR_LABEL_LINE"));
    	
    	btnSave.setText(r.getString("SYNTAXEDITOR_BUTTON_SAVE"));
    	
    	txtAreaEditSong.setPromptText(r.getString("SYNTAXEDITOR_TEXTAREA_SONG_EDIT"));
    	txtAreaErrorMessage.setPromptText(r.getString("SYNTAXEDITOR_TEXTAREA_ERROR_MESSAGE"));
    	
    	SAVE_CHORDPRO_FILE_AS				= r.getString("SAVE_CHORDPRO_FILE_AS");
        CHORD_PRO_FILES						= r.getString("CHORD_PRO_FILES");
        TEXT_FILES							= r.getString("TEXT_FILES");
        ALL_FILES							= r.getString("ALL_FILES");
        PROJECT_SAVED						= r.getString("PROJECT_SAVED");
        FILES_SAVED_SUCCESSFULLY			= r.getString("FILES_SAVED_SUCCESSFULLY");
        ERROR								= r.getString("ERROR");
        BTN_YES								= r.getString("BTN_YES");
        BTN_NO								= r.getString("BTN_NO");
        BTN_CANCEL							= r.getString("BTN_CANCEL");
        BTN_OK								= r.getString("BTN_OK");
        QUESTION_QUIT						= r.getString("QUESTION_QUIT");
        QUESTION_SAVE						= r.getString("QUESTION_SAVE");
        QUESTION_PROJECT_MODIFIED			= r.getString("QUESTION_PROJECT_MODIFIED");
        QUIT_CONFIRMATION					= r.getString("QUIT_CONFIRMATION");
        WYSIWYG_SWITCH_ERROR				= r.getString("WYSIWYG_SWITCH_ERROR");
        ERROR_PARSING_LINE					= r.getString("ERROR_PARSING_LINE");
    }
}
