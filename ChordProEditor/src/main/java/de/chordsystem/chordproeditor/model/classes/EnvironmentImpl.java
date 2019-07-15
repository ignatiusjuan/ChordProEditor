package de.chordsystem.chordproeditor.model.classes;

import de.chordsystem.chordproeditor.model.interfaces.Environment;

public class EnvironmentImpl implements Environment {
	
	public static final int TYPE_NULL = 0;
	public static final int TYPE_CHORUS = 1;
	public static final int TYPE_VERSE = 2;
	public static final int TYPE_TAB = 3;
	public static final int TYPE_GRID = 4;
	public static final int TYPE_INSTRUCTION = 5;
	public static final int TYPE_OTHER = 6;
	
	private int 		type;
	private String		title;
	private String 		chord;
	private String 		lyric;
	
	public EnvironmentImpl() {
		this.type = TYPE_OTHER;
		this.title = "";
		this.chord = "";
		this.lyric = "";
	}
	
	public EnvironmentImpl(boolean isSyntax, int type, String title, String chord, String lyric) {
		this.type = type;
		this.title = title;
		this.chord = chord;
		this.lyric = lyric;
	}

	@Override
	public int getType() {
		return type;
	}

	@Override
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getChord() {
		return chord;
	}

	@Override
	public void setChord(String chord) {
		this.chord = chord;
	}

	@Override
	public String getLyric() {
		return lyric;
	}

	@Override
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (this.type == TYPE_CHORUS || this.type == TYPE_VERSE) {
			if (this.chord.trim().length() > 0 && !this.chord.trim().isEmpty())
				sb.append(this.chord + "\n");
			sb.append(this.lyric + "\n");
		} else if (this.type == TYPE_INSTRUCTION) {
			sb.append("--GO TO " + this.lyric + "--\n");
		} else 
			sb.append(this.lyric + "\n");
		return sb.toString();
	}

	
}