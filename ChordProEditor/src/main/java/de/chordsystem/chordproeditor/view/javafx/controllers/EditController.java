package de.chordsystem.chordproeditor.view.javafx.controllers;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import de.chordsystem.chordproeditor.model.interfaces.Song;
import de.chordsystem.chordproeditor.parser.ChordProConverter;
import de.chordsystem.chordproeditor.parser.ChordProParser;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

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
    private JFXTextArea txtAreaEditSong;

    @FXML
    private JFXButton btnSave;
    
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
    
    StringProperty textFieldProperty = new SimpleStringProperty();
    BooleanProperty hideSidePane = new SimpleBooleanProperty(false);
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
    private final String templateTextColour 		= "{textcolour: insert_textcolour_here}\n";
    private final String templateChordColour 	= "{chordcolour: insert_chordcolour_here}\n";
    
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
    
    private void setSidePaneBind() {
    	lblInsert.visibleProperty().bind(hideSidePane.not());
    	lblAttributes.visibleProperty().bind(hideSidePane.not());
    	lblContents.visibleProperty().bind(hideSidePane.not());
    	scrollpaneAttributes.visibleProperty().bind(hideSidePane.not());
    	scrollpaneContents.visibleProperty().bind(hideSidePane.not());
    	scrollpaneAttributes.disableProperty().bind(hideSidePane);
    	scrollpaneContents.disableProperty().bind(hideSidePane);
    }
    
    /*Methode zum benutzen des Hamburger Buttons im Fenster*/
    @FXML
    private void onClickHamburger(MouseEvent event) {
    	if (!hideSidePane.get()) {
    		AnchorPane.setLeftAnchor(txtAreaEditSong, 60.0);
    		AnchorPane.setLeftAnchor(ivUndo, 60.0);
    		AnchorPane.setLeftAnchor(ivUndoGrey, 60.0);
    		AnchorPane.setLeftAnchor(ivRedo, 130.0);
    		AnchorPane.setLeftAnchor(ivRedoGrey, 130.0);
    	} else {
    		AnchorPane.setLeftAnchor(txtAreaEditSong, 190.0);
    		AnchorPane.setLeftAnchor(ivUndo, 190.0);
    		AnchorPane.setLeftAnchor(ivUndoGrey, 190.0);
    		AnchorPane.setLeftAnchor(ivRedo, 260.0);
    		AnchorPane.setLeftAnchor(ivRedoGrey, 260.0);
    	}
    	hideSidePane.set(!hideSidePane.get());
    }
    
    private void onClickInsertText(String insertText) {
    	txtAreaEditSong.insertText(txtAreaEditSong.getCaretPosition(), insertText);
    	txtAreaEditSong.requestFocus();
    }
    
    public void receiveEditText(String message) {
    	textFieldProperty.set(message);
    }
    
    @FXML
    private void onClickSaveBtn(ActionEvent event) {
    	if (newEditorController.filename.isBlank()) {
    		FileChooser fileChooser = new FileChooser();
        	fileChooser.setTitle("Save ChordPro File As");
        	fileChooser.getExtensionFilters().addAll(
        			new FileChooser.ExtensionFilter("ChordProFiles", "*.chopro", "*.crd", "*.cho", "*.chord", "*.pro"),
        			new FileChooser.ExtensionFilter("PDF", "*.pdf"),
        			new FileChooser.ExtensionFilter("Text file", "*.txt"),
        			new FileChooser.ExtensionFilter("All Files", "*.*")
        	);
        	
        	File selectedFile = fileChooser.showSaveDialog(null);
        	if (selectedFile != null) {
        		newEditorController.filename = selectedFile.getAbsolutePath();
        	}
    	}
    	if (!newEditorController.filename.isBlank())
    		try {
        		PrintWriter out = new PrintWriter(newEditorController.filename);
        		out.println(txtAreaEditSong.getText());
        		out.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
    }
    
    @FXML
    private void onSwitchToWYSIWYG(MouseEvent event) {
    	ChordProParser cpp = new ChordProParser();
		Song tempSong = cpp.tryParseChordProString(txtAreaEditSong.textProperty().getValue());
		if (ChordProParser.getErrorLines().isEmpty()) {
			newEditorController.updateSong(tempSong);
			btnSave.getScene().getWindow().hide();
		}
    }
    
    /*Hier werden die anklickbaren Button ihren jeweiligen Methoden zugewiesen*/
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	editAsWYSIWYG.setOnMouseClicked(this::onSwitchToWYSIWYG);
    	btnSave.setOnAction(this::onClickSaveBtn);
    	txtAreaEditSong.setFont(Font.font("monospaced",FontWeight.NORMAL,14));
    	txtAreaEditSong.textProperty().bindBidirectional(textFieldProperty);
    	
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
    	
    	setSidePaneBind();
    	
    	HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(jfxHamHide);
		transition.setRate(-1);
		jfxHamHide.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
				transition.setRate(transition.getRate()*-1);
		transition.play();
		});
		jfxHamHide.setOnMouseClicked(this::onClickHamburger);
		
    }
    
    public void setNewEditorController(NewEditorController newEditorController) {
    	this.newEditorController = newEditorController;
    }

}
