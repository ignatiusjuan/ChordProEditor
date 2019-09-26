package de.chordsystem.chordproeditor.model.classes;

import java.util.ArrayList;
import java.util.List;

//import de.chordsystem.chordproeditor.model.classes.*;
//import de.chordsystem.chordproeditor.model.abstracts.*;
import de.chordsystem.chordproeditor.model.interfaces.Environment;
import de.chordsystem.chordproeditor.model.interfaces.Fingering;
import de.chordsystem.chordproeditor.model.interfaces.Song;

public class SongImpl implements Song{
	
	private String title;
	private List<String> subtitle;
	private List<String> artist;
	private List<String> composer;
	private List<String> lyricist;
	private List<String> copyright;
	private String album;
	private int year;
	private String key;
	private String time;
	private int tempo;
	private int duration;
	private int capo;
	private List<String> meta;
	private String textfont;
	private int textsize;
	private String textcolour;
	private String chordcolour;
	private boolean isFinished;
	
	private List<Fingering> fingeringList;
	private List<Environment> environmentList;
	
	public SongImpl() {
		super();
		this.title = "";
		this.subtitle = new ArrayList<String>();
		this.artist = new ArrayList<String>();
		this.composer = new ArrayList<String>();
		this.lyricist = new ArrayList<String>();
		this.copyright = new ArrayList<String>();
		this.album = "";
		this.year = 0;
		this.key = "";
		this.time = "";
		this.tempo = 0;
		this.duration = 0;
		this.capo = 0;
		this.meta = new ArrayList<String>();
		this.textfont = "";
		this.textsize = 0;
		this.textcolour = "";
		this.chordcolour = "";
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
	public List<String> getSubtitle() {
		return subtitle;
	}
	/**
	 * @param subtitle the subtitle to set
	 */
	@Override
	public void setSubtitle(String subtitle) {
		this.subtitle.add(subtitle);
	}
	/**
	 * @return the artist
	 */
	@Override
	public List<String> getArtist() {
		return artist;
	}
	/**
	 * @param artist the artist to set
	 */
	@Override
	public void setArtist(String artist) {
		this.artist.add(artist);
	}
	/**
	 * @return the composer
	 */
	@Override
	public List<String> getComposer() {
		return composer;
	}
	/**
	 * @param composer the composer to set
	 */
	@Override
	public void setComposer(String composer) {
		this.composer.add(composer);
	}
	/**
	 * @return the lyricist
	 */
	@Override
	public List<String> getLyricist() {
		return lyricist;
	}
	/**
	 * @param lyricist the lyricist to set
	 */
	@Override
	public void setLyricist(String lyricist) {
		this.lyricist.add(lyricist);
	}
	/**
	 * @return the copyright
	 */
	@Override
	public List<String> getCopyright() {
		return copyright;
	}
	/**
	 * @param copyright the copyright to set
	 */
	@Override
	public void setCopyright(String copyright) {
		this.copyright.add(copyright);
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
	public List<String> getMeta() {
		return meta;
	}
	/**
	 * @param meta the meta to set
	 */
	@Override
	public void setMeta(String meta) {
		this.meta.add(meta);
	}
	/**
	 * @return the textfont
	 */
	@Override
	public String getTextfont() {
		return textfont;
	}
	/**
	 * @param textfont the textfont to set
	 */
	@Override
	public void setTextfont(String textfont) {
		this.textfont = textfont;
	}
	/**
	 * @return the textsize
	 */
	@Override
	public int getTextsize() {
		return textsize;
	}
	/**
	 * @param textsize the textsize to set
	 */
	@Override
	public void setTextsize(int textsize) {
		this.textsize = textsize;
	}
	/**
	 * @return the textcolour
	 */
	@Override
	public String getTextcolour() {
		return textcolour;
	}
	/**
	 * @param textcolour the textcolour to set
	 */
	@Override
	public void setTextcolour(String textcolour) {
		this.textcolour = textcolour;
	}
	/**
	 * @return the chordcolour
	 */
	@Override
	public String getChordcolour() {
		return chordcolour;
	}
	/**
	 * @param chordcolour the chordcolour to set
	 */
	@Override
	public void setChordcolour(String chordcolour) {
		this.chordcolour = chordcolour;
	}
	/**
	 * @return the isFinished
	 */
	@Override
	public boolean isFinished() {
		return isFinished;
	}
	/**
	 * @param isFinished the isFinished to set
	 */
	@Override
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
	 * 
	 * @return
	 */
	@Override
	public String getEnvironmentsAsString() {
		String lastTitle = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
		int lastType = EnvironmentImpl.TYPE_NULL;
		
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Environment env : environmentList) {
			i++;
			if (!(lastType == env.getType() && lastTitle.equals(env.getTitle()))){
				if (env.getType() == EnvironmentImpl.TYPE_CHORUS)
					sb.append("Chorus: " + env.getTitle() + "\n");
				else if (env.getType() == EnvironmentImpl.TYPE_VERSE)
					sb.append("Verse: " + env.getTitle() + "\n");
				else if (env.getType() == EnvironmentImpl.TYPE_TAB)
					sb.append("Tab: " + env.getTitle() + "\n");
				else if (env.getType() == EnvironmentImpl.TYPE_GRID)
					sb.append("Grid: " + env.getTitle() + "\n");
				lastTitle = env.getTitle();
				lastType = env.getType();
			}
			sb.append(env.toString());
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getEnvironmentsAsString2() {
		String lastTitle = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
		int lastType = EnvironmentImpl.TYPE_NULL;
		
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Environment env : environmentList) {
			i++;
			sb.append("-------" + i + "-------\n");
			if (!(lastType == env.getType() && lastTitle.equals(env.getTitle()))){
				if (env.getType() == EnvironmentImpl.TYPE_CHORUS)
					sb.append("Chorus: " + env.getTitle() + "\n");
				else if (env.getType() == EnvironmentImpl.TYPE_VERSE)
					sb.append("Verse: " + env.getTitle() + "\n");
				else if (env.getType() == EnvironmentImpl.TYPE_TAB)
					sb.append("Tab: " + env.getTitle() + "\n");
				else if (env.getType() == EnvironmentImpl.TYPE_GRID)
					sb.append("Grid: " + env.getTitle() + "\n");
				sb.append("\n");
				lastTitle = env.getTitle();
				lastType = env.getType();
			}
			sb.append(env.toString());
		}
		return sb.toString();
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
		if (subtitle.size() > 0)
			for (String s : subtitle)
				sb.append("Subtitle: " + s + "\n");
		if (artist.size() > 0)
			for (String s : artist)
				sb.append("Artist: " + s + "\n");
		if (composer.size() > 0)
			for (String s : composer)
				sb.append("Composer: " + s + "\n");
		if (lyricist.size() > 0)
			for (String s : lyricist)
				sb.append("Lyricist: " + s + "\n");
		if (copyright.size() > 0)
			for (String s : copyright)
				sb.append("Copyright: " + s + "\n");
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
		if (meta.size() > 0)
			for (String s : meta)
				sb.append("Meta: " + s + "\n");
		
		String lastTitle = "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@";
		int lastType = EnvironmentImpl.TYPE_NULL;
		
		for (Environment env : environmentList) {
			if (!(lastType == env.getType() && lastTitle.equals(env.getTitle()))){
				//sb.append("\n");
				if (env.getType() == EnvironmentImpl.TYPE_CHORUS)
					sb.append("Chorus: " + env.getTitle() + "\n");
				else if (env.getType() == EnvironmentImpl.TYPE_VERSE)
					sb.append("Verse: " + env.getTitle() + "\n");
				else if (env.getType() == EnvironmentImpl.TYPE_TAB)
					sb.append("Tab: " + env.getTitle() + "\n");
				else if (env.getType() == EnvironmentImpl.TYPE_GRID)
					sb.append("Grid: " + env.getTitle() + "\n");
				if (env.getType() != EnvironmentImpl.TYPE_COMMENT) {
					lastTitle = env.getTitle();
					lastType = env.getType();
				}
			}
			sb.append(env.toString());
		}
		
		for (Fingering f : fingeringList) {
			sb.append("\n" + f.toString());
		}
		
		return sb.toString();
	}

}
