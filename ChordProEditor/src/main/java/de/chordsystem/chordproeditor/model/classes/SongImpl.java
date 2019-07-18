package de.chordsystem.chordproeditor.model.classes;

import de.chordsystem.chordproeditor.model.interfaces.*;
//import de.chordsystem.chordproeditor.model.classes.*;
//import de.chordsystem.chordproeditor.model.abstracts.*;

import java.util.List;
import java.util.ArrayList;

public class SongImpl implements Song{
	
	private static final int DEFAULT_STRINGS = 6;
	
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
	private int textfont;
	private int textsize;
	private int textcolour;
	private int chordcolour;
	private boolean isFinished;
	
	private List<Fingering> fingeringList;
	private List<Environment> environmentList;
	
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
		this.isFinished = true;
		this.fingeringList = new ArrayList<Fingering>();
		this.environmentList = new ArrayList<Environment>();
	}
	/**
	 * @return the title
	 */
	@Override
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the subtitle
	 */
	@Override
	public String getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	@Override
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	/**
	 * @return the artist
	 */
	@Override
	public String getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	@Override
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * @return the composer
	 */
	@Override
	public String getComposer() {
		return composer;
	}
	/**
	 * @param composer the composer to set
	 */
	@Override
	public void setComposer(String composer) {
		this.composer = composer;
	}
	/**
	 * @return the lyricist
	 */
	@Override
	public String getLyricist() {
		return lyricist;
	}
	/**
	 * @param lyricist the lyricist to set
	 */
	@Override
	public void setLyricist(String lyricist) {
		this.lyricist = lyricist;
	}
	/**
	 * @return the copyright
	 */
	@Override
	public String getCopyright() {
		return copyright;
	}
	/**
	 * @param copyright the copyright to set
	 */
	@Override
	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}
	/**
	 * @return the album
	 */
	@Override
	public String getAlbum() {
		return album;
	}
	/**
	 * @param album the album to set
	 */
	@Override
	public void setAlbum(String album) {
		this.album = album;
	}
	/**
	 * @return the year
	 */
	@Override
	public int getYear() {
		return year;
	}
	/**
	 * @param year the year to set
	 */
	@Override
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * @return the key
	 */
	@Override
	public String getKey() {
		return key;
	}
	/**
	 * @param key the key to set
	 */
	@Override
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the time
	 */
	@Override
	public String getTime() {
		return time;
	}
	/**
	 * @param time the time to set
	 */
	@Override
	public void setTime(String time) {
		this.time = time;
	}
	/**
	 * @return the tempo
	 */
	@Override
	public int getTempo() {
		return tempo;
	}
	/**
	 * @param tempo the tempo to set
	 */
	@Override
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}
	/**
	 * @return the duration
	 */
	@Override
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	@Override
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * @return the capo
	 */
	@Override
	public int getCapo() {
		return capo;
	}
	/**
	 * @param capo the capo to set
	 */
	@Override
	public void setCapo(int capo) {
		this.capo = capo;
	}
	/**
	 * @return the meta
	 */
	@Override
	public String getMeta() {
		return meta;
	}
	/**
	 * @param meta the meta to set
	 */
	@Override
	public void setMeta(String meta) {
		this.meta = meta;
	}
	/**
	 * @return the isFinished
	 */
	public boolean isFinished() {
		return isFinished;
	}
	/**
	 * @param isFinished the isFinished to set
	 */
	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
	/**
	 * @return the xth fingering
	 */
	@Override
	public Fingering getFingering(int x) {
		return fingeringList.get(x);
	}
	/**
	 * @return the fingeringList size
	 */
	@Override
	public int getFingeringSize() {
		return fingeringList.size();
	}
	/**
	 * @param fingering the fingering to add
	 */
	@Override
	public void addFingering(Fingering fingering) {
		fingeringList.add(fingering);
	}
	/**
	 * @return the xth environment
	 */
	@Override
	public Environment getEnvironment(int x) {
		return environmentList.get(x);
	}
	/**
	 * @return the environment size
	 */
	@Override
	public int getEnvironmentSize() {
		return environmentList.size();
	}
	/**
	 * @param environment the environment to add
	 */
	@Override
	public void addEnvironment(Environment environment) {
		environmentList.add(environment);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		if (title.length() > 0) 
			sb.append("Title: " + title + "\n");
		if (subtitle.length() > 0)
			sb.append("Subtitle: " + subtitle + "\n");
		if (artist.length() > 0)
			sb.append("Artist: " + artist + "\n");
		if (composer.length() > 0)
			sb.append("Composer: " + composer + "\n");
		if (lyricist.length() > 0)
			sb.append("Lyricist: " + lyricist + "\n");
		if (copyright.length() > 0)
			sb.append("Copyright: " + copyright + "\n");
		if (album.length() > 0)
			sb.append("Album: " + album + "\n");
		if (year > 0)
			sb.append("Year: " + year + "\n");
		if (key.length() > 0)
			sb.append("Key: " + key + "\n");
		if (time.length() > 0)
			sb.append("Time: " + time + "\n");
		if (tempo > 0)
			sb.append("Tempo: " + tempo + "\n");
		if (duration > 0)
			sb.append("Duration: " + duration + "seconds\n");
		if (capo > 0)
			sb.append("Capo: " + capo + "\n");
		if (meta.length() > 0)
			sb.append("Meta: " + meta + "\n");
		
		String lastTitle = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
		int lastType = EnvironmentImpl.TYPE_NULL;
		
		for (Environment env : environmentList) {
			if (!(lastType == env.getType() && lastTitle.equals(env.getTitle()))){
				sb.append("\n");
				if (env.getType() == EnvironmentImpl.TYPE_CHORUS)
					sb.append("Chorus: " + env.getTitle());
				else if (env.getType() == EnvironmentImpl.TYPE_VERSE)
					sb.append("Verse: " + env.getTitle());
				else if (env.getType() == EnvironmentImpl.TYPE_TAB)
					sb.append("Tab: " + env.getTitle());
				else if (env.getType() == EnvironmentImpl.TYPE_GRID)
					sb.append("Grid: " + env.getTitle());
				sb.append("\n");
				lastTitle = env.getTitle();
				lastType = env.getType();
			}
			sb.append(env.toString());
		}
		
		for (Fingering f : fingeringList) {
			sb.append("\n" + f.toString());
		}
		
		return sb.toString();
	}

}
