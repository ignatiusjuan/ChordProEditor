package de.chordsystem.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;

public class NewEditorController {
	
    @FXML
    private MenuBar MenuBar;

    @FXML
    private JFXListView<?> ListView;

    @FXML
    private JFXButton btnTitle;

    @FXML
    private JFXButton btnArtist;

    @FXML
    private JFXTextArea PdfFile;

    @FXML
    private JFXTextArea TextArea;
    
    public JFXListView getListView() {
    	return ListView;
      }
    public JFXButton getbtnTitle() {
    	return btnTitle;
    }
    public JFXButton getbtnArtist() {
    	return btnArtist;
    }
    public JFXTextArea getPdfFile() {
    	return PdfFile;
    }
    public JFXTextArea getTextArea() {
    	return TextArea;
    }

}
