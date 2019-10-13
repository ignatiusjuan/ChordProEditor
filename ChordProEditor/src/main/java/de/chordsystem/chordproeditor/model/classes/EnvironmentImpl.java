package de.chordsystem.chordproeditor.model.classes;

import de.chordsystem.chordproeditor.model.interfaces.Environment;

/**The class environment save each line of ChordPro-Syntax as an Object. If lyric and chord are found, then they will be combined into 1 environment.
 * @author IgnatiusJuanPradipta
 */
public class EnvironmentImpl implements Environment {
	
	public static final int TYPE_NULL = 0;
	public static final int TYPE_CHORUS = 1;
	public static final int TYPE_VERSE = 2;
	public static final int TYPE_TAB = 3;
	public static final int TYPE_GRID = 4;
	public static final int TYPE_INSTRUCTION = 5;
	public static final int TYPE_COMMENT = 6;
	public static final int TYPE_OTHER = 7;
	
	private int 		type;
	private String		title;
	private String 		chord;
	private String 		lyric;
	private boolean		commentIsItalic;
	private boolean		commentInBox;
	
	/**
	 * Default constructor for EnvironmentImpl
	 */
	public EnvironmentImpl() {
		this.type = TYPE_OTHER;
		this.title = "";
		this.chord = "";
		this.lyric = "";
		commentIsItalic = false;
		commentInBox = false;
	}
	
	/**
	 * This function return the type of environment
	 * @return					type of the environment
	 */
	@Override
	public int getType() {
		return type;
	}

	/**
	 * This function set the type of environment
	 * @param		type		type of the environment
	 */
	@Override
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * This function return the title of environment
	 * @return					title of the environment
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * This function set the title of environment
	 * @param		title		title of the environment
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * This function return the chord of environment
	 * @return					chord of the environment
	 */
	@Override
	public String getChord() {
		return chord;
	}

	/**
	 * This function set the title of environment
	 * @param		chord		chord of the environment, will be combined with the next lyric
	 */
	@Override
	public void setChord(String chord) {
		this.chord = chord;
	}

	/**
	 * This function return the lyric of environment, can be song lyric, comment, instruction, or tab/grid content
	 * @return					lyric of the environment
	 */
	@Override
	public String getLyric() {
		return lyric;
	}

	/**
	 * This function set the lyric of environment, can be song lyric, comment, instruction, or tab/grid content
	 * @param		lyric		lyric of the environment, can be song lyric, comment, instruction, or tab/grid content
	 */
	@Override
	public void setLyric(String lyric) {
		this.lyric = lyric;
	}
	
	/**
	 * This function return true if comment is italic
	 * @return					true if comment is italic
	 */
	@Override
	public boolean getCommentIsItalic() {
		return commentIsItalic;
	}
	
	/**
	 * This function set the title of environment
	 * @param		value		if the comment is italic
	 */
	@Override
	public void setCommentIsItalic(boolean value) {
		commentIsItalic = value;
	}
	
	/**
	 * This function return true if comment is in box
	 * @return					true if comment is in box
	 */
	@Override
	public boolean getCommentInBox() {
		return commentInBox;
	}
	
	/**
	 * This function set the title of environment
	 * @param		value		if the comment is in box
	 */
	@Override
	public void setCommentInBox(boolean value) {
		commentInBox = value;
	}
	
	/**
	 * toString function of EnvironmentImpl.java
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if (this.chord.trim().length() > 0 && !this.chord.trim().isEmpty()) {
			sb.append(this.chord.stripTrailing() + "\n");
			sb.append(this.lyric.stripTrailing() + "\n");
		} else if (this.type == TYPE_INSTRUCTION) {
			sb.append("GO TO --> " + this.lyric.stripTrailing() + "\n");
		} else if (this.type == TYPE_COMMENT) {
			sb.append("//" + this.lyric.stripTrailing() + "\n");
		} else
			sb.append(this.lyric.stripTrailing() + "\n");
		
		return sb.toString();
	}
	
}