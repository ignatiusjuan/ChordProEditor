package de.chordsystem.chordproeditor.model.classes;

import java.util.ArrayList;
import java.util.List;

import de.chordsystem.Prototype.TextParser;
import de.chordsystem.chordproeditor.model.interfaces.Environment;
import de.chordsystem.chordproeditor.model.interfaces.Fingering;
import de.chordsystem.chordproeditor.model.interfaces.Song;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SongProperties {
	
	public Song original;
	public Song edited;
	
	public StringProperty title = new SimpleStringProperty();
	public StringProperty subtitle = new SimpleStringProperty();
	public StringProperty artist = new SimpleStringProperty();
	public StringProperty composer = new SimpleStringProperty();
	public StringProperty lyricist = new SimpleStringProperty();
	public StringProperty copyright = new SimpleStringProperty();
	public StringProperty album = new SimpleStringProperty();
	public IntegerProperty year = new SimpleIntegerProperty();
	public StringProperty key = new SimpleStringProperty();
	public StringProperty time = new SimpleStringProperty();
	public IntegerProperty tempo = new SimpleIntegerProperty();
	public IntegerProperty duration = new SimpleIntegerProperty();
	public IntegerProperty capo = new SimpleIntegerProperty();
	public StringProperty meta = new SimpleStringProperty();
	public StringProperty textfont = new SimpleStringProperty();
	public IntegerProperty textsize = new SimpleIntegerProperty();
	public StringProperty textcolour = new SimpleStringProperty();
	public StringProperty chordcolour = new SimpleStringProperty();
	public BooleanProperty isFinished = new SimpleBooleanProperty();
	
	public StringProperty contents = new SimpleStringProperty();
	
	public SongProperties(Song song){
		this.original = song;
		
		this.title.set(song.getTitle());
		this.subtitle.set(arrayListToString(song.getSubtitle()));
		this.artist.set(arrayListToString(song.getArtist()));
		this.composer.set(arrayListToString(song.getComposer()));
		this.lyricist.set(arrayListToString(song.getLyricist()));
		
		this.copyright.set(arrayListToString(song.getCopyright()));
		this.album.set(song.getAlbum());
		this.year.set(song.getYear());
		this.key.set(song.getKey());
		this.time.set(song.getTime());
		
		this.tempo.set(song.getTempo());
		this.duration.set(song.getDuration());
		this.capo.set(song.getCapo());
		this.meta.set(arrayListToString(song.getMeta()));
		this.textfont.set(song.getTextfont());
		
		this.textsize.set(song.getTextsize());
		this.textcolour.set(song.getTextcolour());
		this.chordcolour.set(song.getChordcolour());
		this.isFinished.set(song.isFinished());
		this.contents.set(song.getEnvironmentsAsString());
	}
	
	public String arrayListToString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0)
				sb.append(';');
			sb.append(list.get(i));
		}
			
		return sb.toString();
	}
	
	public List<String> stringToArrayList(String s) {
		List<String> result = new ArrayList<String>();
		while (s.contains(";")) {
			result.add(s.substring(0, s.indexOf(";")));
			s = s.substring(s.indexOf(";") + 1);
		}
		if (!s.isBlank())
			result.add(s);
		return result;
	}
	
	public Song toSong(String wysiwygContent) {
		Song song = new SongImpl();
		song.setTitle(this.title.getValue());
		for (String s : stringToArrayList(this.subtitle.getValue()))
			song.setSubtitle(s);
		for (String s : stringToArrayList(this.artist.getValue()))
			song.setArtist(s);
		for (String s : stringToArrayList(this.composer.getValue()))
			song.setComposer(s);
		for (String s : stringToArrayList(this.lyricist.getValue()))
			song.setLyricist(s);
		for (String s : stringToArrayList(this.copyright.getValue()))
			song.setCopyright(s);
		song.setAlbum(this.album.getValue());
		song.setYear(this.year.getValue());
		song.setKey(this.key.getValue());
		song.setTime(this.time.getValue());
		song.setTempo(this.tempo.getValue());
		song.setDuration(this.duration.getValue());
		song.setCapo(this.capo.getValue());
		for (String s : stringToArrayList(this.meta.getValue()))
			song.setMeta(s);
		song.setTextfont(this.textfont.getValue());
		song.setTextsize(this.textsize.getValue());
		song.setTextcolour(this.textcolour.getValue());
		song.setChordcolour(this.chordcolour.getValue());
		song.setFinished(this.isFinished.getValue());
		
    	TextParser.parseText(song, wysiwygContent);
		
		for (int i = 0; i < original.getFingeringSize(); i++) {
			song.addFingering(original.getFingering(i));
		}
		return song;
	}
	
	//this.fingeringList = new ArrayList<Fingering>();
	//this.environmentList = new ArrayList<Environment>();

}
