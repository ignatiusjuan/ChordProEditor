package de.chordsystem.chordproeditor.view.javafx.controllers;

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
import javafx.stage.FileChooser;

public class EditController implements Initializable {
	
	WindowPresetSwitchStage wp = new WindowPresetSwitchStage();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextArea txtAreaEditSong;

    @FXML
    private JFXButton btnSave;
    
    StringProperty textFieldProperty = new SimpleStringProperty();
    
    private void setOnAction() {
    	btnSave.setOnAction(this::onClickFileSave);
    }
    
    @FXML
    private void onClickFileSave(ActionEvent event) {
    	
    }
    
    public void receiveEditText(String message) {
    	textFieldProperty.set(message);
    }
    
    @FXML
    private void onClickFileSaveAs(ActionEvent event) {
    	
    	
    	
    	//Write process
    	
    }
    
    
    public void initialize(URL location, ResourceBundle resources) {
    	Font font = new Font("Courier", 20);
    	txtAreaEditSong.setFont(font);
    	txtAreaEditSong.textProperty().bindBidirectional(textFieldProperty);
    }

}
