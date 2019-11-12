/**
 * 
 */
package de.chordsystem.latex;

import java.io.File;
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
	public static boolean generatePDF(String filepath,String filename, Song song) {
		try {
			WriteTex.writeTex(filename,song);

		}catch(IOException e) {
			return false;
		}
		callLatex(filepath,filename);
		
		makeClean(filepath,filename);
		return true;
	}
	
	/***
	 * Calls the LaTeX compiler to generate the pdf
	 * @param song
	 */
	private static void callLatex(String filepath,String filename) {
		try{
			Runtime rt = Runtime.getRuntime();
			rt.exec("cmd.exe /c pdflatex -output-directory=\"" + filepath + "\" C:\\temp\\"+filename+".tex\"");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/***
	 * Delete the files, that are not needed at the end.
	 * @param song
	 */
	private static void makeClean(String filepath, String filename) {
		try{
			Runtime rt = Runtime.getRuntime();
			Thread.sleep(5000);
			String full = filepath + "\\" + filename + ".aux";
			new File(full.replace("\\", "\\\\")).delete();
			full = filepath + "\\" + filename + ".log";
			new File(full.replace("\\", "\\\\")).delete();
			full = filepath + "\\" + filename + ".aux";
			new File(full.replace("\\", "\\\\")).delete();
			full = "C:\\temp\\"+filename+".tex";
			new File(full.replace("\\", "\\\\")).delete();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}