package de.chordsystem.chordproeditor.view.javafx.controllers;

import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;

import de.chordsystem.Prototype.ChordProParser;
import de.chordsystem.chordproeditor.model.classes.SongImpl;
import de.chordsystem.chordproeditor.model.classes.SongProperties;
import de.chordsystem.chordproeditor.model.interfaces.Song;
import de.chordsystem.latex.GeneratePDF;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;


/**
 * 
 * @author engin
 *
 */
public class NewEditorController implements Initializable {
	
	Song tempSong;
	SongProperties tempSongProperties;
	
	ObservableList list = FXCollections.observableArrayList();
	
	Clipboard clipboard = Clipboard.getSystemClipboard();
	
	@FXML
	private Label lblProperties;
	
	@FXML
	private ScrollPane scrollpaneProperties;
	
    @FXML
    private MenuBar MenuBar;
    
    @FXML
    private MenuItem menuFileNew;
    
    @FXML
    private MenuItem menuFileOpen;

    @FXML
    private MenuItem menuFileSave;
    
    @FXML
    private MenuItem menuFileSaveAs;

    @FXML
    private MenuItem menuFileExportAsPDF;

    @FXML
    private MenuItem menuFilePreferences;
    
    @FXML
    private MenuItem menuFileQuit;
    
    @FXML
    private MenuItem menuEditUndo;
    
    @FXML
    private MenuItem menuEditRedo;
        
    @FXML
    private MenuItem menuEditCut;
    
    @FXML
    private MenuItem menuEditCopy;
    
    @FXML
    private MenuItem menuEditPaste;
    
    @FXML
    private MenuItem menuEditSelectAll;
    
    @FXML
    private JFXListView<String> SongList;
    
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTextField txtTitle;

    @FXML
    private JFXTextField txtSubtitle;
    
    @FXML
    private JFXTextField txtArtist;
    
    @FXML
    private JFXTextField txtComposer;  

    @FXML
    private JFXTextField txtLyricist;  

    @FXML
    private JFXTextField txtCopyright;  
    
    @FXML
    private JFXTextField txtAlbum;  

    @FXML
    private JFXTextField txtYear;  

    @FXML
    private JFXTextField txtKey;  

    @FXML
    private JFXTextField txtTime;  

    @FXML
    private JFXTextField txtTempo;

    @FXML
    private JFXTextField txtDuration;  

    @FXML
    private JFXTextField txtTextFont;

    @FXML
    private JFXTextField txtTextSize;

    @FXML
    private JFXTextField txtTextColour;

    @FXML
    private JFXTextField txtChordColour;

    @FXML
    private JFXCheckBox cbIsFinished;
    
    @FXML
    private JFXDrawer drawerLeft;
    
    @FXML
    private MenuButton btnUncapoed;
    
    @FXML
    private TextField TextFieldCapo;
    
    @FXML
    private ImageView ivSaveAsPDF;
    
    @FXML
    private Label lblSaveAsPDF;
    
    @FXML
    private ImageView SaveAsChordPro;
    
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXTextArea txtSongEdit;
    
    @FXML
    private JFXTextField screen;
    
    @FXML
    private Label lblDateTime;
    
    @FXML
    private JFXButton btnWriteTex;
 
    BooleanProperty clipboardEmpty = new SimpleBooleanProperty(false);
    BooleanProperty hideSidePane = new SimpleBooleanProperty(false);
    
    /**
     * Getter
     * @return
     */
    public JFXListView getSongList() {
    	return SongList;
    }
    public JFXTextField gettxtSongTitle() {
    	return txtTitle;
    }
    public JFXTextField gettxtAlternativeTitle() {
    	return txtSubtitle;
    }
    public JFXTextField gettxtArtist() {
    	return txtArtist;
    }
    public JFXTextField gettxtSongId() {
    	return txtComposer;
    }
    public JFXTextArea gettxtSongInsert() {
    	return txtSongEdit;
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
    public ImageView getSaveAsChordPro() {
    	return SaveAsChordPro;
    }
    public JFXHamburger gethamburger() {
    	return hamburger; 
    }
    public Label getlblDateTime() {
    	return lblDateTime;
    }
    public JFXButton getbtnWriteTex() {
    	return btnWriteTex;
    }
    
    private void setOnAction() {
    	menuFileNew.setOnAction(this::onClickFileNew);
		menuFileOpen.setOnAction(this::onClickFileOpen);
		menuFileSave.setOnAction(this::onClickFileSave);
		menuFileSaveAs.setOnAction(this::onClickFileSaveAs);
		
		ivSaveAsPDF.setOnMouseClicked(this::generatePDF);
		lblSaveAsPDF.setOnMouseClicked(this::generatePDF);
		hamburger.setOnMouseClicked(this::onClickHamburger);
    }
    
    private void setShortcut() {
    	menuFileNew.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));
    	menuFileOpen.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));
    	menuFileSave.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));
    	menuFileSaveAs.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.ALT_DOWN));
    	menuFileExportAsPDF.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));
    	menuFilePreferences.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.ALT_DOWN));
    	menuFileQuit.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
    	
    	menuEditUndo.setAccelerator(new KeyCodeCombination(KeyCode.Z, KeyCombination.CONTROL_DOWN));
    	menuEditRedo.setAccelerator(new KeyCodeCombination(KeyCode.Y, KeyCombination.CONTROL_DOWN));
    	menuEditCut.setAccelerator(new KeyCodeCombination(KeyCode.X, KeyCombination.CONTROL_DOWN));
    	menuEditCopy.setAccelerator(new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_DOWN));
    	menuEditPaste.setAccelerator(new KeyCodeCombination(KeyCode.V, KeyCombination.CONTROL_DOWN));
    	menuEditSelectAll.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.CONTROL_DOWN));
    }
    
    private void setMenuBind() {
    	menuEditUndo.disableProperty().bind(txtSongEdit.undoableProperty().not());
    	menuEditRedo.disableProperty().bind(txtSongEdit.redoableProperty().not());
    	menuEditCut.disableProperty().bind(txtSongEdit.selectedTextProperty().isEmpty());
    	menuEditCopy.disableProperty().bind(txtSongEdit.selectedTextProperty().isEmpty());
    	menuEditPaste.disableProperty().bind(clipboardEmpty);
    }
    
    private void setFormatter() {
    	txtYear.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtTempo.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtDuration.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtTextSize.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    }
    
    private void setDataBind() {
    	txtTitle.textProperty().bindBidirectional(tempSongProperties.title);
    	txtSubtitle.textProperty().bindBidirectional(tempSongProperties.subtitle);
    	txtArtist.textProperty().bindBidirectional(tempSongProperties.artist);
    	txtComposer.textProperty().bindBidirectional(tempSongProperties.composer);
    	txtLyricist.textProperty().bindBidirectional(tempSongProperties.lyricist);
    	txtCopyright.textProperty().bindBidirectional(tempSongProperties.copyright);
    	txtAlbum.textProperty().bindBidirectional(tempSongProperties.album);
    	txtYear.textProperty().bindBidirectional(tempSongProperties.year, new NumberStringConverter());
    	txtKey.textProperty().bindBidirectional(tempSongProperties.key);
    	txtTime.textProperty().bindBidirectional(tempSongProperties.time);
    	txtTempo.textProperty().bindBidirectional(tempSongProperties.tempo, new NumberStringConverter());
    	txtDuration.textProperty().bindBidirectional(tempSongProperties.duration, new NumberStringConverter());
    	txtTextFont.textProperty().bindBidirectional(tempSongProperties.textfont);
    	txtTextSize.textProperty().bindBidirectional(tempSongProperties.textsize, new NumberStringConverter());
    	txtTextColour.textProperty().bindBidirectional(tempSongProperties.textcolour);
    	txtChordColour.textProperty().bindBidirectional(tempSongProperties.chordcolour);
    	cbIsFinished.selectedProperty().bindBidirectional(tempSongProperties.isFinished);
    	txtSongEdit.textProperty().bindBidirectional(tempSongProperties.contents);
    }
    
    @FXML
    private void onClickFileNew(ActionEvent event) {
    	
    }
    
    @FXML
    private void onClickFileOpen(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open ChordPro File");
    	fileChooser.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter("ChordProFiles", "*.cho", "*.crd", "*.chopro", "*.chord", "*.pro"),
    			new FileChooser.ExtensionFilter("All Files", "*.*")
    	);
    	
    	File selectedFile = fileChooser.showOpenDialog(null);
    	
    	if (selectedFile != null) {
        	System.out.println(selectedFile);
    		ChordProParser cpp = new ChordProParser();
    		tempSong = cpp.tryParseChordPro(selectedFile.getAbsolutePath());
        	System.out.println(selectedFile.getAbsolutePath());
    		tempSongProperties = new SongProperties(tempSong);
    		setDataBind();
    	}
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
    	String[] defaultName = txtSongEdit.getText().split("\\n");
    	fileChooser.setInitialFileName(defaultName[0].toString());
    	
    	File selectedFile = fileChooser.showSaveDialog(null);
    	
    	//Write process
    	
    }

    @FXML
    private void onFocusTxtTitle(MouseEvent event) {
    	System.out.println(tempSongProperties.title.getValue());
    	System.out.println(txtTitle.getText());
    	System.out.println(tempSongProperties.subtitle.getValue());
    	System.out.println(txtSubtitle.getText());
    }
    
    private void setSidePaneBind() {
    	lblProperties.visibleProperty().bind(hideSidePane.not());
    	scrollpaneProperties.visibleProperty().bind(hideSidePane.not());
    	scrollpaneProperties.disableProperty().bind(hideSidePane);
    }
    
    @FXML
    private void onClickHamburger(MouseEvent event) {
    	if (!hideSidePane.get()) {
    		AnchorPane.setLeftAnchor(txtSongEdit, 70.0);
    	} else {
    		AnchorPane.setLeftAnchor(txtSongEdit, 265.0);
    	}
    	hideSidePane.set(!hideSidePane.get());
    	
    	HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED,(e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();
		});
    }
    
    @FXML
    private void generatePDF(MouseEvent event) {
    	GeneratePDF.generatePDF(tempSong);
    }
    
	public void initialize(URL location, ResourceBundle resources) {
		
		tempSong = new SongImpl();
		tempSongProperties = new SongProperties(tempSong);
		txtTitle.setOnMouseClicked(this::onFocusTxtTitle);
		setSidePaneBind();
		setShortcut();
		setOnAction();
		setMenuBind();
		setDataBind();
		setFormatter();
		showTime();

		
		HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
				transition.setRate(transition.getRate()*-1);
		transition.play();
		});
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
    		
    	} else {
    		screen.setText(song + "Ausgewählt");
    	}

    }


}
