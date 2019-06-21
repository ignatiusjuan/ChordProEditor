package de.chordsystem.chordproeditor.model.classes;

import de.chordsystem.chordproeditor.model.interfaces.Song;

public class SongImpl implements Song {
	
	private String title;
	private String subtitle;
	private String artist;
	private String composer;
	private String lyricist;
	private String copyright;
	private String album;
	private int year;
	private String key;
	private String time;
	private int tempo;
	private int duration;
	private int capo;
	private String meta;
	
	public SongImpl() {
		super();
		this.title = "";
		this.subtitle = "";
		this.artist = "";
		this.composer = "";
		this.lyricist = "";
		this.copyright = "";
		this.album = "";
		this.year = 0;
		this.key = "";
		this.time = "";
		this.tempo = 0;
		this.duration = 0;
		this.capo = 0;
		this.meta = "";
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the subtitle
	 */
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * @return the composer
	 */
	public String getComposer() {
		return composer;
	}
	/**
	 * @param composer the composer to set
	 */
	public void setComposer(String composer) {
		this.composer = composer;
	}
	/**
	 * @return the lyricist
	 */
	public String getLyricist() {
		return lyricist;
	}
	/**
	 * @param lyricist the lyricist to set
	 */
	public void setLyricist(String lyricist) {
		this.lyricist = lyricist;
	}
	/**
	 * @return the copyright
	 */
	public String getCopyright() {
		return copyright;
	}
	/**
	 * @param copyright the copyright to set
	 */
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	/**
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}
	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the time
	 */
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the tempo
	 */
	public int getTempo() {
		return tempo;
	}
	/**
	 * @param tempo the tempo to set
	 */
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * @return the capo
	 */
	public int getCapo() {
		return capo;
	}
	/**
	 * @param capo the capo to set
	 */
	public void setCapo(int capo) {
		this.capo = capo;
	}
	/**
	 * @return the meta
	 */
	public String getMeta() {
		return meta;
	}
	/**
	 * @param meta the meta to set
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}
	@Override
	public String toString() {
		return String.format(
				"SongImpl [title=%s, subtitle=%s, artist=%s, composer=%s, lyricist=%s, copyright=%s, album=%s, year=%s, key=%s, time=%s, tempo=%s, duration=%s, capo=%s, meta=%s]",
				title, subtitle, artist, composer, lyricist, copyright, album, year, key, time, tempo, duration, capo, meta);
	}

}
