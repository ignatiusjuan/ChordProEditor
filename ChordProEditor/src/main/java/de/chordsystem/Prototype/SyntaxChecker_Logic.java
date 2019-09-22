package de.chordsystem.Prototype;

import java.util.ArrayList;

public class SyntaxChecker_Logic {
	
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
		liste = SyntaxChecker_WordList.forChorus();
		if(liste.contains(command[0])) {
			commandGues = "Chorus";
		}
		liste =  SyntaxChecker_WordList.forGrid();
		if(liste.contains(command[0])) {
			commandGues = "Grid";
		}
		liste =  SyntaxChecker_WordList.forTab();
		if(liste.contains(command[0])) {
			commandGues = "Tab";
		}
		liste =  SyntaxChecker_WordList.forComment();
		if(liste.contains(command[0])) {
			commandGues = "Comment";
		}
		return commandGues;
	}

}
