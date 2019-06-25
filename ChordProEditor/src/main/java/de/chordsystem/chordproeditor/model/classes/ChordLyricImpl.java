package de.chordsystem.chordproeditor.model.classes;

import de.chordsystem.chordproeditor.model.interfaces.ChordLyric;

public class ChordLyricImpl implements ChordLyric {
	
	private String chord;
	private String lyric;
	
	public ChordLyricImpl() {
		this.chord = "";
		this.lyric = "";
	}

	public ChordLyricImpl(String chord, String lyric) {
		super();
		this.chord = chord;
		this.lyric = lyric;
	}

	/**
	 * @return the chord
	 */
	public String getChord() {
		return chord;
	}
	
	/**
	 * @param chord the chord to set
	 */
	public void setChord(String chord) {
		this.chord = chord;
	}
	
	/**
	 * @return the lyric
	 */
	public String getLyric() {
		return lyric;
	}
	
	/**
	 * @param lyric the lyric to set
	 */
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}

	@Override
	public String toString() {
		return chord + "\n" + lyric + "\n";
	}
	
}
