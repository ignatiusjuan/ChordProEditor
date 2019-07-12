package de.chordsystem.chordproeditor.model.interfaces;

public interface Verse {

	void addChordLyric(ChordLyric cl);

	ChordLyric getChordLyric(int line);

	int getChordLyricSize();

}