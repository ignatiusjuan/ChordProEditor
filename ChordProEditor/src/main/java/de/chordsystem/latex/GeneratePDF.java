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
	public static boolean generatePDF(String filename, Song song) {
		try {
			WriteTex.writeTex(song);
		}catch(IOException e) {
			return false;
		}
		callLatex(filename);
		//makeClean(song);
		return true;
	}
	
	/***
	 * Calls the LaTeX compiler to generate the pdf
	 * @param song
	 */
	private static void callLatex(String filename) {
		try{
			Runtime rt = Runtime.getRuntime();
			rt.exec("pdflatex \""+filename+".tex\"");
		}catch(Exception e){
			System.out.println("Konsolen Exception callLatex");
		}
	}
	
	/***
	 * Moves PDF from song to given path
	 * @param path
	 * @param song
	 */
	public static void movePDF(String path, String filename) {
		try{
			Runtime rt = Runtime.getRuntime();
			System.out.println("move \""+filename+".pdf\" \""+path+"\"");
			rt.exec("move \""+filename+".pdf\" \""+path+"\"");
		}catch(Exception e){
			System.out.println("Konsolen Exception movePDF");
		}
	}
	
	/***
	 * Delete the files, that are not needed at the end.
	 * @param song
	 */
	private static void makeClean(String filename) {
		try{
			Runtime rt = Runtime.getRuntime();
			rt.exec("del \""+filename+".aux\"");
			rt.exec("del \""+filename+".tex\"");
			rt.exec("del \""+filename+".log\"");
			rt.exec("del \""+filename+".out\"");
		}catch(Exception e){
			System.out.println("Konsolen Exception makeClean");
		}
	}

}
