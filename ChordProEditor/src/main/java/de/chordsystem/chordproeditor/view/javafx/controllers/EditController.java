package de.chordsystem.chordproeditor.view.javafx.controllers;

import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

public class EditController implements Initializable {
	
	WindowPresetSwitchStage wp = new WindowPresetSwitchStage();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextArea txtEditSong;
    

    @FXML
    private JFXButton btnSave;
    
    private void setOnAction() {
    	btnSave.setOnAction(this::onClickFileSave);
    }
    
    @FXML
    private void onClickFileSave(ActionEvent event) {
    	
    }
    
    @FXML
    private void onClickFileSaveAs(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save ChordPro File As");
    	fileChooser.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter("ChordProFiles", "*.chopro", "*.crd", "*.cho", "*.chord", "*.pro"),
    			new FileChooser.ExtensionFilter("PDF", "*.pdf"),
    			new FileChooser.ExtensionFilter("Text file", "*.txt"),
    			new FileChooser.ExtensionFilter("All Files", "*.*")
    	);
    	String[] defaultName = txtEditSong.getText().split("\\n");
    	fileChooser.setInitialFileName(defaultName[0].toString());
    	
    	File selectedFile = fileChooser.showSaveDialog(null);
    	
    	//Write process
    	
    }
    
    
    public void initialize(URL location, ResourceBundle resources) {
    	
    }

}
