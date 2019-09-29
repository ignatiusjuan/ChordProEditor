package de.chordsystem.chordproeditor.syntaxchecker;

import java.util.ArrayList;
import java.util.List;

import de.chordsystem.chordproeditor.parser.ChordProParser;

public class Logic {
	
	/***
	 * Builds an array for the FoundFaults and gives the lines to the getFaultChordPro 
	 * @return Array of FoundFaults
	 */
	public static FoundFault[] checkChordPro() {
		List<Integer> fehlerZeilen = ChordProParser.getErrorLines();
		List<String> fehlerStrings = ChordProParser.getErrorStrings();
		FoundFault[] fehlerListe = new FoundFault[fehlerZeilen.size()];
		for(int i = 0; i < fehlerZeilen.size(); i++) {
			FoundFault fehler = getFaultChordPro(fehlerZeilen.get(i), fehlerStrings.get(i));
			fehlerListe[i] = fehler;
		}
		return fehlerListe;
	}
	
	/***
	 * Checks the given lines of faults
	 * @param zeile int
	 * @param line String
	 * @return FoundFault
	 */
	private static FoundFault getFaultChordPro(int zeile, String line) {
		FoundFault fehler = new FoundFault();
		fehler.setZeile(zeile);
		int klammerZu = line.length()+1;
		int problemKlammer = line.length()+1;
		for(int i = 0; i < line.length(); i++) {//Zuerst Klammern überprüfen
			switch(line.charAt(i)) {
			case '[':
				klammerZu = sucheKlammerZu(line, i+1, ']');//i+1 weil soll nach gefundener Klammer suchen
				if(klammerZu < line.length()+1) {
					i = klammerZu;
				}else {
					problemKlammer = i;
				}
				break;
			case '{':
				klammerZu = sucheKlammerZu(line, i+1, '}');
				if(klammerZu < line.length()) {
					i = klammerZu;
				}else {
					problemKlammer = i;
				}
				break;
			default:
				break;
			}
		}
		if(problemKlammer == line.length()+1) {
			fehler = checkForMisspelling(zeile, line);
		}else {
			fehler.setStart(problemKlammer);
			fehler.setEnde(problemKlammer);
			fehler.setVorschlag("Please check brackets");
		}
		return fehler;
	}
	
	private static FoundFault checkForMisspelling(int zeile, String line) {
		FoundFault fehler = new FoundFault();
		fehler.setZeile(zeile);
		String tmp;
		String tmpTry;
		for(int i = 0; i < line.length(); i++) {
			switch(line.charAt(i)) {
			case '{':
				i++;
				tmp = line.substring(i);
				ArrayList<String> liste = new ArrayList<String>();
				//Chorus
				liste = WordList.forChorusText();
				for(int j = 0; j < liste.size(); j++) {
					tmpTry = liste.get(j);
					if(tmp.startsWith(tmpTry)) {
						fehler.setStart(i);
						fehler.setEnde(i+5);
						fehler.setVorschlag("Chorus");
					}
				}
				//Tab
				liste = WordList.forTabText();
				for(int j = 0; j < liste.size(); j++) {
					tmpTry = liste.get(j);
					if(tmp.startsWith(tmpTry)) {
						fehler.setStart(i);
						fehler.setEnde(i+2);
						fehler.setVorschlag("Tab");
					}
				}
				//Grid
				liste = WordList.forGridText();
				for(int j = 0; j < liste.size(); j++) {
					tmpTry = liste.get(j);
					if(tmp.startsWith(tmpTry)) {
						fehler.setStart(i);
						fehler.setEnde(i+3);
						fehler.setVorschlag("Grid");
					}
				}
				//Title
				liste = WordList.forTitle();
				for(int j = 0; j < liste.size(); j++) {
					tmpTry = liste.get(j);
					if(tmp.startsWith(tmpTry)) {
						fehler.setStart(i);
						fehler.setEnde(i+3);
						fehler.setVorschlag("title");
					}
				}
				//TODO usw.
			}
		}
		return fehler;
	}

	private static int sucheKlammerZu(String line, int place, char klammerArt) {
		switch(klammerArt) { //Fehler = line.length()+1; Richtig = zahl kleiner
		case '}':
			for (int i = place; i < line.length(); i++) {
				if(line.charAt(i) == '{') {
					return line.length()+1;
				}
				if(line.charAt(i) == '}') {
					return i;
				}
			}
			break;
		case ']':
			for (int i = place; i < line.length(); i++) {
				if(line.charAt(i) == '[') {
					return line.length()+1;
				}
				if(line.charAt(i) == ']') {
					return i;
				}
			}
			break;
		default:
			break;
		}
		return line.length()+1;
	}
	
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
			if(fehler.getVorschlag() != null) {
				fehlerArray[i] = fehler;
				countFehler ++;
			}
			
		}
		return fehlerArray;
	}

	/**
	 * Tries to find the fault of the line an gives an proposal for correction
	 * @param zeile
	 * @param zeilennummer
	 * @return FoundFault
	 */
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
