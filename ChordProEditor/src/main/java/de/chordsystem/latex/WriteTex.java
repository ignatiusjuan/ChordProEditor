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
	public static void writeTex(Song song) throws IOException
	{

		FileWriter tex = new FileWriter(song.getTitle()+".tex");
		BufferedWriter bw = new BufferedWriter(tex);
		bw.write("\\documentclass{scrartcl}");
		bw.newLine();
		bw.write("\\setlength{\\parindent}{0em}");
		bw.newLine();
		bw.write("\\title{"+song.getTitle()+"}");
		bw.newLine();
		bw.write("\\author{"+song.getArtist()+"}");
		bw.newLine();
		bw.write("\\date{"+song.getYear()+"}");
		bw.newLine();
		bw.write("\\begin{document}");
		bw.newLine();
		bw.write("\\maketitle");
		bw.newLine();
		bw.write("\\texttt{");
		bw.newLine();
		writeLyrics(song, bw);
		String temp = song.getCopyright().toString().replace("[", "");
		temp = temp.replace("]", "");
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
			bw.write(song.getEnvironment(env).getChord()+" \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write(song.getEnvironment(env).getLyric()+" \\\\");
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
			bw.write(song.getEnvironment(env).getChord()+" \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write(song.getEnvironment(env).getLyric()+" \\\\");
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
			bw.write(song.getEnvironment(env).getChord()+" \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write(song.getEnvironment(env).getLyric()+" \\\\");
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
			bw.write(song.getEnvironment(env).getChord()+" \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write(song.getEnvironment(env).getLyric()+" \\\\");
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
			bw.write("\\textbf{Null:"+song.getEnvironment(env).getTitle()+"} \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			bw.write(song.getEnvironment(env).getChord()+" \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write(song.getEnvironment(env).getLyric()+" \\\\");
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
			bw.write(song.getEnvironment(env).getChord()+" \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write(song.getEnvironment(env).getLyric()+" \\\\");
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
		if(song.getEnvironment(env).getChord() != null) {
			bw.write(song.getEnvironment(env).getChord()+" \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write(song.getEnvironment(env).getLyric()+" \\\\");
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
			bw.write(song.getEnvironment(env).getChord()+" \\\\");
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write(song.getEnvironment(env).getLyric()+" \\\\");
			bw.newLine();
		}
	}
}