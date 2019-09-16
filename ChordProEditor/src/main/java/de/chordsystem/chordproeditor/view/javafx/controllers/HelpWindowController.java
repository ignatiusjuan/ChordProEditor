package de.chordsystem.chordproeditor.view.javafx.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class HelpWindowController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton btnBack;
    
    @FXML
    private CheckBox cklShowHelpOnStart;
    
    private void closeHelpWindow(ActionEvent event) {
    	btnBack.getScene().getWindow().hide();
    }
    
    private void tickShowHelp(ActionEvent event) {
    	
    }
    
    
    @Override
 	public void initialize(URL url, ResourceBundle resourceBundle) {
 		btnBack.setOnAction(this::closeHelpWindow);
    	cklShowHelpOnStart.setOnAction(this::tickShowHelp);
    
    }

}

