package de.chordsystem.latex;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import de.chordsystem.chordproeditor.model.abstracts.EnvironmentAbstract;
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
		FileWriter tex = createTexDokument(song.getTitle());
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
	 * @param song
	 * @param bw
	 * @return
	 * @throws IOException
	 */
	private static boolean writeLyrics(Song song, BufferedWriter bw) throws IOException
	{
		//TODO
		for (int j = 0; j < song.getEnvironmentSize(); j++) {
			switch(song.getEnvironment(j).getType()) {
				case EnvironmentAbstract.CHORUS:
					writeChorus(song, j, bw);
					break;
				case EnvironmentAbstract.VERSE:
					writeVerse(song, j, bw);
					break;
				case EnvironmentAbstract.TAB:
					writeTab(song, j, bw);
					break;
				case EnvironmentAbstract.GRID:
					writeGrid(song, j, bw);
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
	 * @throws IOException
	 */
	private static void writeChorus(Song song, int env, BufferedWriter bw) throws IOException
	{
		Chorus chorus = (Chorus)song.getEnvironment(env);
		bw.write("\noindent");
		bw.write("\textbf{Chorus:}");
		bw.newLine();
		bw.newLine();
		for (int i = 0; i < chorus.getChordLyricSize(); i++) {
			ChordLyric cl = chorus.getChordLyric(i);
			bw.write("\noindent");
			bw.write(cl.getChord());
			bw.newLine();
			bw.newLine();
			bw.write("\noindent");
			bw.write(cl.getLyric());
			bw.newLine();
			bw.newLine();
		}
	}
	
	/***
	 * Writes the verse of the current environment in the song
	 * @param song
	 * @param env
	 * @param bw
	 * @throws IOException
	 */
	private static void writeVerse(Song song, int env, BufferedWriter bw) throws IOException{
		Verse verse = (Verse)song.getEnvironment(env);
		bw.write("\noindent");
		bw.write("\textbf{Verse:}");
		bw.newLine();
		bw.newLine();
		for (int i = 0; i < verse.getChordLyricSize(); i++) {
			ChordLyric cl = verse.getChordLyric(i);
			bw.write("\noindent");
			bw.write(cl.getChord());
			bw.newLine();
			bw.newLine();
			bw.write("\noindent");
			bw.write(cl.getLyric());
			bw.newLine();
			bw.newLine();
		}
	}
	
	/***
	 * Writes the tab of the current environment in the song
	 * @param song
	 * @param env
	 * @param bw
	 * @throws IOException
	 */
	private static void writeTab(Song song, int env, BufferedWriter bw) throws IOException{
		Tab tab = (Tab)song.getEnvironment(env);
		bw.write("\noindent");
		bw.write("\textbf{Tab:}");
		bw.newLine();
		bw.newLine();
		for (int i = 0; i < tab.getTabListSize(); i++) {
			String cl = tab.getTab(i);
			bw.write("\noindent");
			bw.write(cl);
			bw.newLine();
			bw.newLine();
			bw.write("\noindent");
			bw.write(cl);
			bw.newLine();
			bw.newLine();
		}
	}
	
	/***
	 * Writes the grid of the current environment in the song
	 * @param song
	 * @param env
	 * @param bw
	 * @throws IOException
	 */
	private static void writeGrid(Song song, int env, BufferedWriter bw) throws IOException{
		Grid grid = (Grid)song.getEnvironment(env);
		bw.write("\noindent");
		bw.write("\textbf{Verse:}");
		bw.newLine();
		bw.newLine();
		for (int i = 0; i < grid.getGridListSize(); i++) {
			String cl = grid.getGrid(i);
			bw.write("\noindent");
			bw.write(cl);
			bw.newLine();
			bw.newLine();
			bw.write("\noindent");
			bw.write(cl);
			bw.newLine();
			bw.newLine();
		}
	}
	
	/***
	 * Creates a new .tex file with the title of the document as name
	 * @param titel
	 * @return
	 * @throws IOException
	 */
	private static FileWriter createTexDokument(String titel) throws IOException
	{
		FileWriter tex = new FileWriter(titel+".tex");
		return tex;
	}
}
