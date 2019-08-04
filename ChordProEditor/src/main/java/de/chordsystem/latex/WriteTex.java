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
		//Documentclass article 
		bw.write("\\documentclass{scrartcl}");
		bw.newLine();
		//Set title
		bw.write("\\title{"+song.getTitle()+"}");
		bw.newLine();
		//Set author
		bw.write("\\author{"+song.getArtist()+"}");
		bw.newLine();
		//Set year
		bw.write("\\date{"+song.getYear()+"}");
		bw.newLine();
		//Begin of document
		bw.write("\begin{document}");
		bw.newLine();
		//Text monospaced
		bw.write("\\ttfamily");
		bw.newLine();
		//isFinished
		if(!song.isFinished()) {
			bw.write("Not Finished");
			bw.newLine();
		}
		//Maketitle
		bw.write("\\maketitle");
		bw.newLine();
		//Subtitles
		for(int i = 0; i < song.getSubtitle().size(); i++) {
			bw.write(song.getSubtitle().get(i));
			bw.newLine();
		}
		bw.newLine();
		//Album
		bw.write("Album: " + song.getAlbum());
		bw.newLine();
		//Composers
		bw.write("Composer: ");
		for(int i = 0; i < song.getComposer().size(); i++) {
			bw.write(song.getComposer().get(i));
			bw.newLine();
		}
		bw.newLine();
		//Lyricists
		bw.write("Lyricists: ");
		for(int i = 0; i < song.getLyricist().size(); i++) {
			bw.write(song.getLyricist().get(i));
			bw.newLine();
		}
		bw.newLine();
		//Key
		bw.write("Key: " + song.getKey());
		bw.newLine();
		//Time
		bw.write("Time: " + song.getTime());
		bw.newLine();
		//Tempo
		bw.write("Tempo: " + song.getTempo());
		bw.newLine();
		//Duration
		bw.write("Duration: " + song.getDuration());
		bw.newLine();
		//Capo
		bw.write("Capo: " + song.getCapo());
		bw.newLine();
		//Meta
		bw.write("Metadata: ");
		for(int i = 0; i < song.getMeta().size(); i++) {
			bw.write(song.getMeta().get(i));
			bw.newLine();
		}
		bw.newLine();
		//Lyrics
		writeLyrics(song, bw);
		bw.newLine();
		for(int i = 0; i < song.getCopyright().size(); i++) {
			bw.write(song.getCopyright().get(i));
		}
		bw.newLine();
		//End of document
		bw.write("\\end{document}");
		bw.newLine();
		bw.close();
	}
	
	/***
	 * Decides which method has to be called to write the current environment 
	 * @return
	 * @throws IOException
	 */
	private static void writeLyrics(Song song, BufferedWriter bw) throws IOException
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
			bw.write("\noindent");
			bw.write("\textbf{Chorus:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getChord());
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getLyric());
			bw.newLine();
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
			bw.write("\noindent");
			bw.write("\textbf{Verse:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getChord());
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getLyric());
			bw.newLine();
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
			bw.write("\noindent");
			bw.write("\textbf{Tab:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getChord());
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getLyric());
			bw.newLine();
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
			bw.write("\noindent");
			bw.write("\textbf{Grid:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getChord());
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getLyric());
			bw.newLine();
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
			bw.write("\noindent");
			bw.write("\textbf{Null:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getChord());
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getLyric());
			bw.newLine();
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
			bw.write("\noindent");
			bw.write("\textbf{Other:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getChord());
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getLyric());
			bw.newLine();
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
				bw.write("\textit{");
			}
			bw.write("\noindent");
			bw.write("\textbf{Comment:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getChord());
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getLyric());
			bw.newLine();
			bw.newLine();
		}
		if(newEnv == true) {
			bw.write("}");
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
			bw.write("\noindent");
			bw.write("\textbf{Instruction:"+song.getEnvironment(env).getTitle()+"}");
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getChord() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getChord());
			bw.newLine();
			bw.newLine();
		}
		if(song.getEnvironment(env).getLyric() != null) {
			bw.write("\noindent");
			bw.write(song.getEnvironment(env).getLyric());
			bw.newLine();
			bw.newLine();
		}
	}
}