package de.chordsystem.chordproeditor.syntaxchecker;

import java.util.ArrayList;

public class Logic {
	
	public static int[][] checkText(String text){
		int countFehler = 0;
		int [][] fehler = new int[1][3];//Muss dynamisch erweitert werden
		String[] zeilen = text.split("\\n");
		for(int i = 0; i < zeilen.length; i++) {
			String start = getStart(zeilen[i]);
			if(fehler.length < countFehler) {//Array erweitern
				int [][] tmp = new int[fehler.length+1][3];
				System.arraycopy(fehler, 0, tmp, 0, tmp.length);
				fehler = tmp;
			}
			
		}
		return fehler;
	}

	private static String getStart(String zeile) {
		String[] command = zeile.split(":");
		String commandGues = "";
		ArrayList<String> liste = new ArrayList<String>();
		liste = WordList.forChorus();
		if(liste.contains(command[0])) {
			commandGues = "Chorus";
		}
		liste =  WordList.forGrid();
		if(liste.contains(command[0])) {
			commandGues = "Grid";
		}
		liste =  WordList.forTab();
		if(liste.contains(command[0])) {
			commandGues = "Tab";
		}
		liste =  WordList.forComment();
		if(liste.contains(command[0])) {
			commandGues = "Comment";
		}
		return commandGues;
	}

}
