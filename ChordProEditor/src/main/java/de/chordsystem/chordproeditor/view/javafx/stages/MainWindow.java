package de.chordsystem.chordproeditor.view.javafx.stages;



import java.net.URL;

import de.chordsystem.chordproeditor.view.javafx.controllers.NewEditorController;
import javafx.application.Application;
//import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainWindow extends Application{
	private static Stage window;

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
		window.setResizable(false);
		window.setTitle("ChordPro Editor");
		//window.getIcons().add(new Image("/Pictures/icon.png"));
		window.show();
	}

	public Stage getWindow(){
		return window;
	}
	
	public static void main(String[] args){
		launch(args);
	}
}
