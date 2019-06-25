package de.chordsystem.chordproeditor.model.interfaces;

import de.chordsystem.chordproeditor.model.abstracts.EnvironmentAbstract;

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
	String getSubtitle();

	/**
	 * @param subtitle the subtitle to set
	 */
	void setSubtitle(String subtitle);

	/**
	 * @return the artist
	 */
	String getArtist();

	/**
	 * @param artist the artist to set
	 */
	void setArtist(String artist);

	/**
	 * @return the composer
	 */
	String getComposer();

	/**
	 * @param composer the composer to set
	 */
	void setComposer(String composer);

	/**
	 * @return the lyricist
	 */
	String getLyricist();

	/**
	 * @param lyricist the lyricist to set
	 */
	void setLyricist(String lyricist);

	/**
	 * @return the copyright
	 */
	String getCopyright();

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
	String getMeta();

	/**
	 * @param meta the meta to set
	 */
	void setMeta(String meta);

	/**
	 * @return the xth environment
	 */
	EnvironmentAbstract getEnvironment(int x);
	
	/**
	 * @return the environment size
	 */
	int getEnvironmentSize();
	
	/**
	 * @param environment the environment to add
	 */
	void addEnvironment(EnvironmentAbstract environment);

	/**
	 * @return the currentEnvironment
	 */
	int getCurrentEnvironment();

	/**
	 * @param currentEnvironment the currentEnvironment to set
	 */
	void setCurrentEnvironment(int currentEnvironment);

	String toString();

}