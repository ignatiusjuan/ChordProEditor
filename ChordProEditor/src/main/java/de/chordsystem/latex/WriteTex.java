package de.chordsystem.latex;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import de.chordsystem.chordproeditor.model.interfaces.Song;


//https://www.tutorials.de/threads/java-tex-pdf.375887/

public class WriteTex {
	
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
		//TODO
		writeLyrics(song, bw);
		//TODO
		bw.write("\\end{document}");
		bw.newLine();
		bw.close();
	}
	
	private static boolean writeLyrics(Song song, BufferedWriter bw) {
		//TODO
		return false;
	}
	
	private static FileWriter createTexDokument(String titel) throws IOException
	{
		FileWriter tex = new FileWriter(titel+".tex");
		return tex;
	}

}
