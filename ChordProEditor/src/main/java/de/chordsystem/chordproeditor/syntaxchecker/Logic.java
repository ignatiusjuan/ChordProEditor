package de.chordsystem.chordproeditor.syntaxchecker;

import java.util.ArrayList;

public class Logic {
	
	/***
	 * scanns the text line by line for faults
	 * @param text
	 * @return Array of FoundFaults
	 */
	public static FoundFault[] checkText(String text){
		int countFehler = 0;
		FoundFault [] fehlerArray = new FoundFault[1];//Muss dynamisch erweitert werden
		String[] zeilen = text.split("\\n");
		for(int i = 0; i < zeilen.length; i++) {
			if(fehlerArray.length < countFehler) {//Array erweitern
				FoundFault [] tmp = new FoundFault[fehlerArray.length+1];
				System.arraycopy(fehlerArray, 0, tmp, 0, tmp.length);
				fehlerArray = tmp;
			}
			FoundFault fehler = getFaultText(zeilen[i], i);
			if(fehler.verbesserungsVorschlag != null) {
				fehlerArray[i] = fehler;
				countFehler ++;
			}
			
		}
		return fehlerArray;
	}

	private static FoundFault getFaultText(String zeile, int zeilennummer) {
		FoundFault fehler = new FoundFault();
		String[] command = zeile.split(":");
		command[0] = command[0].trim();
		String commandGues = "";
		ArrayList<String> liste = new ArrayList<String>();
		liste = WordList.forChorusText();
		if(liste.contains(command[0])) {
			commandGues = "Chorus";
			fehler.setStart(0);
			fehler.setEnde(5);
		}
		liste =  WordList.forGridText();
		if(liste.contains(command[0])) {
			commandGues = "Grid";
			fehler.setStart(0);
			fehler.setEnde(3);
		}
		liste =  WordList.forTabText();
		if(liste.contains(command[0])) {
			commandGues = "Tab";
			fehler.setStart(0);
			fehler.setEnde(2);
		}
		liste =  WordList.forCommentText();
		if(liste.contains(command[0])) {
			commandGues = "Comment";
			fehler.setStart(0);
			fehler.setEnde(1);
		}
		fehler.setVorschlag(commandGues);
		return fehler;
	}

}
