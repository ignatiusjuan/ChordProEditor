/**
 * 
 */
package de.chordsystem.chordproeditor.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chordsystem.chordproeditor.model.classes.EnvironmentImpl;
import de.chordsystem.chordproeditor.model.interfaces.Environment;
import de.chordsystem.chordproeditor.model.interfaces.Song;

/**
 * @author shineglurak
 * @author ignatiusjuan
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
	
	private static final String regexChorus		= "^\\s*Chorus\\s*:\\s*(.*?)\\s*$";
	private static final String regexVerse		= "^\\s*Verse\\s*:\\s*(.*?)\\s*$";
	private static final String regexGrid		= "^\\s*Grid\\s*:\\s*(.*?)\\s*$";
	private static final String regexTab		= "^\\s*Tab\\s*:\\s*(.*?)\\s*$";
	private static final String regexComment	= "^\\s*(#|//)\\s*(.*?)\\s*$";
	
	private static final String regexEmpty		= "^\\s*$";
	
	public static Song parseText(Song song, String text) {
		int lastType = TYPE_NULL;
		int currType = TYPE_OTHER;
		String lastName = "";
		String currName = "";
		String[] lines = text.split("\\n");
		
		Environment env;
		
		for(int i = 0; i < lines.length; i++) {
			if (Pattern.compile(regexEmpty).matcher(lines[i]).find()) {
				if (lastType != TYPE_OTHER) {
					currType = TYPE_OTHER;
					currName = "";
					env = new EnvironmentImpl();
					env.setType(TYPE_OTHER);
					env.setTitle("");
					song.addEnvironment(env);
					env = null;
				}
			} else if (Pattern.compile(regexChorus).matcher(lines[i]).find()) {
				Matcher m = Pattern.compile(regexChorus).matcher(lines[i]);
				m.find();
				if (lastType != TYPE_CHORUS || !lastName.equals(m.group(1))) {
					currType = TYPE_CHORUS;
					currName = m.group(1);
				}
			} else if (Pattern.compile(regexVerse).matcher(lines[i]).find()) {
				Matcher m = Pattern.compile(regexVerse).matcher(lines[i]);
				m.find();
				if (lastType != TYPE_VERSE || !lastName.equals(m.group(1))) {
					currType = TYPE_VERSE;
					currName = m.group(1);
				}
			} else if (Pattern.compile(regexGrid).matcher(lines[i]).find()) {
				Matcher m = Pattern.compile(regexGrid).matcher(lines[i]);
				m.find();
				if (lastType != TYPE_GRID || !lastName.equals(m.group(1))) {
					currType = TYPE_GRID;
					currName = m.group(1);
				}
				while((i+1) < lines.length && !Pattern.compile(regexEmpty).matcher(lines[i+1]).find()) {
					if (Pattern.compile(regexComment).matcher(lines[i]).find()) {
						env = new EnvironmentImpl();
						lines[i] = lines[i].replace("//", "");
						lines[i] = lines[i].replace("#", "");
						
						env.setType(TYPE_COMMENT);
						env.setLyric(lines[i]);
						song.addEnvironment(env);
						env = null;
					} else {
						env = new EnvironmentImpl();
						env.setTitle(currName);
						env.setType(currType);
						env.setLyric(lines[i+1]);
						song.addEnvironment(env);
						env = null;
						i++;
					}
				} 
			} else if (Pattern.compile(regexTab).matcher(lines[i]).find()) {
				Matcher m = Pattern.compile(regexTab).matcher(lines[i]);
				m.find();
				if (lastType != TYPE_TAB || !lastName.equals(m.group(1))) {
					currType = TYPE_TAB;
					currName = m.group(1);
				}
				while((i+1) < lines.length && !Pattern.compile(regexEmpty).matcher(lines[i+1]).find()) {
					if (Pattern.compile(regexComment).matcher(lines[i]).find()) {
						env = new EnvironmentImpl();
						lines[i] = lines[i].replace("//", "");
						lines[i] = lines[i].replace("#", "");
						
						env.setType(TYPE_COMMENT);
						env.setLyric(lines[i]);
						song.addEnvironment(env);
						env = null;
					} else {
						env = new EnvironmentImpl();
						env.setTitle(currName);
						env.setType(currType);
						env.setLyric(lines[i+1]);
						song.addEnvironment(env);
						env = null;
						i++;
					}
				} 
			} else if (Pattern.compile(regexComment).matcher(lines[i]).find()) {
				env = new EnvironmentImpl();
				lines[i] = lines[i].replace("//", "");
				lines[i] = lines[i].replace("#", "");
				
				env.setType(TYPE_COMMENT);
				env.setLyric(lines[i]);
				song.addEnvironment(env);
				env = null;
			} else {
				if (isChordRow(lines[i])) {
					if (lastType == TYPE_OTHER) {
						currType = TYPE_VERSE;
						currName = "";
					}
					env = new EnvironmentImpl();
					env.setType(currType);
					env.setTitle(currName);
					env.setChord(lines[i]);
					if((i+1) < lines.length && !isChordRow(lines[i+1])) {
						env.setLyric(lines[i+1]);
						i++;
					} 
					song.addEnvironment(env);
					env = null;
				} else {
					env = new EnvironmentImpl();
					env.setType(TYPE_VERSE);
					env.setTitle("");
					env.setLyric(lines[i]);
					song.addEnvironment(env);
					env = null;
				}
			}
			lastType = currType;
			lastName = currName;
		}
		return song;
	}
	
	private static boolean isChordRow(String row) {
		String[] words = row.split(" ");
		for(int i = 0; i < words.length; i++) {
			String trimmed = words[i].trim();
			if(!trimmed.isEmpty() && ChordChecker.isAChord(trimmed)) {
				return true;
			}
		}
		return false;
	}

}
