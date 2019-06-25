package de.chordsystem.chordproeditor.model.classes;

import de.chordsystem.chordproeditor.model.abstracts.EnvironmentAbstract;
import de.chordsystem.chordproeditor.model.interfaces.ChordLyric;
import de.chordsystem.chordproeditor.model.interfaces.Verse;

import java.util.List;
import java.util.ArrayList;

public class VerseImpl extends EnvironmentAbstract implements Verse{
	
	private List<ChordLyric> chordlyricList;
	
	public VerseImpl(String name) {
		this(name,"#000000");
	}
	
	public VerseImpl(String name, String colour) {
		super();
		this.type = VERSE;
		this.name = name;
		this.colour = colour;
		this.chordlyricList = new ArrayList<ChordLyric>();
	}
	
	public void addChordLyric(ChordLyric cl) {
		chordlyricList.add(cl);
	}
	
	public ChordLyric getChordLyric(int line) {
		return chordlyricList.get(line);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Verse: " + name + "\n");
		for (ChordLyric cl : chordlyricList) {
			sb.append(cl + "\n");
		}
		return sb.toString();
	}
}
