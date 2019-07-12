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
	public static void callLatex(Song song) {
		String title = song.getTitle();
		try{
			Runtime rt = Runtime.getRuntime();
			rt.exec("pdflatex "+title+".tex");
		}catch(Exception e){
			System.out.println("Exception");
		}
	}
	
	/***
	 * Delete the files, that are not needed at the end.
	 * @param song
	 */
	private static void makeClean(Song song) {
		//TODO move pdf elsewhere, delete the rest
	}

}
