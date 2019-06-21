package de.chordsystem.chordproeditor.model.interfaces;

public interface Song {

	/**
	 * @return the title
	 */
	public String getTitle();

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title);

	/**
	 * @return the subtitle
	 */
	public String getSubtitle();

	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle);

	/**
	 * @return the artist
	 */
	public String getArtist();

	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist);

	/**
	 * @return the composer
	 */
	public String getComposer();

	/**
	 * @param composer the composer to set
	 */
	public void setComposer(String composer);

	/**
	 * @return the lyricist
	 */
	public String getLyricist();

	/**
	 * @param lyricist the lyricist to set
	 */
	public void setLyricist(String lyricist);

	/**
	 * @return the copyright
	 */
	public String getCopyright();

	/**
	 * @param copyright the copyright to set
	 */
	public void setCopyright(String copyright);

	/**
	 * @return the album
	 */
	public String getAlbum();

	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album);

	/**
	 * @return the year
	 */
	public int getYear();

	/**
	 * @param year the year to set
	 */
	public void setYear(int year);

	/**
	 * @return the key
	 */
	public String getKey();

	/**
	 * @param key the key to set
	 */
	public void setKey(String key);

	/**
	 * @return the time
	 */
	public String getTime();

	/**
	 * @param time the time to set
	 */
	public void setTime(String time);

	/**
	 * @return the tempo
	 */
	public int getTempo();

	/**
	 * @param tempo the tempo to set
	 */
	public void setTempo(int tempo);

	/**
	 * @return the duration
	 */
	public int getDuration();

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration);

	/**
	 * @return the capo
	 */
	public int getCapo();

	/**
	 * @param capo the capo to set
	 */
	public void setCapo(int capo);

	/**
	 * @return the meta
	 */
	public String getMeta();

	/**
	 * @param meta the meta to set
	 */
	public void setMeta(String meta);

}