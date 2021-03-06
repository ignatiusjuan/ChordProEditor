package de.chordsystem.chordproeditor.view.javafx.stages;



import java.net.URL;
import java.util.ResourceBundle;

import de.chordsystem.chordproeditor.userdata.UserData;
import de.chordsystem.chordproeditor.view.javafx.controllers.HelpWindowController;
import de.chordsystem.chordproeditor.view.javafx.controllers.NewEditorController;
import de.chordsystem.chordproeditor.view.javafx.helperclasses.WindowPresetSwitchStage;
import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainWindow extends Application implements Initializable{
	private static Stage window;
	private WindowPresetSwitchStage wp = new WindowPresetSwitchStage();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		final URL fxmlUrl = getClass().getResource("/fxml/NewEditor.fxml");
		final FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
		fxmlLoader.setController(new NewEditorController());
		final Parent root = fxmlLoader.load();
		root.setCache(true);
		root.setCacheHint(CacheHint.SPEED);
		Scene scene = new Scene(root); 
		window.setScene(scene);
		window.setResizable(true);
		window.setTitle("ChordPro Editor");
		window.getIcons().add(new Image("/Icons/icon 512x512.png/"));
		window.show();
		
		if (UserData.getShowHelpOnStart())
			wp.createWindowNewStage("/fxml/HelpWindow.fxml", "Informationen zum Anwenden des Editors", new HelpWindowController(), window, new Image("/Icons/icon 512x512.png/"));
	}

	public Stage getWindow(){
		return window;
	}
	
	public static void main(String[] args){
		launch(args); 
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
