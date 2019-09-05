package de.chordsystem.chordproeditor.view.javafx.controllers;

import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXTextArea;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class EditController implements Initializable {
	
	WindowPresetSwitchStage wp = new WindowPresetSwitchStage();

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextArea txtAreaEditSong;
    
    public void initialize(URL location, ResourceBundle resources) {
    	
    }

}
