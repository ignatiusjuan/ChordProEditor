package de.chordsystem.latex;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import de.chordsystem.chordproeditor.model.classes.EnvironmentImpl;
import de.chordsystem.chordproeditor.model.interfaces.*;


//https://www.tutorials.de/threads/java-tex-pdf.375887/

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
		writeLyrics(song, bw);
		bw.write("\\end{document}");
		bw.newLine();
		bw.close();
	}
	
	/***
	 * Decides which method has to be called to write the current environment 
	 * @return
	 * @throws IOException
	 */
	private static boolean writeLyrics(Song song, BufferedWriter bw) throws IOException
	{
		boolean newEnv = true;
		for (int currentEnv = 0; currentEnv < song.getEnvironmentSize(); currentEnv++) {
			if(song.getEnvironment(currentEnv).getTitle()==song.getEnvironment(currentEnv-1).getTitle()&&
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
		return false;
	}

	/***
	 * Writes the chorus of the current environment in the song
	 * @param song
	 * @param env
	 * @param bw
	 * @param newEnv 
	 * @throws IOException
	 */
	private static void writeChorus(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException
	{
		if(newEnv == true) {
			bw.write("\noindent");
			bw.write("\textbf{Chorus:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		bw.write("\noindent");
		bw.write(song.getEnvironment(env).getChord());
		bw.newLine();
		bw.newLine();
		bw.write("\noindent");
		bw.write(song.getEnvironment(env).getLyric());
		bw.newLine();
		bw.newLine();
	}
	
	/***
	 * Writes the verse of the current environment in the song
	 * @param song
	 * @param env
	 * @param bw
	 * @param newEnv 
	 * @throws IOException
	 */
	private static void writeVerse(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException{
		if(newEnv == true) {
			bw.write("\noindent");
			bw.write("\textbf{Verse:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		bw.write("\noindent");
		bw.write(song.getEnvironment(env).getLyric());
		bw.newLine();
		bw.newLine();
	}
	
	/***
	 * Writes the tab of the current environment in the song
	 * @param song
	 * @param env
	 * @param bw
	 * @param newEnv 
	 * @throws IOException
	 */
	private static void writeTab(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException{
		if(newEnv == true) {
			bw.write("\noindent");
			bw.write("\textbf{Tab:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		bw.write("\noindent");
		bw.write(song.getEnvironment(env).getLyric());
		bw.newLine();
		bw.newLine();
	}
	
	/***
	 * Writes the grid of the current environment in the song
	 * @param song
	 * @param env
	 * @param bw
	 * @param newEnv 
	 * @throws IOException
	 */
	private static void writeGrid(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException{
		if(newEnv == true) {
			bw.write("\noindent");
			bw.write("\textbf{Grid:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		bw.write("\noindent");
		bw.write(song.getEnvironment(env).getLyric());
		bw.newLine();
		bw.newLine();
	}
	
	private static void writeNull(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException
	{
		// TODO Auto-generated method stub
		if(newEnv == true) {
			bw.write("\noindent");
			bw.write("\textbf{Null:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		bw.write("\noindent");
		bw.write(song.getEnvironment(env).getLyric());
		bw.newLine();
		bw.newLine();
		
	}

	private static void writeOther(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException
	{
		// TODO Auto-generated method stub
		if(newEnv == true) {
			bw.write("\noindent");
			bw.write("\textbf{Other:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		bw.write("\noindent");
		bw.write(song.getEnvironment(env).getLyric());
		bw.newLine();
		bw.newLine();
	}

	private static void writeComment(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException
	{
		//TODO Box
		if(newEnv == true) {
			if(song.getEnvironment(env).getCommentIsItalic()==true) {
				bw.write("\textit{");
			}
			bw.write("\noindent");
			bw.write("\textbf{Comment:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		bw.write("\noindent");
		bw.write(song.getEnvironment(env).getLyric());
		bw.newLine();
		bw.newLine();
		if(newEnv == true) {
			bw.write("}");
		}
	}

	private static void writeInstruction(Song song, int env, BufferedWriter bw, boolean newEnv) throws IOException 
	{
		// TODO Auto-generated method stub
		if(newEnv == true) {
			bw.write("\noindent");
			bw.write("\textbf{Instruction:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		bw.write("\noindent");
		bw.write(song.getEnvironment(env).getLyric());
		bw.newLine();
		bw.newLine();
	}
}