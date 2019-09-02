package de.chordsystem.chordproeditor.model.classes;

import java.util.List;

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
	
	public static String arrayListToString(List<String> list) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i != 0)
				sb.append(';');
			sb.append(list.get(i));
		}
			
		return sb.toString();
	}
	
	//this.fingeringList = new ArrayList<Fingering>();
	//this.environmentList = new ArrayList<Environment>();

}
