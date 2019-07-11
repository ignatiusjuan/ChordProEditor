package de.chordsystem.chordproeditor.model.classes;

import de.chordsystem.chordproeditor.model.abstracts.EnvironmentAbstract;
import de.chordsystem.chordproeditor.model.interfaces.ChordLyric;
import de.chordsystem.chordproeditor.model.interfaces.Chorus;

import java.util.List;
import java.util.ArrayList;

public class ChorusImpl extends EnvironmentAbstract implements Chorus{
	
	private List<ChordLyric> chordlyricList;
	
	public ChorusImpl(String name) {
		this(name,"#000000");
	}
	
	public ChorusImpl(String name, String colour) {
		super();
		this.type = CHORUS;
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
	
	public int getChordLyricSize() {
		return chordlyricList.size();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Chorus: " + name + "\n");
		for (ChordLyric cl : chordlyricList) {
			sb.append(cl + "\n");
		}
		return sb.toString();
	}
}
