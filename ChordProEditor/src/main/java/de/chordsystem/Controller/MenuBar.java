package de.chordsystem.Controller;

import javafx.scene.control.Menu;

public class MenuBar extends NewEditorController {

	
	 final Menu menu1 = new Menu("Datei");
	 final Menu menu2 = new Menu("");
	 final Menu menu3 = new Menu("Help");
	 
	 MenuBar menuBar = new MenuBar();
	 menuBar.getMenus().addAll(menu1, menu2, menu3);

}
