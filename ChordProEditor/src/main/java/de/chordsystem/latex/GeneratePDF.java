/**
 * 
 */
package de.chordsystem.latex;

import java.io.IOException;

import de.chordsystem.chordproeditor.model.interfaces.*;

/**
 * @author shineglurak
 *
 */
public class GeneratePDF {
	
	/***
	 * Start the routine for the .tex file an handels the IOExceptions which could appear.
	 * Calls at the makeClean().
	 * @param song
	 * @return true if was succesfull else false
	 */
	public static boolean generatePDF(Song song) {
		try {
			WriteTex.writeTex(song);
		}catch(IOException e) {
			return false;
		}
		callLatex(song);
		makeClean(song);
		return true;
	}
	
	/***
	 * Calls the LaTeX compiler to generate the pdf
	 * @param song
	 */
	private static void callLatex(Song song) {
		String title = song.getTitle();
		try{
			Runtime rt = Runtime.getRuntime();
			rt.exec("pdflatex "+title+".tex");
		}catch(Exception e){
			System.out.println("Konsolen Exception callLatex");
		}
	}
	
	/***
	 * Moves PDF from song to given path
	 * @param path
	 * @param song
	 */
	public static void movePDF(String path, Song song) {
		try{
			Runtime rt = Runtime.getRuntime();
			rt.exec("move "+song.getTitle()+" "+path);
		}catch(Exception e){
			System.out.println("Konsolen Exception movePDF");
		}
	}
	
	/***
	 * Delete the files, that are not needed at the end.
	 * @param song
	 */
	private static void makeClean(Song song) {
		try{
			Runtime rt = Runtime.getRuntime();
			rt.exec("del "+song.getTitle()+".aux");
			rt.exec("del "+song.getTitle()+".tex");
			rt.exec("del "+song.getTitle()+".log");
			rt.exec("del "+song.getTitle()+".out");
		}catch(Exception e){
			System.out.println("Konsolen Exception movePDF");
		}
	}

}
