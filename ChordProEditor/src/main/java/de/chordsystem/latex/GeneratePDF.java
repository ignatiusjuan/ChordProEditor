/**
 * 
 */
package de.chordsystem.latex;

import java.io.IOException;

import de.chordsystem.chordproeditor.model.interfaces.Song;

/**
 * @author shineglurak
 *
 */
public class GeneratePDF {
	
	public static boolean generatePDF(Song song) {
		try {
			WriteTex.writeTex(song);
			song.getEnvironment(song.getCurrentEnvironment());
		}catch(IOException e) {
			
		}
		makeClean(song);
		return false;
	}
	
	public void callLatex(Song song) {
		String title = song.getTitle();
		/*
		try{
			Runtime rt = Runtime.getRuntime();
			rt.exec("mkdir Test");
		}catch(Exception e){
			System.out.println("Exception");
		}
		 */
		
	}
	
	private static void makeClean(Song song) {
		//TODO move pdf elsewhere, delete the rest
	}

}
