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
		Environment env = new EnvironmentImpl();
		String[] zeilen = text.split("\\n");
		for(int i = 0; i < zeilen.length; i++) {
			if(getCommand(zeilen[i])!="none") {
				command = getCommand(zeilen[i]);
			}
			switch(command) {
			case "#":
				env.setType(TYPE_COMMENT);
				env.setLyric(zeilen[i]);
				song.addEnvironment(env);
				break;
			case "<":
				env.setType(TYPE_OTHER);
				env.setLyric(zeilen[i]);
				song.addEnvironment(env);
				break;
			case "\\\\":
				env.setType(TYPE_COMMENT);
				env.setLyric(zeilen[i]);
				song.addEnvironment(env);
				break;
			case "Chorus":
				env.setType(TYPE_CHORUS);
				if(lastType != TYPE_CHORUS){
					String[] teil = zeilen[i].split(":");
					env.setTitle(teil[0]);
				}else{
					if(isChordRow(zeilen[i])) {
						env.setChord(zeilen[i]);
						if(isChordRow(zeilen[i+1])) {
							env.setChord(zeilen[i+1]);
							i++;
						}
					}else{
						env.setLyric(zeilen[i]);
					}
				}
				song.addEnvironment(env);
				break;
			case "Tab":
				env.setType(TYPE_TAB);
				if(isChordRow(zeilen[i])) {
					env.setChord(zeilen[i]);
				}else {
					env.setLyric(zeilen[i]);
				}
				song.addEnvironment(env);
				break;
			case "Grid":
				env.setType(TYPE_GRID);
				if(isChordRow(zeilen[i])) {
					env.setChord(zeilen[i]);
				}else {
					env.setLyric(zeilen[i]);
				}
				song.addEnvironment(env);
				break;
			default:
				break;
			}
		}
		return song;
	}
	
	private static boolean isChordRow(String row) {
		Boolean isChord = true;
		String[] words = row.split(" ");
		for(int i = 0; i < words.length; i++) {
			if(ChordChecker.isAChord(words[i])) {
			}else{
				isChord = false;
			}
		}
		return isChord;
	}
	
	private static String getCommand(String string) {
		String[] command = string.split(":");
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
			if(command[0].startsWith("\\\\")) {
				return "\\\\";
			}
			String[] words = string.split(" ");
			for(int i = 0; i < words.length; i++) {
				if(ChordChecker.isAChord(words[i])) {
					return "Chorus";
				}
			}
			return "none";
		}
	}

}
