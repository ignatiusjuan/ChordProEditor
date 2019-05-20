package de.chordsystem.Controller;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;

public class ChordProEditorController {
	


	    @FXML
	    private JFXTextField TextFieldSongTitle;

	    @FXML
	    private JFXTextField TextFieldAlternativeTitle;

	    @FXML
	    private JFXTextField TextFieldArtist;

	    @FXML
	    private MenuButton btnKey;

	    @FXML
	    private MenuButton btnCapo;

	    @FXML
	    private MenuButton btnFormat;

	    public JFXTextField getTextFieldSongTitle() {
	    	return TextFieldSongTitle;
	    }
	    public JFXTextField getTextFieldAlternativeTitle() {
	    	return TextFieldAlternativeTitle;
	    }
	    public JFXTextField getTextFieldArtist() {
	    	return TextFieldArtist;
	    }
	    public MenuButton getbtnKey() {
	    	return btnKey;
	    }
	    public MenuButton getbtnCapo() {
	    	return btnCapo;
	    }
	    public MenuButton getbtnFormat() {
	    	return btnFormat;
	    }
	    
	    
	    
	}






