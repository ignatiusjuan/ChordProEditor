/**
 * 
 */
package de.chordsystem.latex;

import java.io.IOException;

import de.chordsystem.chordproeditor.model.interfaces.*;
import de.chordsystem.chordproeditor.model.abstracts.EnvironmentAbstract;

/**
 * @author shineglurak
 *
 */
public class GeneratePDF {
	
	public static boolean generatePDF(Song song) {
		try {
			WriteTex.writeTex(song);
			if (song.getEnvironment(song.getCurrentEnvironment()).getType() == EnvironmentAbstract.CHORUS) {
				Chorus chorus = (Chorus)song.getEnvironment(song.getCurrentEnvironment());
				for (int i = 0; i < chorus.getChordLyricSize(); i++) {
					ChordLyric cl = chorus.getChordLyric(i);
					System.out.println(cl.getChord());
					System.out.println(cl.getLyric());
				}
			}
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
