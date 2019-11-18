package de.chordsystem.latex;
import de.chordsystem.chordproeditor.model.classes.EnvironmentImpl;
import de.chordsystem.chordproeditor.model.interfaces.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/***
 * @author Anne-Kathrin Haag
 * Creates the .tex document, with the information in the song given
 * 
 */
public class WriteTex {
	/***
	 * Writes the head of the .tex document and calls the writers for the environmentes
	 * @param song
	 * @throws IOException
	 */
	public static void writeTex(String filepath,String filename,Song song) throws IOException
	{
		FileWriter tex = new FileWriter(filepath + "\\" + filename+".tex");
		BufferedWriter bw = new BufferedWriter(tex);
		bw.write("\\documentclass{article}");
		bw.newLine();
		bw.write("\\usepackage{courier}");
		bw.newLine();
		bw.write("\\voffset 0cm");
		bw.newLine();
		bw.write("\\hoffset 0cm");
		bw.newLine();
		bw.write("\\topmargin -30mm");
		bw.newLine();
		bw.write("\\textwidth 18cm");
		bw.newLine();
		bw.write("\\textheight 27cm");
		bw.newLine();
		bw.write("\\oddsidemargin -6mm \\evensidemargin -6mm");
		bw.newLine();
		bw.write("\\setlength{\\parindent}{0em}");
		bw.newLine();
		bw.write("\\hbadness=99999");
		bw.newLine();
		String tmp = prepareString(song.getTitle());
		bw.write("\\title{"+tmp+"}");
		bw.newLine();
		bw.write("\\author{");
		for(int i = 0; i < song.getArtist().size(); i++) {
			tmp = prepareString(song.getArtist().get(i));
			bw.write(tmp);
		}
		if(song.getArtist().size() == 0) {
			bw.write("no Author");
		}
		bw.write("}");
		bw.newLine();
		if(song.getYear() > 1800) {
			bw.write("\\date{"+song.getYear()+"}");
			bw.newLine();
		}else {
			bw.write("\\date{\\today}");
			bw.newLine();
		}
		bw.write("\\begin{document}");
		bw.newLine();
		bw.write("\\maketitle");
		bw.newLine();
		bw.write("\\texttt{");
		bw.newLine();
		writeLyrics(song, bw);
		String temp = prepareString(song.getCopyright().toString());
		bw.write(temp);
		bw.newLine();
		bw.write("}");
		bw.newLine();
		bw.write("\\end{document}");
		bw.newLine();
		bw.close();
	}
	
	/***
	 * Decides which method has to be called to write the current environment 
	 * @return
	 * @throws IOException
	 * @param song
	 * @param bw
	 * @throws IOException
	 */
	private static void writeLyrics(Song song, BufferedWriter bw) throws IOException
	{
		boolean newEnv = true;
		for (int currentEnv = 0; currentEnv < song.getEnvironmentSize(); currentEnv++) {
			if(currentEnv-1 >= 0 &&
				song.getEnvironment(currentEnv).getTitle()==song.getEnvironment(currentEnv-1).getTitle()&&
				song.getEnvironment(currentEnv).getType()==song.getEnvironment(currentEnv-1).getType()) {
				newEnv = false;
			}else {
				newEnv = true;
			}
			switch(song.getEnvironment(currentEnv).getType()) {
				case EnvironmentImpl.TYPE_CHORUS:
					writeChorus(song, currentEnv, bw, newEnv);
					break;
				case EnvironmentImpl.TYPE_VERSE:
					writeVerse(song, currentEnv, bw, newEnv);
					break;
				case EnvironmentImpl.TYPE_TAB:
					writeTab(song, currentEnv, bw, newEnv);
					break;
				case EnvironmentImpl.TYPE_GRID:
					writeGrid(song, currentEnv, bw, newEnv);
					break;
			
				case EnvironmentImpl.TYPE_INSTRUCTION:
					writeInstruction(song, currentEnv, bw, newEnv);
					break;
				case EnvironmentImpl.TYPE_COMMENT:
					writeComment(song, currentEnv, bw, newEnv);
					break;
				case EnvironmentImpl.TYPE_OTHER:
					writeOther(song, currentEnv, bw, newEnv);
					break;
				case EnvironmentImpl.TYPE_NULL:
					writeNull(song, currentEnv, bw, newEnv);
					break;
			}
		}
	}

	/***
	 * Writes the chorus of the current environment in the song
	 * @param song current Song
	 * @param env curent Environment
	 * @param bw Writer
	 * @param newEnv if an new Environment startet
	 * @throws IOException
	 */
	private static void writeChorus(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException
	{
		String tooLong = "no";
		int breakPointChord = 0;
		String tmpChord = "";
		int breakPointLyric = 0;
		String tmpLyric = "";
		if(newEnv == true) {
			bw.write("\\textbf{Chorus:"+song.getEnvironment(env).getTitle()+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = song.getEnvironment(env).getChord();
			
			breakPointChord = isTooLong(text);
			if(breakPointChord > 0) {
				tooLong = "chord";
				tmpChord = text.substring(breakPointChord);
				text = text.substring(0,breakPointChord);
			}
			
			text = prepareString(text);
			bw.write("\\textbf{ "+text+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = song.getEnvironment(env).getLyric();
			
			breakPointLyric = isTooLong(text);
			if(breakPointLyric > 0) {
				if(tooLong == "chord") {
					tooLong = "both";
				}else {
					tooLong = "lyric";
				}
				tmpLyric = text.substring(breakPointLyric);
				text = text.substring(0,breakPointLyric);
			}
			
			text = prepareString(text);
			bw.write(text+"\\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		switch(tooLong) {
		case "both":
			tmpChord = prepareString(tmpChord);
			tmpLyric = prepareString(tmpLyric);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "chord":
			tmpChord = prepareString(tmpChord);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "lyric":
			tmpLyric = prepareString(tmpLyric);
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "no":
			break;
		}
	}
	
	/***
	 * Writes the verse of the current environment in the song
	 * @param song current Song
	 * @param env curent Environment
	 * @param bw Writer
	 * @param newEnv if an new Environment startet
	 * @throws IOException
	 */
	private static void writeVerse(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException{
		String tooLong = "no";
		int breakPointChord = 0;
		String tmpChord = "";
		int breakPointLyric = 0;
		String tmpLyric = "";
		if(newEnv == true) {
			bw.write("\\textbf{Verse:"+song.getEnvironment(env).getTitle()+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = song.getEnvironment(env).getChord();
			
			breakPointChord = isTooLong(text);
			if(breakPointChord > 0) {
				tooLong = "chord";
				tmpChord = text.substring(breakPointChord);
				text = text.substring(0,breakPointChord);
			}
			
			text = prepareString(text);
			bw.write("\\textbf{ "+text+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = song.getEnvironment(env).getLyric();
			
			breakPointLyric = isTooLong(text);
			if(breakPointLyric > 0) {
				if(tooLong == "chord") {
					tooLong = "both";
				}else {
					tooLong = "lyric";
				}
				tmpLyric = text.substring(breakPointLyric);
				text = text.substring(0,breakPointLyric);
			}
			
			text = prepareString(text);
			bw.write(text+"\\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		switch(tooLong) {
		case "both":
			tmpChord = prepareString(tmpChord);
			tmpLyric = prepareString(tmpLyric);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "chord":
			tmpChord = prepareString(tmpChord);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "lyric":
			tmpLyric = prepareString(tmpLyric);
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "no":
			break;
		}
	}
	
	/***
	 * Writes the tab of the current environment in the song
	 * @param song current Song
	 * @param env curent Environment
	 * @param bw Writer
	 * @param newEnv if an new Environment startet
	 * @throws IOException
	 */
	private static void writeTab(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException{
		String tooLong = "no";
		int breakPointChord = 0;
		String tmpChord = "";
		int breakPointLyric = 0;
		String tmpLyric = "";
		if(newEnv == true) {
			bw.write("\\textbf{Tab:"+song.getEnvironment(env).getTitle()+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = song.getEnvironment(env).getChord();
			
			breakPointChord = isTooLong(text);
			if(breakPointChord > 0) {
				tooLong = "chord";
				tmpChord = text.substring(breakPointChord);
				text = text.substring(0,breakPointChord);
			}
			
			text = prepareString(text);
			bw.write("\\textbf{ "+text+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = song.getEnvironment(env).getLyric();
			
			breakPointLyric = isTooLong(text);
			if(breakPointLyric > 0) {
				if(tooLong == "chord") {
					tooLong = "both";
				}else {
					tooLong = "lyric";
				}
				tmpLyric = text.substring(breakPointLyric);
				text = text.substring(0,breakPointLyric);
			}
			
			text = prepareString(text);
			bw.write(text+"\\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		switch(tooLong) {
		case "both":
			tmpChord = prepareString(tmpChord);
			tmpLyric = prepareString(tmpLyric);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "chord":
			tmpChord = prepareString(tmpChord);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "lyric":
			tmpLyric = prepareString(tmpLyric);
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "no":
			break;
		}
	}
	
	/***
	 * Writes the grid of the current environment in the song
	 * @param song current Song
	 * @param env curent Environment
	 * @param bw Writer
	 * @param newEnv if an new Environment startet
	 * @throws IOException
	 */
	private static void writeGrid(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException{
		String tooLong = "no";
		int breakPointChord = 0;
		String tmpChord = "";
		int breakPointLyric = 0;
		String tmpLyric = "";
		if(newEnv == true) {
			bw.write("\\textbf{Grid:"+song.getEnvironment(env).getTitle()+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = song.getEnvironment(env).getChord();
			
			breakPointChord = isTooLong(text);
			if(breakPointChord > 0) {
				tooLong = "chord";
				tmpChord = text.substring(breakPointChord);
				text = text.substring(0,breakPointChord);
			}
			
			text = prepareString(text);
			bw.write("\\textbf{ "+text+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = song.getEnvironment(env).getLyric();
			
			breakPointLyric = isTooLong(text);
			if(breakPointLyric > 0) {
				if(tooLong == "chord") {
					tooLong = "both";
				}else {
					tooLong = "lyric";
				}
				tmpLyric = text.substring(breakPointLyric);
				text = text.substring(0,breakPointLyric);
			}
			
			text = prepareString(text);
			bw.write(text+"\\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		switch(tooLong) {
		case "both":
			tmpChord = prepareString(tmpChord);
			tmpLyric = prepareString(tmpLyric);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "chord":
			tmpChord = prepareString(tmpChord);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "lyric":
			tmpLyric = prepareString(tmpLyric);
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "no":
			break;
		}
	}
	
	/***
	 * Writes an Null environment in the song
	 * @param song current Song
	 * @param env curent Environment
	 * @param bw Writer
	 * @param newEnv if an new Environment startet
	 * @throws IOException
	 */
	private static void writeNull(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException
	{
		String tooLong = "no";
		int breakPointChord = 0;
		String tmpChord = "";
		int breakPointLyric = 0;
		String tmpLyric = "";
		if(newEnv == true) {
			bw.write("\\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = song.getEnvironment(env).getChord();
			
			breakPointChord = isTooLong(text);
			if(breakPointChord > 0) {
				tooLong = "chord";
				tmpChord = text.substring(breakPointChord);
				text = text.substring(0,breakPointChord);
			}
			
			text = prepareString(text);
			bw.write("\\textbf{ "+text+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = song.getEnvironment(env).getLyric();
			
			breakPointLyric = isTooLong(text);
			if(breakPointLyric > 0) {
				if(tooLong == "chord") {
					tooLong = "both";
				}else {
					tooLong = "lyric";
				}
				tmpLyric = text.substring(breakPointLyric);
				text = text.substring(0,breakPointLyric);
			}
			
			text = prepareString(text);
			bw.write(text+"\\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		switch(tooLong) {
		case "both":
			tmpChord = prepareString(tmpChord);
			tmpLyric = prepareString(tmpLyric);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "chord":
			tmpChord = prepareString(tmpChord);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "lyric":
			tmpLyric = prepareString(tmpLyric);
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "no":
			break;
		}
	}

	/***
	 * Writes other environments in the song
	 * @param song current Song
	 * @param env curent Environment
	 * @param bw Writer
	 * @param newEnv if an new Environment startet
	 * @throws IOException
	 */
	private static void writeOther(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException
	{
		String tooLong = "no";
		int breakPointChord = 0;
		String tmpChord = "";
		int breakPointLyric = 0;
		String tmpLyric = "";
		if(newEnv == true && !(song.getEnvironment(env).getTitle().isBlank() || song.getEnvironment(env).getTitle().isEmpty())) {
			bw.write("\\textbf{"+song.getEnvironment(env).getTitle()+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = song.getEnvironment(env).getChord();
			
			breakPointChord = isTooLong(text);
			if(breakPointChord > 0) {
				tooLong = "chord";
				tmpChord = text.substring(breakPointChord);
				text = text.substring(0,breakPointChord);
			}
			
			text = prepareString(text);
			bw.write("\\textbf{ "+text+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = song.getEnvironment(env).getLyric();
			
			breakPointLyric = isTooLong(text);
			if(breakPointLyric > 0) {
				if(tooLong == "chord") {
					tooLong = "both";
				}else {
					tooLong = "lyric";
				}
				tmpLyric = text.substring(breakPointLyric);
				text = text.substring(0,breakPointLyric);
			}
			
			text = prepareString(text);
			bw.write(text+"\\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		switch(tooLong) {
		case "both":
			tmpChord = prepareString(tmpChord);
			tmpLyric = prepareString(tmpLyric);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "chord":
			tmpChord = prepareString(tmpChord);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "lyric":
			tmpLyric = prepareString(tmpLyric);
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "no":
			break;
		}
	}

	/***
	 * Writes the comment of the current environment in the song
	 * @param song current Song
	 * @param env curent Environment
	 * @param bw Writer
	 * @param newEnv if an new Environment startet
	 * @throws IOException
	 */
	private static void writeComment(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException
	{
		String tooLong = "no";
		int breakPointChord = 0;
		String tmpChord = "";
		int breakPointLyric = 0;
		String tmpLyric = "";
		if(newEnv == true) {
			if(song.getEnvironment(env).getCommentIsItalic()==true) {
				bw.write("\\textit{");
				bw.newLine();
			}
			bw.write("\\textbf{Comment:"+song.getEnvironment(env).getTitle()+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = song.getEnvironment(env).getLyric();
			
			breakPointLyric = isTooLong(text);
			if(breakPointLyric > 0) {
				if(tooLong == "chord") {
					tooLong = "both";
				}else {
					tooLong = "lyric";
				}
				tmpLyric = text.substring(breakPointLyric);
				text = text.substring(0,breakPointLyric);
			}
			
			text = prepareString(text);
			bw.write(text+"\\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(newEnv == true && song.getEnvironment(env).getCommentIsItalic()==true) {
			bw.write("}");
			bw.newLine();
		}
		switch(tooLong) {
		case "both":
			tmpChord = prepareString(tmpChord);
			tmpLyric = prepareString(tmpLyric);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "chord":
			tmpChord = prepareString(tmpChord);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "lyric":
			tmpLyric = prepareString(tmpLyric);
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "no":
			break;
		}
	}

	/***
	 * Writes the instruction of the current environment in the song
	 * @param song current Song
	 * @param env curent Environment
	 * @param bw Writer
	 * @param newEnv if an new Environment startet
	 * @throws IOException
	 */
	private static void writeInstruction(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException 
	{
		String tooLong = "no";
		int breakPointChord = 0;
		String tmpChord = "";
		int breakPointLyric = 0;
		String tmpLyric = "";
		if(newEnv == true) {
			bw.write("\\textbf{Instruction:"+song.getEnvironment(env).getTitle()+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = song.getEnvironment(env).getChord();
			
			breakPointChord = isTooLong(text);
			if(breakPointChord > 0) {
				tooLong = "chord";
				tmpChord = text.substring(breakPointChord);
				text = text.substring(0,breakPointChord);
			}
			
			text = prepareString(text);
			bw.write("\\textbf{ "+text+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = song.getEnvironment(env).getLyric();
			
			breakPointLyric = isTooLong(text);
			if(breakPointLyric > 0) {
				if(tooLong == "chord") {
					tooLong = "both";
				}else {
					tooLong = "lyric";
				}
				tmpLyric = text.substring(breakPointLyric);
				text = text.substring(0,breakPointLyric);
			}
			
			text = prepareString(text);
			bw.write(text+"\\hspace*{\\fill} \\\\");
			bw.newLine();
		}
		switch(tooLong) {
		case "both":
			tmpChord = prepareString(tmpChord);
			tmpLyric = prepareString(tmpLyric);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "chord":
			tmpChord = prepareString(tmpChord);
			bw.write("\\textbf{ "+tmpChord+"} \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "lyric":
			tmpLyric = prepareString(tmpLyric);
			bw.write(tmpLyric+" \\hspace*{\\fill} \\\\");
			bw.newLine();
			break;
		case "no":
			break;
		}
	}
	
	private static String prepareString(String line) {//Attention order matters
		String lineFix = line;
		lineFix = line.replace("\\", "\\textbackslash");
		lineFix = lineFix.replace("#", "\\# ");
		lineFix = lineFix.replace("<", "$<$");
		lineFix = lineFix.replace(">", "$>$");
		lineFix = lineFix.replace("[", "{[}");
		lineFix = lineFix.replace("]", "{]}");
		lineFix = lineFix.replace("~", "\\~{}");
		lineFix = lineFix.replace(" ", "~");
		lineFix = lineFix.replace("\"", "''");
		lineFix = lineFix.replace("ä", "\\\"a");
		lineFix = lineFix.replace("ö", "\\\"o");
		lineFix = lineFix.replace("ü", "\\\"u");
		lineFix = lineFix.replace("Ä", "\\\"A");
		lineFix = lineFix.replace("Ö", "\\\"O");
		lineFix = lineFix.replace("Ü", "\\\"U");
		lineFix = lineFix.replace("ß", "\\ss{}");
		lineFix = lineFix.replace("$", "\\$");
		lineFix = lineFix.replace("_", "\\_");
		lineFix = lineFix.replace("°", "\\textdegree");
		lineFix = lineFix.replace("^", "\\^{}");
		lineFix = lineFix.replace("%", "\\%");
		lineFix = replaceFirstSpaces(lineFix);
		return lineFix;
	}
	
	private static String replaceFirstSpaces(String line) {
		String tmp = line;
		int count = 0;
		while(!tmp.isEmpty() && tmp.charAt(0)=='~') {
			count ++;
			tmp = tmp.substring(1);
		}
		if(count > 0) {
			tmp = "\\hspace*{"+count+"em}"+tmp;
		}
		return tmp;
	}
	
	private static int isTooLong(String text) {
		int trennZahl = 0;
		if(text.length() > 80) {
			trennZahl = 80;
		}
		return trennZahl;
	}
}