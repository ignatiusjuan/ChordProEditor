/**
 * 
 */
package de.chordsystem.Prototype;

import de.chordsystem.chordproeditor.model.classes.EnvironmentImpl;
import de.chordsystem.chordproeditor.model.classes.SongImpl;
import de.chordsystem.chordproeditor.model.interfaces.Environment;
import de.chordsystem.chordproeditor.model.interfaces.Song;
import javafx.scene.control.TextArea;

/**
 * @author shineglurak
 *
 */
public class TextParser {
	
	public static final int TYPE_NULL = 0;
	public static final int TYPE_CHORUS = 1;
	public static final int TYPE_VERSE = 2;
	public static final int TYPE_TAB = 3;
	public static final int TYPE_GRID = 4;
	public static final int TYPE_INSTRUCTION = 5;
	public static final int TYPE_COMMENT = 6;
	public static final int TYPE_OTHER = 7;
	
	public static Song parseText(String text) {
		int lastType = TYPE_NULL;
		String command = "none";
		Song song = new SongImpl();
		String[] zeilen = text.split("\\n");
		for(int i = 0; i < zeilen.length; i++) {
			Environment env = new EnvironmentImpl();
			if(getCommand(zeilen[i])!="none") {
				command = getCommand(zeilen[i]);
			}
			switch(command) {
			case "#":
				zeilen[i] = zeilen[i].replace("#", "");
				env.setType(TYPE_COMMENT);
				env.setLyric(zeilen[i]);
				song.addEnvironment(env);
				break;
			case "<":
				env.setType(TYPE_OTHER);
				env.setLyric(zeilen[i]);
				song.addEnvironment(env);
				break;
			case "//":
				zeilen[i] = zeilen[i].replace("//", "");
				env.setType(TYPE_COMMENT);
				env.setLyric(zeilen[i]);
				song.addEnvironment(env);
				break;
			case "Chorus":
				zeilen[i] = zeilen[i].replace("Chorus:", "");
				env.setType(TYPE_CHORUS);
				String[] teil = zeilen[i].split(":");
				env.setTitle(teil[0]);
				song.addEnvironment(env);
				break;
			case "Tab":
				zeilen[i] = zeilen[i].replace("Tab:", "");
				env.setType(TYPE_TAB);
				if(isChordRow(zeilen[i])) {
					env.setChord(zeilen[i]);
				}else {
					env.setLyric(zeilen[i]);
				}
				song.addEnvironment(env);
				break;
			case "Grid":
				zeilen[i] = zeilen[i].replace("Grid:", "");
				env.setType(TYPE_GRID);
				if(isChordRow(zeilen[i])) {
					env.setChord(zeilen[i]);
				}else {
					env.setLyric(zeilen[i]);
				}
				song.addEnvironment(env);
				break;
			case "Verse":
				env.setType(TYPE_VERSE);
				if(isChordRow(zeilen[i])) {
					env.setChord(zeilen[i]);
					System.out.println("Chord: "+zeilen[i]);
					if((i+1) < zeilen.length && !isChordRow(zeilen[i+1])) {
						env.setLyric(zeilen[i+1]);
						i++;
						System.out.println("Lyric after Chord: "+zeilen[i]);
					}
				}else{
					env.setLyric(zeilen[i]);
					System.out.println("Lyric only: "+zeilen[i]);
				}
				song.addEnvironment(env);
				System.out.println();
				break;
			default:
				break;
			}
//			System.out.println("Zeile:"+zeilen[i]);
//			System.out.println("Kommando:"+command);
		}
		return song;
	}
	
	private static boolean isChordRow(String row) {
		Boolean isChord = false;
		String[] words = row.split(" ");
		for(int i = 0; i < words.length; i++) {
			String trimmed = words[i].trim();
//			System.out.println("Word: "+trimmed);
			if(!trimmed.isEmpty() && ChordChecker.isAChord(trimmed)) {
				isChord = true;
			}
			if(!trimmed.isEmpty() && !ChordChecker.isAChord(trimmed)) {
				isChord = false;
			}
//			System.out.println("Ist Chord: "+isChord);
//			System.out.println();
//			if(words[i] != null && words[i] != "") {
//				System.out.println("Wort: "+words[i]);
//				System.out.println((int)words[i].charAt(0)+" erste Stelle");
//			}
		}
		return isChord;
	}
	
	private static String getCommand(String string) {
		String[] command = string.split(":");
//		System.out.println(command[0]);
		switch(command[0]) {
		case "Chorus":
			return "Chorus";
		case "Tab":
			return "Tab";
		case "Grid":
			return "Grid";
		default:
			if(command[0].startsWith("#")) {
				return "#";
			}
			if(command[0].startsWith("<")) {
				return "<";
			}
			if(command[0].startsWith("//")) {
				return "//";
			}
			return "Verse";
		}
	}

}
