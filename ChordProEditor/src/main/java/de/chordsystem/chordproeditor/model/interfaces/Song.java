package de.chordsystem.chordproeditor.model.interfaces;

import java.util.List;

import de.chordsystem.chordproeditor.model.interfaces.Environment;
import de.chordsystem.chordproeditor.model.interfaces.Fingering;

public interface Song {

	/**
	 * @return the title
	 */
	String getTitle();

	/**
	 * @param title the title to set
	 */
	void setTitle(String title);

	/**
	 * @return the subtitle
	 */
	List<String> getSubtitle();

	/**
	 * @param subtitle the subtitle to set
	 */
	void setSubtitle(String subtitle);

	/**
	 * @return the artist
	 */
	List<String> getArtist();

	/**
	 * @param artist the artist to set
	 */
	void setArtist(String artist);

	/**
	 * @return the composer
	 */
	List<String> getComposer();

	/**
	 * @param composer the composer to set
	 */
	void setComposer(String composer);

	/**
	 * @return the lyricist
	 */
	List<String> getLyricist();

	/**
	 * @param lyricist the lyricist to set
	 */
	void setLyricist(String lyricist);

	/**
	 * @return the copyright
	 */
	List<String> getCopyright();

	/**
	 * @param copyright the copyright to set
	 */
	void setCopyright(String copyright);

	/**
	 * @return the album
	 */
	String getAlbum();

	/**
	 * @param album the album to set
	 */
	void setAlbum(String album);

	/**
	 * @return the year
	 */
	int getYear();

	/**
	 * @param year the year to set
	 */
	void setYear(int year);

	/**
	 * @return the key
	 */
	String getKey();

	/**
	 * @param key the key to set
	 */
	void setKey(String key);

	/**
	 * @return the time
	 */
	String getTime();

	/**
	 * @param time the time to set
	 */
	void setTime(String time);

	/**
	 * @return the tempo
	 */
	int getTempo();

	/**
	 * @param tempo the tempo to set
	 */
	void setTempo(int tempo);

	/**
	 * @return the duration
	 */
	int getDuration();

	/**
	 * @param duration the duration to set
	 */
	void setDuration(int duration);

	/**
	 * @return the capo
	 */
	int getCapo();

	/**
	 * @param capo the capo to set
	 */
	void setCapo(int capo);

	/**
	 * @return the meta
	 */
	List<String> getMeta();

	/**
	 * @param meta the meta to set
	 */
	void setMeta(String meta);

	/**
	 * @return the textfont
	 */
	String getTextfont();

	/**
	 * @param textfont the textfont to set
	 */
	void setTextfont(String textfont);

	/**
	 * @return the textsize
	 */
	int getTextsize();

	/**
	 * @param textsize the textsize to set
	 */
	void setTextsize(int textsize);

	/**
	 * @return the textcolour
	 */
	String getTextcolour();

	/**
	 * @param textcolour the textcolour to set
	 */
	void setTextcolour(String textcolour);

	/**
	 * @return the chordcolour
	 */
	String getChordcolour();

	/**
	 * @param chordcolour the chordcolour to set
	 */
	void setChordcolour(String chordcolour);

	/**
	 * @return the isFinished
	 */
	boolean isFinished();

	/**
	 * @param isFinished the isFinished to set
	 */
	void setFinished(boolean isFinished);

	/**
	 * @return the xth fingering
	 */
	Fingering getFingering(int x);

	/**
	 * @return the fingeringList size
	 */
	int getFingeringSize();

	/**
	 * @param fingering the fingering to add
	 */
	void addFingering(Fingering fingering);

	/**
	 * @return the xth environment
	 */
	Environment getEnvironment(int x);

	/**
	 * @return the environment size
	 */
	int getEnvironmentSize();

	/**
	 * @param environment the environment to add
	 */
	void addEnvironment(Environment environment);

	String toString();

}