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
			rt.exec("pdflatex -output-directory=\"" + filepath + "\" C:\\temp\\"+filename+".tex\"");
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
			rt.exec("cmd.exe /c" + "move \""+filename+".pdf\" \""+path+"\"");
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println("Konsolen Exception movePDF");
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
			
			//rt.exec("cmd.exe /c " + "del /f \""+ filepath + "\\" + filename+".aux\"");
			//rt.exec("cmd.exe /c " + "del \""+ filepath + "\\" + filename+".log\"");
			//rt.exec("cmd.exe /c " + "del \"C:\\temp\\"+filename+".tex\"");
			//rt.exec("del \""+filename+".out\"");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
