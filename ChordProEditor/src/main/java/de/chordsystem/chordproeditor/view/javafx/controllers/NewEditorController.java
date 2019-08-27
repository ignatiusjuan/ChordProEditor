package de.chordsystem.chordproeditor.view.javafx.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import de.chordsystem.chordproeditor.model.classes.SongImpl;
import de.chordsystem.latex.GeneratePDF;

import de.chordsystem.chordproeditor.model.interfaces.Song;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


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
    private AnchorPane anchorPane;

    @FXML
    private JFXTextField txtSongTitle;

    @FXML
    private JFXTextField txtAlternativeTitle;
    
    @FXML
    private JFXTextField txtArtist;
    
    @FXML
    private JFXTextField txtSongId;  
        
    @FXML
    private MenuButton btnUncapoed;
    
    @FXML
    private TextField TextFieldCapo;
    
    @FXML
    private JFXButton btnMinus;
    
    @FXML
    private JFXButton btnPlus;
    
    @FXML
    private ImageView SaveAsPdf;
    
    @FXML
    private ImageView SaveAsChordPro;
    
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXTextArea txtSongInsert;
    
    @FXML
    private JFXTextField screen;
    
    @FXML
    private Label label;

    @FXML
    private Label lblDateTime;
    
    @FXML
    private JFXButton btnWriteTex;
   
    @FXML
    private JFXDrawer drawer;
 
    
    /**
     * Getter
     * @return
     */
    public JFXListView getSongList() {
    	return SongList;
    }
    public JFXTextField gettxtSongTitle() {
    	return txtSongTitle;
    }
    public JFXTextField gettxtAlternativeTitle() {
    	return txtAlternativeTitle;
    }
    public JFXTextField gettxtArtist() {
    	return txtArtist;
    }
    public JFXTextField gettxtSongId() {
    	return txtSongId;
    }
    public JFXTextArea gettxtSongInsert() {
    	return txtSongInsert;
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
    public ImageView getSaveAsPdf() {
    	return SaveAsPdf;
    }
    public ImageView getSaveAsChordPro() {
    	return SaveAsChordPro;
    }
    public JFXHamburger gethamburger() {
    	return hamburger; 
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
    public JFXDrawer getdrawer() {
    	return drawer;
    }
    
    @FXML
    // TODO: ist der methode funktion übergeben
    private void onEnterSongTitle(ActionEvent event) {
    	
    }
    
    @FXML
    private void onEnterAlternativeTitle(ActionEvent even) {
    	
    }
    
    //Methode to call generatePDF
    private void clickOnWriteTex(ActionEvent event) {
    	Song song = new SongImpl();
    	song.setTitle("Hallo");
    	GeneratePDF.generatePDF(song);
    }
    
    

	public void initialize(URL location, ResourceBundle resources) {
		loadData();
		showTime();
		//btnUncapoed.setOnAction(this::onEnter);
		txtSongTitle.setOnAction(this::onEnterSongTitle);
		txtAlternativeTitle.setOnAction(this::onEnterAlternativeTitle);
		
		
		btnWriteTex.setOnAction(this::clickOnWriteTex);
		
		
		//methode noch eigene animation bauen 
		
		//-----------------------Erledigen----------------
		//SaveAsChordPro.setOnMouseClicked();
		//SaveAsPdf.setOnMouseClicked();
		//-----------------------Erledigen----------------
		
		/**
		 * Methode um auf den Slide des Menu Hamburgers zuzugreifen und auszugeben
		 */
//		VBox box = FXMLLoader.load(getClass().getResource("DrawerContent.fxml"));
//		drawer.setSidePane(box);
//		HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
//			burgerTask2.setRate(-1);
//			hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
//				burgerTask2.setRate(burgerTask2.getRate() * -1);
//				burgerTask2.play();
//				
//				if(drawer.isShown())
//				drawer.hide();
//				else
//				drawer.draw();
//			});
		
	}
	
	// falls Datenbank extieren würde
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
