package de.chordsystem.chordproeditor.view.javafx.controllers;

import de.chordsystem.chordproeditor.parser.ChordProParser;
import de.chordsystem.chordproeditor.model.classes.SongProperties;
import de.chordsystem.chordproeditor.model.interfaces.Song;
import de.chordsystem.chordproeditor.userdata.UserData;
import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

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
    private final String templateChorus 			= "{start_of_chorus: chorus_name}\n"+ 
			  									  "chord_and_lyric\n"				+
			  									  "{end_of_chorus}\n";
    private final String templateTab 			= "{start_of_tab: tab_name}\n"		+ 
    											  "tab_contents\n"					+
    											  "{end_of_chorus}\n";
    private final String templateGrid 			= "{start_of_grid: grid_name}\n"	+ 
			  									  "grid_contents\n"					+
			  									  "{end_of_grid}\n";
    private final String templateComment			= "{comment: comment}";
    private final String templateChordDiagram	= "{chord: chord_name base-fret offset frets x 0 1 2 3 4 fingers x 0 1 2 3 4 5}";
    
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
    	} else {
    		AnchorPane.setLeftAnchor(txtAreaEditSong, 190.0);
    	}
    	hideSidePane.set(!hideSidePane.get());
    }
    
    @FXML
    private void onClickInsertText(String insertText) {
    	txtAreaEditSong.insertText(txtAreaEditSong.getCaretPosition(), insertText);
    	// need to add undo point to txtAreaEditSong 
    	txtAreaEditSong.requestFocus();
    }
    
    public void receiveEditText(String message) {
    	textFieldProperty.set(message);
    }
    
    @FXML
    private void onClickSaveBtn(ActionEvent event) {
    	ChordProParser cpp = new ChordProParser();
		Song tempSong = cpp.tryParseChordProString(txtAreaEditSong.textProperty().getValue());
		newEditorController.updateSong(tempSong);
		btnSave.getScene().getWindow().hide();
    }
    
    /*Hier werden die anklickbaren Button ihren jeweiligen Methoden zugewiesen*/
    public void initialize(URL location, ResourceBundle resources) {
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
    	ivRedo.setOnMouseClicked((e) -> {txtAreaEditSong.undo(); txtAreaEditSong.requestFocus();});
    	
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
