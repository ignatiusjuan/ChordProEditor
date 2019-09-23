package de.chordsystem.chordproeditor.view.javafx.controllers;

import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;
import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchScene;

import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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

import de.chordsystem.Prototype.ChordProConverter;
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
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
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
import javafx.scene.image.*;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
    private JFXTextField txtCapo;
    
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
		menuFileQuit.setOnAction(this::onClickFileQuit);
		
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
			
			GeneratePDF.generatePDF(tempSong);
		});
		lblSaveAsPDF.setOnMouseClicked((event) -> {
			//GeneratePDF.generatePDF(tempSong);
		});
		
		hamburger.setOnMouseClicked(this::onClickHamburger);
		
		EditIcon.setOnMouseClicked(this::switchSceneToEdit);
    }

    /*Wenn auf das Bearbeiten Bild geklickt wird, öffnet sich das Fenster zum Editieren des Songs auswählen*/
    public void switchSceneToEdit(MouseEvent event) {
    	Song tmpSong = loadedSong.toSong(txtSongEdit.getText());
    	tmpSong.setYear(Integer.parseInt(txtYear.getText()));
    	tmpSong.setTempo(Integer.parseInt(txtTempo.getText()));
    	tmpSong.setDuration(Integer.parseInt(txtDuration.getText()));
    	tmpSong.setCapo(Integer.parseInt(txtCapo.getText()));
    	tmpSong.setTextsize(Integer.parseInt(txtTextSize.getText()));
    	
    	
    	EditController editController = new EditController();
    	try{
    		final FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/fxml/Edit.fxml"));
            fxmlLoader.setController(editController);
            editController.receiveEditText(ChordProConverter.tryConvertToChordPro(tmpSong));
            editController.setNewEditorController(this);
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load());
            stage.setResizable(false);
            stage.initOwner(lblDateTime.getScene().getWindow());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.setTitle("Editiere den Song");
            stage.getIcons().add(new Image("/Icons/icon 512x512.png/"));
            stage.show();

        }catch(IOException io){
            io.printStackTrace();
        }
    }
    
    /* Diese Methode oeffnet das Fenster zu Hilfestellung des Programms */
    public void switchSceneToQuestionIcon(MouseEvent event) {
    	wp.createWindowNewStage("/fxml/HelpWindow.fxml", "Informationen zum Anwenden des Editors", new HelpWindowController(), lblDateTime.getScene().getWindow(), new Image("/Icons/icon 512x512.png/"));
    }
    
    private boolean songsEqual(Song songA, Song songB) {
    	return songA.toString().equals(songB.toString()) ? true : false;
    }
    
    /*Diese Methode dient dazu um die Shortcuts für die in der MenuBar angebenen Aktionen auszufuehren */
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
    
    /*Einige hilfen zum Editieren des Songs Undo, Redo, Cut, Copy, Paste*/
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
    	txtCapo.setTextFormatter(new TextFormatter<>(new IntegerStringConverter()));
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
    	txtYear.textProperty().set(loadedSong.year.getValue().toString());
    	txtKey.textProperty().bindBidirectional(loadedSong.key);
    	txtTime.textProperty().bindBidirectional(loadedSong.time);
    	txtTempo.textProperty().set(loadedSong.tempo.getValue().toString());
    	txtDuration.textProperty().set(loadedSong.duration.getValue().toString());
    	txtCapo.textProperty().set(loadedSong.capo.getValue().toString());
    	txtTextFont.textProperty().bindBidirectional(loadedSong.textfont);
    	txtTextSize.textProperty().set(loadedSong.textsize.getValue().toString());
    	txtTextColour.textProperty().bindBidirectional(loadedSong.textcolour);
    	txtChordColour.textProperty().bindBidirectional(loadedSong.chordcolour);
    	cbIsFinished.selectedProperty().bindBidirectional(loadedSong.isFinished);
    	txtSongEdit.textProperty().bindBidirectional(loadedSong.contents);
    }
    
    /*Beim anklicken von Open wird eine neue Datei erstellt*/
    @FXML
    private void onClickFileNew(ActionEvent event) {
    	Song tmpSong = loadedSong.toSong(txtSongEdit.getText());
    	tmpSong.setYear(Integer.parseInt(txtYear.getText()));
    	tmpSong.setTempo(Integer.parseInt(txtTempo.getText()));
    	tmpSong.setDuration(Integer.parseInt(txtDuration.getText()));
    	tmpSong.setCapo(Integer.parseInt(txtCapo.getText()));
    	tmpSong.setTextsize(Integer.parseInt(txtTextSize.getText()));
    	if (!songsEqual(emptySong, tmpSong)) {
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
    
    /*Methode zum offnen eines Songs im WYSIWYG (What You See Is What You Get) modus im TextArea des Programms*/
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
    /*Hier wird beim anklicken des Saves in der MenuBar, die Datei in der Form gespeichert in der Sie geoeffnet ist*/
    @FXML
    private void onClickFileSave(ActionEvent event) {
    	if (filename.isBlank()) {
    		onClickFileSaveAs(event);
    	} else {
    		try {
        		PrintWriter out = new PrintWriter(filename);
        		Song tmpSong = loadedSong.toSong(txtSongEdit.getText());
            	tmpSong.setYear(Integer.parseInt(txtYear.getText()));
            	tmpSong.setTempo(Integer.parseInt(txtTempo.getText()));
            	tmpSong.setDuration(Integer.parseInt(txtDuration.getText()));
            	tmpSong.setCapo(Integer.parseInt(txtCapo.getText()));
            	tmpSong.setTextsize(Integer.parseInt(txtTextSize.getText()));
        		out.println(ChordProConverter.tryConvertToChordPro(tmpSong));
        		out.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
    	}	
    }
    /*Diese Methode dient zur Speicherung der Datei in einer der vorgegebenen Arten*/
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
    		try {
        		PrintWriter out = new PrintWriter(filename);
        		Song tmpSong = loadedSong.toSong(txtSongEdit.getText());
            	tmpSong.setYear(Integer.parseInt(txtYear.getText()));
            	tmpSong.setTempo(Integer.parseInt(txtTempo.getText()));
            	tmpSong.setDuration(Integer.parseInt(txtDuration.getText()));
            	tmpSong.setCapo(Integer.parseInt(txtCapo.getText()));
            	tmpSong.setTextsize(Integer.parseInt(txtTextSize.getText()));
        		out.println(ChordProConverter.tryConvertToChordPro(tmpSong));
        		out.close();
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
    	}	
    }
    
    @FXML
    private void onClickFileQuit(ActionEvent event) {
    	Platform.exit();
        System.exit(0);
    }
    
    private void setSidePaneBind() {
    	lblProperties.visibleProperty().bind(hideSidePane.not());
    	scrollpaneProperties.visibleProperty().bind(hideSidePane.not());
    	scrollpaneProperties.disableProperty().bind(hideSidePane);
    }
    
    /*Methode zum benutzen des Hamburger Buttons im Fenster*/
    @FXML
    private void onClickHamburger(MouseEvent event) {
    	if (!hideSidePane.get()) {
    		AnchorPane.setLeftAnchor(txtSongEdit, 70.0);
    	} else {
    		AnchorPane.setLeftAnchor(txtSongEdit, 265.0);
    	}
    	hideSidePane.set(!hideSidePane.get());
    	
    }
    
    public void updateSong(Song song) {
    	loadedSong = new SongProperties(song);
		setDataBind();
    }
    
    /*Hier werden die anklickbaren Button ihren jeweiligen Methoden zugewiesen*/
	public void initialize(URL location, ResourceBundle resources) {
		
		loadedSong = new SongProperties(emptySong);
		setSidePaneBind();
		setShortcut();
		setOnAction();
		setMenuBind();
		setDataBind();
		setFormatter();
		showTime();
		
		txtSongEdit.setFont(Font.font("monospaced",FontWeight.NORMAL,14));
		
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
