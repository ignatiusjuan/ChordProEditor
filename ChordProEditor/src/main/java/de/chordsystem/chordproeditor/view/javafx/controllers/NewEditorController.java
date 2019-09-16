package de.chordsystem.chordproeditor.view.javafx.controllers;

import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;
import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchScene;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
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
import de.chordsystem.Prototype.TextParser;
import de.chordsystem.chordproeditor.model.classes.EnvironmentImpl;
import de.chordsystem.chordproeditor.model.classes.SongImpl;
import de.chordsystem.chordproeditor.model.classes.SongProperties;
import de.chordsystem.chordproeditor.model.interfaces.Environment;
import de.chordsystem.chordproeditor.model.interfaces.Song;
import de.chordsystem.chordproeditor.userdata.UserData;
import de.chordsystem.latex.GeneratePDF;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
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
import javafx.scene.robot.Robot;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;
import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;
/**
 * 
 * @author engin
 *
 */
public class NewEditorController implements Initializable {
	
	private WindowPresetSwitchStage wp = new WindowPresetSwitchStage();
	private WindowPresetSwitchScene wpss = new WindowPresetSwitchScene();
	
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
    private Menu menuEdit;
    
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
    private ImageView SaveAsPdf;
    
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
    
    @FXML
    private ImageView ivSaveAsPDF;

    @FXML
    private Label lblSaveAsPDF;

    @FXML 
    private ImageView EditIcon;
    
    @FXML
    private ImageView ivHelp;
    
    @FXML
    private JFXButton btnPlus;

    @FXML
    private JFXButton btnMinus;
    
    
    BooleanProperty clipboardEmpty = new SimpleBooleanProperty(false);
    BooleanProperty hideSidePane = new SimpleBooleanProperty(false);
    
    Song tempSong = new SongImpl();
    Song emptySong = new SongImpl();
    String filepath = "";
    String filename = "";
    SongProperties loadedSong;
	
	Clipboard clipboard = Clipboard.getSystemClipboard();
	
	private void simulateKeyPress(KeyCode a, KeyCode b) {
		Robot bot;
		bot = new Robot();
		bot.keyPress(a);
		bot.keyPress(b);
		bot.keyRelease(a);
		bot.keyRelease(b);
	}
	
    private void setOnAction(){
    	menuFileNew.setOnAction(this::onClickFileNew);
		menuFileOpen.setOnAction(this::onClickFileOpen);
		menuFileSave.setOnAction(this::onClickFileSave);
		menuFileSaveAs.setOnAction(this::onClickFileSaveAs);
		
		menuEdit.setOnShowing((event) -> {
			clipboardEmpty.set(clipboard.hasString());
		});
		menuEditUndo.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.Z);
		});
		menuEditRedo.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.Y);
		});
		menuEditCopy.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.C);
		});
		menuEditCut.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.X);
		});
		menuEditPaste.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.V);
		});
		menuEditSelectAll.setOnAction((event) -> {
			simulateKeyPress(KeyCode.CONTROL, KeyCode.A);
		});
		
		ivSaveAsPDF.setOnMouseClicked((event) -> {
//			String text = "";
//			text += "# From: stevenj1@aol.com (StevenJ1)\n";
//			text += "<mau@beatles.cselt.stet.it>\n";
//			text += "#By George David Weiss and Bob Thiele\n";
//			text += "\n";
//			text += "D          F#m       G          F#m\n";
//			text += "I see trees of green, red roses too\n";
//			text += "Em7           D      F#7            Bm\n";
//			text += "I see them bloom, for me and you,\n";
//			text += "Bb                Em7/A        A7          D     D+     Gmaj7        A7\n";
//			text += "And I think to myself, What a wonderful world.\n";
//			text += "//cb\n";
//			text += "\n";
//			text += "Chorus: chorus 1G\n";
//			text += "aaaaaaaaa\n";
//			text += "\n";
//			text += "Chorus: chorus 2G\n";
//			text += "aaaaaaaaa\n";
//			text += "//cb\n";
//			text += "\n";
//			text += "//cb\n";
//			text += "\n";
//			text += "Chorus: chorus 3G\n";
//			text += "aaaaaaaaa\n";
//			
//			Song song = TextParser.parseText(text);
//			String ausgabe = song.toString();
//			System.out.print(ausgabe);
			
			tempSong.setArtist("Kuenstler Test");
			tempSong.setAlbum("Album Test");
			tempSong.setComposer("Composer Test");
			tempSong.setCopyright("Copyright Test");
			tempSong.setDuration(4);
			tempSong.setFinished(false);
			tempSong.setLyricist("Lyricist Test");
			tempSong.setTitle("Test");
			tempSong.setYear(2000);
			tempSong.setSubtitle("Subtitle Test");
			
//			for(int i = 0; i < 6; i++) {
//				Environment env1 = new EnvironmentImpl();
//				env1.setType(EnvironmentImpl.TYPE_CHORUS);
//				env1.setTitle("Title Test "+i);
//				tempSong.addEnvironment(env1);
//				Environment env2 = new EnvironmentImpl();
//				env2.setType(EnvironmentImpl.TYPE_VERSE);
//				env2.setChord("Chord Test "+i);
//				env2.setLyric("Test Lyric "+i);
//				tempSong.addEnvironment(env2);
//				Environment env3 = new EnvironmentImpl();
//				env3.setType(EnvironmentImpl.TYPE_COMMENT);
//				env3.setLyric("Kommentar "+i);
//				tempSong.addEnvironment(env3);
//			}
			
			GeneratePDF.generatePDF(tempSong);
		});
		lblSaveAsPDF.setOnMouseClicked((event) -> {
			//GeneratePDF.generatePDF(tempSong);
		});
		
		hamburger.setOnMouseClicked(this::onClickHamburger);
		
		EditIcon.setOnMouseClicked(this::switchSceneToEdit);
    }
    
    
//    public void switchSceneToEdit(MouseEvent event) {
//    	Stage stageInfo = (Stage) EditIcon.getScene().getWindow();
//    	wpss.createWindowSwitchScene("/fxml/Edit.fxml", new EditController(), stageInfo);
//    	
//    }
    
    

    /*Wenn auf das Bearbeiten Bild geklickt wird, öffnet sich das Fenster zum Editieren des Songs auswählen*/
    public void switchSceneToEdit(MouseEvent event) {
    	String wysiwygContent = txtSongEdit.getText();
    	Song tmpSong = TextParser.parseText(wysiwygContent);
    	wp.createWindowNewStage("/fxml/Edit.fxml", "Editiere den Song", new EditController());
    }
    
    public void switchSceneToQuestionIcon(MouseEvent event) {
    	wp.createWindowNewStage("/fxml/HelpWindow.fxml", "Informationen zum Anwenden des Editors", new HelpWindowController());
    }
    
    private boolean songsEqual(Song songA, Song songB) {
    	return songA.toString().equals(songB.toString()) ? true : false;
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
    	menuEditPaste.disableProperty().bind(clipboardEmpty.not());
    }
    
    private void setFormatter() {
    	txtYear.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtTempo.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtDuration.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    	txtTextSize.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
    }
    
    private void setDataBind() {
    	txtTitle.textProperty().bindBidirectional(loadedSong.title);
    	txtSubtitle.textProperty().bindBidirectional(loadedSong.subtitle);
    	txtArtist.textProperty().bindBidirectional(loadedSong.artist);
    	txtComposer.textProperty().bindBidirectional(loadedSong.composer);
    	txtLyricist.textProperty().bindBidirectional(loadedSong.lyricist);
    	txtCopyright.textProperty().bindBidirectional(loadedSong.copyright);
    	txtAlbum.textProperty().bindBidirectional(loadedSong.album);
    	txtYear.textProperty().bindBidirectional(loadedSong.year, new NumberStringConverter());
    	txtKey.textProperty().bindBidirectional(loadedSong.key);
    	txtTime.textProperty().bindBidirectional(loadedSong.time);
    	txtTempo.textProperty().bindBidirectional(loadedSong.tempo, new NumberStringConverter());
    	txtDuration.textProperty().bindBidirectional(loadedSong.duration, new NumberStringConverter());
    	txtTextFont.textProperty().bindBidirectional(loadedSong.textfont);
    	txtTextSize.textProperty().bindBidirectional(loadedSong.textsize, new NumberStringConverter());
    	txtTextColour.textProperty().bindBidirectional(loadedSong.textcolour);
    	txtChordColour.textProperty().bindBidirectional(loadedSong.chordcolour);
    	cbIsFinished.selectedProperty().bindBidirectional(loadedSong.isFinished);
    	txtSongEdit.textProperty().bindBidirectional(loadedSong.contents);
    }
    
    @FXML
    private void onClickFileNew(ActionEvent event) {
    	if (!songsEqual(emptySong, loadedSong.toSong())) {
    		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
	    	alert.setTitle("Current project is modified");
	    	alert.setContentText("Save?");
	    	ButtonType yesButton = new ButtonType("Yes", ButtonData.YES);
	    	ButtonType noButton = new ButtonType("No", ButtonData.NO);
	    	ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
	    	alert.getButtonTypes().setAll(yesButton, noButton, cancelButton);
	    	alert.showAndWait().ifPresent(type -> {
	    	        if (type == yesButton) {
	    	        	//Write process
	    	        	loadedSong = new SongProperties(emptySong);
	    	    		setDataBind();
	    	        } else if (type == noButton) {
	    	        	loadedSong = new SongProperties(emptySong);
	    	    		setDataBind();
	    	        } else {

	    	        }
	    	});
    	}
    	filename = "";
    }
    
    @FXML
    private void onClickFileOpen(ActionEvent event) {
    	
    	FileChooser fileChooser = new FileChooser();
    	try {
    		File f = new File(UserData.getOpenPath());
        	if (f.exists() && f.isDirectory())
            	fileChooser.setInitialDirectory(new File(UserData.getOpenPath()));
    	} 
    	catch (Exception e) 
    	{
    		fileChooser.setInitialDirectory(null);
    	}
    	fileChooser.setTitle("Open ChordPro File");
    	fileChooser.getExtensionFilters().addAll(
    			new FileChooser.ExtensionFilter("ChordProFiles", "*.cho", "*.crd", "*.chopro", "*.chord", "*.pro"),
    			new FileChooser.ExtensionFilter("All Files", "*.*")
    	);
    	
    	File selectedFile = fileChooser.showOpenDialog(null);
    	
    	if (selectedFile != null) {
    		ChordProParser cpp = new ChordProParser();
    		tempSong = cpp.tryParseChordPro(selectedFile.getAbsolutePath());
    		loadedSong = new SongProperties(tempSong);
    		setDataBind();
    		UserData.setOpenPath(selectedFile.getParent());
    		filename = selectedFile.getAbsolutePath();
    	}
    }
    
    @FXML
    private void onClickFileSave(ActionEvent event) {
    	if (filename.isBlank()) {
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
        	filename = selectedFile.getAbsolutePath();
    	}

    	if (!filename.isBlank()) {
    		//Write process
    	}	
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
    	filename = selectedFile.getAbsolutePath();
    	
    	if (!filename.isBlank()) {
    		//Write process
    	}
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
    	
    }
    
	public void initialize(URL location, ResourceBundle resources) {
		
		loadedSong = new SongProperties(emptySong);
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
		
		ivHelp.setOnMouseClicked(this::switchSceneToQuestionIcon);
		
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
    
  

}
