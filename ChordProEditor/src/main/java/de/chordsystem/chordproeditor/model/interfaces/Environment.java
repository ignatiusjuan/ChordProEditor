package de.chordsystem.chordproeditor.model.interfaces;

public interface Environment {

	int getType();

	void setType(int type);

	String getTitle();

	void setTitle(String title);

	String getChord();

	void setChord(String chord);

	String getLyric();

	void setLyric(String lyric);
	
	String toString();

}