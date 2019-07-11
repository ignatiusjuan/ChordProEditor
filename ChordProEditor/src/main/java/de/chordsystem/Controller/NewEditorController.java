package de.chordsystem.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;


/**
 * 
 * @author engin
 *
 */
public class NewEditorController implements Initializable {
	ObservableList list = FXCollections.observableArrayList();
	
	
	
    @FXML
    private MenuBar MenuBar;

    @FXML
    private JFXListView<String> SongList;
    
  

    @FXML
    private JFXButton btnTitle;

    @FXML
    private JFXButton btnArtist;

    @FXML
    private MenuButton btnUncapoed;
    
    @FXML
    private TextField TextFieldCapo;
    
    @FXML
    private JFXButton btnMinus;
    
    @FXML
    private JFXButton btnPlus;
    
    @FXML
    private JFXButton btnTags;
    
    @FXML
    private ImageView Speichern;
    
    @FXML
    private ImageView Feld;

    @FXML
    private JFXTextArea TextArea;
    
    @FXML
    private JFXTextField screen;
    
    @FXML
    private Label label;

    @FXML
    private Label lblDateTime;
    
    @FXML
    private JFXButton btnWriteTex;
    
    
    
    
    
    public JFXListView getSongList() {
    	return SongList;
      }
    public JFXButton getbtnTitle() {
    	return btnTitle;
    }
    public JFXButton getbtnArtist() {
    	return btnArtist;
    }
    
    public JFXButton getbtnTags() {
    	return btnTags;
    }
    
    public JFXTextArea getTextArea() {
    	return TextArea;
    }
    public JFXTextField getScreen() {
    	return screen;
    }
    public MenuButton getbtnUncapoed() {
    	return btnUncapoed;
    }
    public TextField getTextFieldCapo() {
    	return TextFieldCapo; 
    }
    public JFXButton getbtnMinus() {
    	return btnMinus;
    }
    public JFXButton getbtnPlus() {
    	return btnPlus;
    }
    public ImageView getSpeichern() {
    	return Speichern;
    }
    public ImageView getFeld() {
    	return Feld; 
    }
    public Label getLabel() {
    	return label;
    }
    public Label getlblDateTime() {
    	return lblDateTime;
    }
    public JFXButton getbtnWriteTex() {
    	return btnWriteTex;
    }
    
    

	public void initialize(URL location, ResourceBundle resources) {
		loadData();
		showTime();
		//TextArea.setOnAction(this::onEnterSong);
	}
		
	private void loadData() {
		list.removeAll(list);
		String a="Perfect Day";
		String b="Symphonie";
		String c="Happy";
		String d="Nexter Song";
		list.addAll(a,b,c,d);
		SongList.getItems().addAll(list);
	}
	
	/**
	 * Methode um die Aktuelle Uhrzeit mit Sekundentakt in dem 
	 * Fenster auszugeben
	 */
    private void showTime() {
    	Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            lblDateTime.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    
    
    /**
     * Methode um einen Song aus der SongListe auszuwählen 
     * @param event
     */
    @FXML
    void displaySelected(MouseEvent event) {
    	String song = SongList.getSelectionModel().getSelectedItem();
    	if (song==null || song.isEmpty()) {
    		screen.setText("Kein Song ausgewäht");
    		
    	}else {
    		screen.setText(song + "Ausgewählt");
    	}

    }


}
