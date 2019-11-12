package de.chordsystem.latex;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import de.chordsystem.chordproeditor.model.classes.EnvironmentImpl;
import de.chordsystem.chordproeditor.model.interfaces.*;

//https://www.tutorials.de/threads/java-tex-pdf.375887/

/***
 * 
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
	public static void writeTex(String filename,Song song) throws IOException
	{

		FileWriter tex = new FileWriter("C:\\temp\\" + filename+".tex");
		BufferedWriter bw = new BufferedWriter(tex);
		bw.write("\\documentclass{article}");
		bw.newLine();
		bw.write("\\usepackage{courier}");
		bw.newLine();
		bw.write("\\setlength{\\parindent}{0em}");
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
	 */
	/**
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
		if(newEnv == true) {
			bw.write("\\textbf{Chorus:"+song.getEnvironment(env).getTitle()+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = prepareString(song.getEnvironment(env).getChord());
			bw.write("\\textbf{ "+text+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = prepareString(song.getEnvironment(env).getLyric());
			bw.write(text+" \\\\");
			bw.newLine();
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
		if(newEnv == true) {
			bw.write("\\textbf{Verse:"+song.getEnvironment(env).getTitle()+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = prepareString(song.getEnvironment(env).getChord());
			bw.write("\\textbf{ "+text+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = prepareString(song.getEnvironment(env).getLyric());
			bw.write(text+" \\\\");
			bw.newLine();
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
		if(newEnv == true) {
			bw.write("\\textbf{Tab:"+song.getEnvironment(env).getTitle()+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = prepareString(song.getEnvironment(env).getChord());
			bw.write("\\textbf{ "+text+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = prepareString(song.getEnvironment(env).getLyric());
			bw.write(text+" \\\\");
			bw.newLine();
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
		if(newEnv == true) {
			bw.write("\\textbf{Grid:"+song.getEnvironment(env).getTitle()+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = prepareString(song.getEnvironment(env).getChord());
			bw.write("\\textbf{ "+text+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = prepareString(song.getEnvironment(env).getLyric());
			bw.write(text+" \\\\");
			bw.newLine();
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
		// TODO Auto-generated method stub
		if(newEnv == true) {
			bw.write(" \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = prepareString(song.getEnvironment(env).getChord());
			bw.write("\\textbf{ "+text+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = prepareString(song.getEnvironment(env).getLyric());
			bw.write(text+" \\\\");
			bw.newLine();
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
		// TODO Auto-generated method stub
		if(newEnv == true) {
			bw.write("\\textbf{Other:"+song.getEnvironment(env).getTitle()+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = prepareString(song.getEnvironment(env).getChord());
			bw.write("\\textbf{ "+text+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = prepareString(song.getEnvironment(env).getLyric());
			bw.write(text+" \\\\");
			bw.newLine();
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
		//TODO Box
		if(newEnv == true) {
			if(song.getEnvironment(env).getCommentIsItalic()==true) {
				bw.write("\\textit{");
				bw.newLine();
			}
			bw.write("\\textbf{Comment:"+song.getEnvironment(env).getTitle()+"} \\\\");
			bw.newLine();
		}
//		if(song.getEnvironment(env).getChord() != null) {
//			String text = prepareString(song.getEnvironment(env).getChord());
//			bw.write("\\textbf{ "+text+"} \\\\");
//			bw.newLine();
//		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = prepareString(song.getEnvironment(env).getLyric());
			bw.write(text+" \\\\");
			bw.newLine();
		}
		if(newEnv == true && song.getEnvironment(env).getCommentIsItalic()==true) {
			bw.write("}");
			bw.newLine();
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
		// TODO Auto-generated method stub
		if(newEnv == true) {
			bw.write("\\textbf{Instruction:"+song.getEnvironment(env).getTitle()+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			String text = prepareString(song.getEnvironment(env).getChord());
			bw.write("\\textbf{ "+text+"} \\\\"
					);
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			String text = prepareString(song.getEnvironment(env).getLyric());
			bw.write(text+" \\\\");
			bw.newLine();
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
//		System.out.println(line);
		while(!tmp.isEmpty() && tmp.charAt(0)=='~') {
//			System.out.println(tmp);
			count ++;
			tmp = tmp.substring(1);
		}
		if(count > 0) {
			tmp = "\\hspace*{"+count+"em}"+tmp;
		}
		return tmp;
	}
}