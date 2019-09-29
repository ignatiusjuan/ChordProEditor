package de.chordsystem.chordproeditor.view.javafx.controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import de.chordsystem.chordproeditor.userdata.UserData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for HelpWindow
 * @author IgnatiusJuanPradipta
 * @author engin
 */
public class HelpWindowController implements Initializable {

    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private Label lblTitle;
    
    @FXML
    private Label lblDescription;
    
    @FXML
    private JFXButton btnBack;
    
    @FXML
    private CheckBox cklShowHelpOnStart;
    
    /**
     * Function to close the Help Window
     **/
    private void closeHelpWindow(ActionEvent event) {
    	btnBack.getScene().getWindow().hide();
    }
    
    /**
     * Method to set HelpWindow showed or not on start
     * @param event
     */
    private void tickShowHelp(ActionEvent event) {
    	if (cklShowHelpOnStart.isSelected())
    		UserData.setShowHelpOnStart(true);
    	else
    		UserData.setShowHelpOnStart(false);
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
    	
    	lblTitle.setText(r.getString("HELP_LABEL_TITLE"));
    	lblDescription.setText(r.getString("HELP_LABEL_DESCRIPTION"));
    	btnBack.setText(r.getString("HELP_BUTTON_BACK"));
    	cklShowHelpOnStart.setText(r.getString("HELP_CHECKLIST_SHOW_ON_START"));
    }
    
    /**
     * Functions to be initialised during first window load
     **/
    @Override
 	public void initialize(URL url, ResourceBundle resourceBundle) {
 		btnBack.setOnAction(this::closeHelpWindow);
 		cklShowHelpOnStart.setSelected(UserData.getShowHelpOnStart());
    	cklShowHelpOnStart.setOnAction(this::tickShowHelp);
    	
    	setLanguage(UserData.getLocale());
    }

}

