package de.chordsystem.chordproeditor.view.javafx.controllers;

import de.chordsystem.Prototype.ChordProParser;
import de.chordsystem.chordproeditor.model.classes.SongProperties;
import de.chordsystem.chordproeditor.model.interfaces.Song;
import de.chordsystem.chordproeditor.userdata.UserData;
import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EditController implements Initializable {
	
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextArea txtAreaEditSong;

    @FXML
    private JFXButton btnSave;
    
    StringProperty textFieldProperty = new SimpleStringProperty();
    private NewEditorController newEditorController;
    
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
    }
    
    public void setNewEditorController(NewEditorController newEditorController) {
    	this.newEditorController = newEditorController;
    }

}
