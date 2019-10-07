package de.chordsystem.chordproeditor.view.javafx.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import de.chordsystem.chordproeditor.model.classes.SongImpl;
import de.chordsystem.chordproeditor.model.classes.SongProperties;
import de.chordsystem.chordproeditor.model.interfaces.Song;
import de.chordsystem.chordproeditor.parser.ChordChecker;
import de.chordsystem.chordproeditor.parser.ChordProConverter;
import de.chordsystem.chordproeditor.parser.ChordProParser;
import de.chordsystem.chordproeditor.parser.ChordTransposer;
import de.chordsystem.chordproeditor.userdata.UserData;
import de.chordsystem.chordproeditor.view.javafx.helperclasses.GetStringFromFile;
//import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchScene;
import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;
import de.chordsystem.latex.GeneratePDF;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.robot.Robot;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;

/**
 * Controller for WYSIWYGEditorController
 * @author engin
 * @author IgnatiusJuanPradipta
 */
public class NewEditorController implements Initializable {
	
	private WindowPresetSwitchStage wp = new WindowPresetSwitchStage();
	//private WindowPresetSwitchScene wpss = new WindowPresetSwitchScene();
	
	@FXML
	private Label lblAttributes;
	
	@FXML
	private ScrollPane scrollpaneAttributes;
	
    @FXML
    private MenuBar MenuBar;

    @FXML
    private Menu menuFile;
    
    @FXML
    private MenuItem menuFileNew;
    
    @FXML
    private MenuItem menuFileOpen;

    @FXML
    private MenuItem menuFileSave;
    
    @FXML
    private MenuItem menuFileSaveAs;
    
    @FXML
    private MenuItem menuExportAsPDF;

    @FXML
    private Menu menuFilePreferences;

    @FXML
    private Menu menuFilePreferencesLanguage;
    
    @FXML
    private MenuItem menuFilePreferencesLanguageDeutsch;
    
    @FXML
    private MenuItem menuFilePreferencesLanguageEnglish;
    
    @FXML
    private MenuItem menuFileQuit;
    
    @FXML
    private Menu menuEdit;
    
    @FXML
    private MenuItem menuEditUndo;
    
    @FXML
    private MenuItem menuEditRedo;
        
    @FXML
    private MenuItem menuEditCut;
    
    @FXML
    private MenuItem menuEditCopy;
    
    @FXML
    private MenuItem menuEditPaste;
    
    @FXML
    private MenuItem menuEditSelectAll;
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    private JFXTextField txtSubtitle;
    
    @FXML
    private JFXTextField txtArtist;
    
    @FXML
    private JFXTextField txtComposer;  

    @FXML
    private JFXTextField txtLyricist;  

    @FXML
    private JFXTextField txtCopyright;  
    
    @FXML
    private JFXTextField txtAlbum;  

    @FXML
    private JFXTextField txtYear;  

    @FXML
    private JFXTextField txtKey;  

    @FXML
    private JFXTextField txtTime;  

    @FXML
    private JFXTextField txtTempo;

    @FXML
    private JFXTextField txtDuration;  

    @FXML
    private JFXTextField txtCapo;
    
    @FXML
    private JFXTextField txtTextFont;

    @FXML
    private JFXTextField txtTextSize;

    @FXML
    private JFXTextField txtTextColour;

    @FXML
    private JFXTextField txtChordColour;

    @FXML
    private JFXCheckBox cbIsFinished;
    
    @FXML
    private JFXDrawer drawerLeft;
    
    @FXML
    private ImageView SaveAsPdf;
    
    @FXML
    private ImageView SaveAsChordPro;
    
    @FXML
    private ImageView ivChordDiagram;
    
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXTextArea txtSongEdit;
    
    @FXML
    private JFXTextField screen;
    
    @FXML
    private Label lblDateTime;
    
    @FXML
    private JFXButton btnWriteTex;
    
    @FXML
    private ImageView ivSaveAsPDF;

    @FXML
    private JFXButton lblFont;
    
    @FXML
    private JFXButton lblTranspose;
    
    @FXML
    private JFXButton lblEditInChordPro;
    
    @FXML
    private JFXButton lblSaveAsChordPro;
    
    @FXML
    private JFXButton lblChordDiagram;
    
    @FXML
    private Label lblSaveAsPDF;

    @FXML 
    private ImageView editAsChordProSyntax;
    
    @FXML
    private ImageView ivHelp;
    
    @FXML
    private JFXButton btnFontPlus;

    @FXML
    private JFXButton btnFontMinus;
        
    @FXML
    private JFXButton btnTransposePlus;

    @FXML
    private JFXButton btnTransposeMinus;
    
    @FXML
    private JFXTextArea txtChordDiagram;
    
    BooleanProperty clipboardEmpty = new SimpleBooleanProperty(false);
    BooleanProperty hideSidePane = new SimpleBooleanProperty(false);
    BooleanProperty showChordDiagram = new SimpleBooleanProperty(false);
    
    Song tempSong = new SongImpl();
    Song emptySong = new SongImpl();
    String filepath = "";
    String filename = "";
    SongProperties loadedSong = new SongProperties(emptySong);
	
	Clipboard clipboard = Clipboard.getSystemClipboard();
	
	//Dialog
	private String OPEN_CHORDPRO_FILE					= "Open ChordPro File";
    private String SAVE_CHORDPRO_FILE_AS				= "Save ChordPro File As";
    private String EXPORT_AS_PDF						= "Export As PDF";
    private String CHORD_PRO_FILES						= "ChordPro Files";
    private String TEXT_FILES							= "Text Files";
    private String ALL_FILES							= "All Files";
    private String BTN_YES								= "Yes";
    private String BTN_NO								= "No";
    private String BTN_CANCEL							= "Cancel";
    private String QUESTION_SAVE						= "Save?";
    private String QUESTION_PROJECT_MODIFIED			= "Current project is modified";
    private String SYNTAX_MODE							= "Syntax Mode";
    private String HELP_WINDOW_TITLE					= "Instructions to use the program";
	
	/**
     * Force empty current workspace. USE WITH CAUTION!
     */
    public void forceNewDocument() {
    	loadedSong = new SongProperties(emptySong);
		setDataBind();
		filename = "";
    }
	
	/**
     * Functions to be initialised during first window load
     */
	public void initialize(URL location, ResourceBundle resources) {
		
		loadedSong = new SongProperties(emptySong);
		setSidePaneBind();
		setShortcut();
		setOnAction();
		setMenuBind();
		setDataBind();
		setFormatter();
		showTime();
		txtYear.setText("0");
		txtTempo.setText("0");
		txtDuration.setText("0");
		txtCapo.setText("0");
		txtTextSize.setText("0");
		
		txtSongEdit.setFont(Font.font("monospaced",FontWeight.NORMAL,14));
		txtChordDiagram.setFont(Font.font("monospaced",FontWeight.NORMAL,18));
		
		HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
				transition.setRate(transition.getRate()*-1);
		transition.play();
		});
		
		setLanguage(UserData.getLocale());
	}

    /**
     * This function create a new document and clear edited fields.
     **/
    @FXML
    private void onClickFileNew(ActionEvent event) {
    	Song tmpSong = loadedSong.toSong(txtSongEdit.getText());
    	tmpSong.setYear(Integer.parseInt(txtYear.getText()));
    	tmpSong.setTempo(Integer.parseInt(txtTempo.getText()));
    	tmpSong.setDuration(Integer.parseInt(txtDuration.getText()));
    	tmpSong.setCapo(Integer.parseInt(txtCapo.getText()));
    	tmpSong.setTextsize(Integer.parseInt(txtTextSize.getText()));
    	if (!songsEqual(emptySong, tmpSong)) {
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
	    	        	onClickFileSave(new ActionEvent());
	    	        	loadedSong = new SongProperties(emptySong);
	    	    		setDataBind();
	    	        } else if (type == noButton) {
	    	        	loadedSong = new SongProperties(emptySong);
	    	    		setDataBind();
	    	        } else {

	    	        }
	    	});
    	}
    	filename = "";
    }
    
    /**
     * This function shows an open dialog window and open the chosen file.
     **/
    @FXML
    private void onClickFileOpen(ActionEvent event) {
    			
    	FileChooser fileChooser = new FileChooser();
    	try {
    		File f = new File(UserData.getOpenPath());
        	if (f.exists() && f.isDirectory())
            	fileChooser.setInitialDirectory(new File(UserData.getOpenPath()));
    	} 
    	catch (Exception e) 
    	{
    		fileChooser.setInitialDirectory(null);
    	}
    	fileChooser.setTitle(OPEN_CHORDPRO_FILE);
    	fileChooser.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter(CHORD_PRO_FILES, "*.cho", "*.crd", "*.chopro", "*.chord", "*.pro"),
    			new FileChooser.ExtensionFilter(ALL_FILES, "*.*")
    	);
    	
    	File selectedFile = fileChooser.showOpenDialog(null);
    	
    	if (selectedFile != null) {
    		ChordProParser cpp = new ChordProParser();
    		ChordChecker.customChord.clear();
    		tempSong = cpp.tryParseChordPro(selectedFile.getAbsolutePath());
    		UserData.setOpenPath(selectedFile.getParent());
			filename = selectedFile.getAbsolutePath();
    		if (ChordProParser.getErrorLines().isEmpty()) {
				loadedSong = new SongProperties(tempSong);
				setDataBind();
    		} else {
    			EditController editController = new EditController();
    	    	try{
    	    		final FXMLLoader fxmlLoader = new FXMLLoader();
    	            fxmlLoader.setLocation(getClass().getResource("/fxml/Edit.fxml"));
    	            fxmlLoader.setController(editController);
    	            editController.receiveEditText(GetStringFromFile.get(filename));
    	            editController.setNewEditorController(this);
    	            Stage stage = new Stage();
    	            Scene scene = new Scene(fxmlLoader.load());
    	            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    	                @Override
    	                public void handle(WindowEvent e) {
    	                    e.consume();
    	                    editController.closeWindowEvent(new WindowEvent(stage, null));
    	                }
    	            });
    	            stage.setResizable(true);
    	            stage.initOwner(lblDateTime.getScene().getWindow());
    	            stage.initModality(Modality.APPLICATION_MODAL);
    	            stage.setScene(scene);
    	            stage.setTitle(SYNTAX_MODE);
    	            stage.getIcons().add(new Image("/Icons/icon 512x512.png/"));
    	            stage.show();

    	        }catch(IOException io){
    	            io.printStackTrace();
    	        }
    		}
    	}
    }

    /**
     * This function close the whole program.
     * @param event
     */
    @FXML
    private void onClickFileQuit(ActionEvent event) {
    	Platform.exit();
        System.exit(0);
    }    

    /**
     * This function save the edited ChordPro document. If it was opened from a file, then the file will be overwritten, else a dialog to save it as new file will be opened.
     * @param event
     */
    @FXML
    public void onClickFileSave(ActionEvent event) {
    	if (filename.isBlank()) {
    		onClickFileSaveAs(event);
    	} else {
    		try {
        		PrintWriter out = new PrintWriter(filename);
        		Song tmpSong = loadedSong.toSong(txtSongEdit.getText());
            	tmpSong.setYear(Integer.parseInt(txtYear.getText()));
            	tmpSong.setTempo(Integer.parseInt(txtTempo.getText()));
            	tmpSong.setDuration(Integer.parseInt(txtDuration.getText()));
            	tmpSong.setCapo(Integer.parseInt(txtCapo.getText()));
            	tmpSong.setTextsize(Integer.parseInt(txtTextSize.getText()));
        		out.println(ChordProConverter.tryConvertToChordPro(tmpSong));
        		out.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
    	}	
    }
    
    /**
     * This function save the edited ChordPro document directly as a new file and a dialog window to choose path and name will be opened.
     * @param event
     */
    private void onClickFileSaveAs(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle(SAVE_CHORDPRO_FILE_AS);
    	fileChooser.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter(CHORD_PRO_FILES, "*.chopro", "*.crd", "*.cho", "*.chord", "*.pro"),
    			new FileChooser.ExtensionFilter(TEXT_FILES, "*.txt"),
    			new FileChooser.ExtensionFilter(ALL_FILES, "*.*")
    	);
    	if (!txtTitle.getText().isEmpty()) {
    		String defaultName = txtTitle.getText();
    		fileChooser.setInitialFileName(defaultName);
    	} else {
    		String[] defaultName = txtSongEdit.getText().split("\\n");
        	if (defaultName.length > 0)
        		fileChooser.setInitialFileName(defaultName[0].toString());
    	}
    	
    	File selectedFile = fileChooser.showSaveDialog(null);
    	if (selectedFile != null) {
	    	filename = selectedFile.getAbsolutePath();
	    	try {
	    		PrintWriter out = new PrintWriter(filename);
	    		Song tmpSong = loadedSong.toSong(txtSongEdit.getText());
	        	tmpSong.setYear(Integer.parseInt(txtYear.getText()));
	        	tmpSong.setTempo(Integer.parseInt(txtTempo.getText()));
	        	tmpSong.setDuration(Integer.parseInt(txtDuration.getText()));
	        	tmpSong.setCapo(Integer.parseInt(txtCapo.getText()));
	        	tmpSong.setTextsize(Integer.parseInt(txtTextSize.getText()));
	    		out.println(ChordProConverter.tryConvertToChordPro(tmpSong));
	    		out.close();
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
    	}
    }
    
    private void onClickExportAsPDF(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle(SAVE_CHORDPRO_FILE_AS);
    	fileChooser.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter("PDF", "*.pdf")
    	);
    	if (!txtTitle.getText().isEmpty()) {
    		String defaultName = txtTitle.getText();
    		fileChooser.setInitialFileName(defaultName);
    	} else {
    		String[] defaultName = txtSongEdit.getText().split("\\n");
        	if (defaultName.length > 0)
        		fileChooser.setInitialFileName(defaultName[0].toString());
    	}
    	
    	File selectedFile = fileChooser.showSaveDialog(null);
    	if (selectedFile != null) {
	    	filename = selectedFile.getAbsolutePath();
	    	try {
	    		Song song = loadedSong.toSong(txtSongEdit.getText());
	        	GeneratePDF.generatePDF(song);
	        	//GeneratePDF.generatePDF(filename,song);
	    		
	    	} catch (Exception e) {
	    		e.printStackTrace();
	    	}
    	}
    	
    	
    }
    
    /**
     * Functions to be executed when Hamburger is clicked
     */
    @FXML
    private void onClickHamburger(MouseEvent event) {
    	if (!hideSidePane.get()) {
    		AnchorPane.setLeftAnchor(txtSongEdit, 70.0);
    	} else {
    		AnchorPane.setLeftAnchor(txtSongEdit, 265.0);
    	}
    	hideSidePane.set(!hideSidePane.get());
    }
    
    /**
     * This function reacts the same as "Save As"
     * @param event
     */
    @FXML
    private void OnMousePressedSaveAsChordPro(MouseEvent event) {
    	onClickFileSaveAs(new ActionEvent());
    }
    
    /**
     * Set properties when song is changed or new syntax is parsed
     */
    private void setDataBind() {
    	txtTitle.textProperty().bindBidirectional(loadedSong.title);
    	txtSubtitle.textProperty().bindBidirectional(loadedSong.subtitle);
    	txtArtist.textProperty().bindBidirectional(loadedSong.artist);
    	txtComposer.textProperty().bindBidirectional(loadedSong.composer);
    	txtLyricist.textProperty().bindBidirectional(loadedSong.lyricist);
    	txtCopyright.textProperty().bindBidirectional(loadedSong.copyright);
    	txtAlbum.textProperty().bindBidirectional(loadedSong.album);
    	txtYear.textProperty().set(loadedSong.year.getValue().toString());
    	txtKey.textProperty().bindBidirectional(loadedSong.key);
    	txtTime.textProperty().bindBidirectional(loadedSong.time);
    	txtTempo.textProperty().set(loadedSong.tempo.getValue().toString());
    	txtDuration.textProperty().set(loadedSong.duration.getValue().toString());
    	txtCapo.textProperty().set(loadedSong.capo.getValue().toString());
    	txtTextFont.textProperty().bindBidirectional(loadedSong.textfont);
    	txtTextSize.textProperty().set(loadedSong.textsize.getValue().toString());
    	txtTextColour.textProperty().bindBidirectional(loadedSong.textcolour);
    	txtChordColour.textProperty().bindBidirectional(loadedSong.chordcolour);
    	cbIsFinished.selectedProperty().bindBidirectional(loadedSong.isFinished);
    	txtSongEdit.textProperty().bindBidirectional(loadedSong.contents);
    }
    
    /**
     * Set formatter for number only Textfields
     */
    private void setFormatter() {
    	txtYear.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtTempo.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtDuration.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtCapo.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtTextSize.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
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
    	
    	menuFile.setText(r.getString("WYSIWYG_MENUBAR_FILE"));
    	menuEdit.setText(r.getString("WYSIWYG_MENUBAR_EDIT"));
    	
    	menuFileNew.setText(r.getString("WYSIWYG_MENUITEM_FILE_NEW"));
    	menuFileOpen.setText(r.getString("WYSIWYG_MENUITEM_FILE_OPEN"));
    	menuFileSave.setText(r.getString("WYSIWYG_MENUITEM_FILE_SAVE"));
    	menuFileSaveAs.setText(r.getString("WYSIWYG_MENUITEM_FILE_SAVE_AS"));
    	menuExportAsPDF.setText(r.getString("WYSIWYG_MENUITEM_FILE_EXPORT_AS_PDF"));
    	menuFilePreferences.setText(r.getString("WYSIWYG_MENU_FILE_PREFERENCES"));
    	menuFilePreferencesLanguage.setText(r.getString("WYSIWYG_MENU_FILE_PREFERENCES_LANGUAGE"));
    	menuFilePreferencesLanguageDeutsch.setText(r.getString("WYSIWYG_MENUITEM_FILE_PREFERENCES_LANGUAGE_DEUTSCH"));
    	menuFilePreferencesLanguageEnglish.setText(r.getString("WYSIWYG_MENUITEM_FILE_PREFERENCES_LANGUAGE_ENGLISH"));
    	menuFileQuit.setText(r.getString("WYSIWYG_MENUITEM_FILE_QUIT"));
    	
    	menuEditUndo.setText(r.getString("WYSIWYG_MENUITEM_EDIT_UNDO"));
    	menuEditRedo.setText(r.getString("WYSIWYG_MENUITEM_EDIT_REDO"));
    	menuEditCut.setText(r.getString("WYSIWYG_MENUITEM_EDIT_CUT"));
    	menuEditCopy.setText(r.getString("WYSIWYG_MENUITEM_EDIT_COPY"));
    	menuEditPaste.setText(r.getString("WYSIWYG_MENUITEM_EDIT_PASTE"));
    	menuEditSelectAll.setText(r.getString("WYSIWYG_MENUITEM_EDIT_SELECT_ALL"));
    	
    	lblAttributes.setText(r.getString("WYSIWYG_LABEL_ATTRIBUTES"));

    	txtTitle.setPromptText(r.getString("WYSIWYG_TEXTFIELD_TITLE"));
    	txtSubtitle.setPromptText(r.getString("WYSIWYG_TEXTFIELD_SUBTITLE"));
    	txtArtist.setPromptText(r.getString("WYSIWYG_TEXTFIELD_ARTIST"));
    	txtComposer.setPromptText(r.getString("WYSIWYG_TEXTFIELD_COMPOSER"));
    	txtLyricist.setPromptText(r.getString("WYSIWYG_TEXTFIELD_LYRICIST"));
    	txtCopyright.setPromptText(r.getString("WYSIWYG_TEXTFIELD_COPYRIGHT"));
    	txtAlbum.setPromptText(r.getString("WYSIWYG_TEXTFIELD_ALBUM"));
    	txtYear.setPromptText(r.getString("WYSIWYG_TEXTFIELD_YEAR"));
    	txtKey.setPromptText(r.getString("WYSIWYG_TEXTFIELD_KEY"));
    	txtTime.setPromptText(r.getString("WYSIWYG_TEXTFIELD_TIME"));
    	txtTempo.setPromptText(r.getString("WYSIWYG_TEXTFIELD_TEMPO"));
    	txtDuration.setPromptText(r.getString("WYSIWYG_TEXTFIELD_DURATION"));
    	txtCapo.setPromptText(r.getString("WYSIWYG_TEXTFIELD_CAPO"));
    	txtTextFont.setPromptText(r.getString("WYSIWYG_TEXTFIELD_TEXTFONT"));
    	txtTextSize.setPromptText(r.getString("WYSIWYG_TEXTFIELD_TEXTSIZE"));
    	txtTextColour.setPromptText(r.getString("WYSIWYG_TEXTFIELD_TEXTCOLOUR"));
    	txtChordColour.setPromptText(r.getString("WYSIWYG_TEXTFIELD_CHORDCOLOUR"));
    	cbIsFinished.setText(r.getString("WYSIWYG_TEXTFIELD_ISFINISHED"));
    	
    	txtSongEdit.setPromptText(r.getString("WYSIWYG_TEXTAREA_SONG_EDIT"));
    	
    	lblFont.setText(r.getString("WYSIWYG_LABEL_FONT"));
    	lblTranspose.setText(r.getString("WYSIWYG_LABEL_TRANSPOSE"));
    	lblEditInChordPro.setText(r.getString("WYSIWYG_LABEL_EDIT_IN_CHORDPRO"));
    	lblSaveAsChordPro.setText(r.getString("WYSIWYG_LABEL_SAVE_AS_CHORDPRO"));
    	lblSaveAsPDF.setText(r.getString("WYSIWYG_LABEL_SAVE_AS_PDF"));
    	lblChordDiagram.setText(r.getString("WYSIWYG_LABEL_CHORD_DIAGRAM"));
    	
    	OPEN_CHORDPRO_FILE					= r.getString("OPEN_CHORDPRO_FILE");
    	SAVE_CHORDPRO_FILE_AS				= r.getString("SAVE_CHORDPRO_FILE_AS");
    	EXPORT_AS_PDF						= r.getString("WYSIWYG_MENUITEM_FILE_EXPORT_AS_PDF");
    	CHORD_PRO_FILES						= r.getString("CHORD_PRO_FILES");
    	TEXT_FILES							= r.getString("TEXT_FILES");
    	ALL_FILES							= r.getString("ALL_FILES");
    	BTN_YES								= r.getString("BTN_YES");
    	BTN_NO								= r.getString("BTN_NO");
    	BTN_CANCEL							= r.getString("BTN_CANCEL");
    	QUESTION_SAVE						= r.getString("QUESTION_SAVE");
    	QUESTION_PROJECT_MODIFIED			= r.getString("QUESTION_PROJECT_MODIFIED");
    	SYNTAX_MODE							= r.getString("SYNTAX_MODE");
    	HELP_WINDOW_TITLE					= r.getString("HELP_WINDOW_TITLE");
    }
    
    /**
     * Set disable property of edit menu
     */
    private void setMenuBind() {
    	menuEditUndo.disableProperty().bind(txtSongEdit.undoableProperty().not());
    	menuEditRedo.disableProperty().bind(txtSongEdit.redoableProperty().not());
    	menuEditCut.disableProperty().bind(txtSongEdit.selectedTextProperty().isEmpty());
    	menuEditCopy.disableProperty().bind(txtSongEdit.selectedTextProperty().isEmpty());
    	menuEditPaste.disableProperty().bind(clipboardEmpty.not());
    }
      
    /**
	 * Set functions to be called when an element is clicked
	 */
	private void setOnAction(){
    	menuFileNew.setOnAction(this::onClickFileNew);
		menuFileOpen.setOnAction(this::onClickFileOpen);
		menuFileSave.setOnAction(this::onClickFileSave);
		menuFileSaveAs.setOnAction(this::onClickFileSaveAs);
		menuExportAsPDF.setOnAction(this::onClickExportAsPDF);
		menuFileQuit.setOnAction(this::onClickFileQuit);
		
		SaveAsChordPro.setOnMousePressed(this::OnMousePressedSaveAsChordPro);
		
		menuEdit.setOnShowing((event) -> {
			clipboardEmpty.set(clipboard.hasString());
		});
		menuEditUndo.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.Z);
		});
		menuEditRedo.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.Y);
		});
		menuEditCopy.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.C);
		});
		menuEditCut.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.X);
		});
		menuEditPaste.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.V);
		});
		menuEditSelectAll.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.A);
		});

		hamburger.setOnMouseClicked(this::onClickHamburger);
		
		editAsChordProSyntax.setOnMouseClicked(this::switchSceneToEdit);
		
		ivHelp.setOnMouseClicked(this::switchSceneToQuestionIcon);
		
		btnTransposePlus.setOnMouseClicked((e) -> {
			if (!txtKey.getText().isEmpty())
				txtKey.setText(ChordTransposer.transposeUp(txtKey.getText())); 
			double pos = txtSongEdit.getScrollTop();
			txtSongEdit.replaceText(0, txtSongEdit.getLength(), ChordTransposer.changeChord(1,txtSongEdit.getText()));
			txtSongEdit.setScrollTop(pos);
			});
		
		btnTransposeMinus.setOnMouseClicked((e) -> {
			if (!txtKey.getText().isEmpty())
				txtKey.setText(ChordTransposer.transposeDown(txtKey.getText())); 
			double pos = txtSongEdit.getScrollTop();
			txtSongEdit.replaceText(0, txtSongEdit.getLength(), ChordTransposer.changeChord(-1,txtSongEdit.getText()));
			txtSongEdit.setScrollTop(pos);
			});
		
		btnFontPlus.setOnMouseClicked((e) -> {
			txtSongEdit.setFont(Font.font("monospaced",FontWeight.NORMAL,Math.min(txtSongEdit.getFont().getSize() + 2.0, 100.0)));
		});
		
		btnFontMinus.setOnMouseClicked((e) -> {
			txtSongEdit.setFont(Font.font("monospaced",FontWeight.NORMAL,Math.max(txtSongEdit.getFont().getSize() - 2.0, 4.0)));
		});

		menuFilePreferencesLanguageDeutsch.setOnAction((e) -> {
			UserData.setLocale("de","DE");
			setLanguage(new Locale("de","DE"));
		});
		
		menuFilePreferencesLanguageEnglish.setOnAction((e) -> {
			UserData.setLocale("en","US");
			setLanguage(new Locale("en","US"));
		});
		
		ivChordDiagram.setOnMouseClicked(this::showChordDiagrams);
    }
     
    /**
     * Set shortcut for menu items
     */
    private void setShortcut() {
    	menuFileNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
    	menuFileOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
    	menuFileSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
    	menuFileSaveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN));
    	menuFileQuit.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
    	
    	menuEditUndo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
    	menuEditRedo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
    	menuEditCut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
    	menuEditCopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
    	menuEditPaste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
    	menuEditSelectAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
    }
    
    /**
     * This function shows/hides right sidePane when hamburger is clicked ;
     */
    private void setSidePaneBind() {
    	lblAttributes.visibleProperty().bind(hideSidePane.not());
    	scrollpaneAttributes.visibleProperty().bind(hideSidePane.not());
    	scrollpaneAttributes.disableProperty().bind(hideSidePane);
    }
    
    /**
     * Show Chord Diagram Pane
     */
    private void showChordDiagrams(MouseEvent e) {
    	if (showChordDiagram.get()) {
    		AnchorPane.setRightAnchor(txtSongEdit, 20.0);
    	} else {
    		AnchorPane.setRightAnchor(txtSongEdit, 215.0);
    	}
    	showChordDiagram.set(!showChordDiagram.get());
    	txtChordDiagram.setText(loadedSong.fingering.get());
    }
    
    /**
	 * This function shows the clock.
	 */
    private void showTime() {
    	Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            lblDateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    
    /**
	 * This function simulate a two key pressed.
	 * @param 		a		first key
	 * @param 		b		second key
	 */	
	private void simulateKeyPress(KeyCode a, KeyCode b) {
		Robot bot;
		bot = new Robot();
		bot.keyPress(a);
		bot.keyPress(b);
		bot.keyRelease(a);
		bot.keyRelease(b);
	}
    
    /**
     * Check if songA equal to songB. CONTAINS BUGS!
     * @param 		songA		first song
     * @param 		songB		second song
     * @return					true if both song are equal
     */
    private boolean songsEqual(Song songA, Song songB) {
    	return songA.toString().equals(songB.toString()) ? true : false;
    }
	
	/**
     * Function to switch to Syntax Edit Window
     * @param event
     */
    public void switchSceneToEdit(MouseEvent event) {
    	Song tmpSong = loadedSong.toSong(txtSongEdit.getText());
    	tmpSong.setYear(Integer.parseInt(txtYear.getText()));
    	tmpSong.setTempo(Integer.parseInt(txtTempo.getText()));
    	tmpSong.setDuration(Integer.parseInt(txtDuration.getText()));
    	tmpSong.setCapo(Integer.parseInt(txtCapo.getText()));
    	tmpSong.setTextsize(Integer.parseInt(txtTextSize.getText()));
    	
    	EditController editController = new EditController();
    	try{
    		final FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/Edit.fxml"));
            fxmlLoader.setController(editController);
            editController.receiveEditText(ChordProConverter.tryConvertToChordPro(tmpSong));
            editController.setNewEditorController(this);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent e) {
                    e.consume();
                    editController.closeWindowEvent(new WindowEvent(stage, null));
                }
            });
            //stage.setOnCloseRequest(editController::closeWindowEvent);
            stage.setResizable(true);
            stage.initOwner(lblDateTime.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle(SYNTAX_MODE);
            stage.getIcons().add(new Image("/Icons/icon 512x512.png/"));
            stage.show();

        }catch(IOException io){
            io.printStackTrace();
        }
    }
    
    /**
     * Function to switch to Help Window
     * @param event
     */
    public void switchSceneToQuestionIcon(MouseEvent event) {
    	wp.createWindowNewStage("/fxml/HelpWindow.fxml", HELP_WINDOW_TITLE, new HelpWindowController(), lblDateTime.getScene().getWindow(), new Image("/Icons/icon 512x512.png/"));
    }
    
    /**
     * Update loaded song, whenever new file is opened or user create a new document
     * @param 		song		song to be loaded and binded to TextArea and TextFields
     */
    public void updateSong(Song song) {
    	loadedSong = new SongProperties(song);
    	txtChordDiagram.setText(loadedSong.fingering.get());
		setDataBind();
    }
}
