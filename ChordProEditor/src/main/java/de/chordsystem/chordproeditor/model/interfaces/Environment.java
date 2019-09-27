package de.chordsystem.chordproeditor.model.interfaces;

/**
 * Interface for Environment class
 * @author IgnatiusJuanPradipta
 *
 */
public interface Environment {

	/**
	 * This function return the type of environment
	 * @return					type of the environment
	 */
	int getType();

	/**
	 * This function set the type of environment
	 * @param		type		type of the environment
	 */
	void setType(int type);

	/**
	 * This function return the title of environment
	 * @return					title of the environment
	 */
	String getTitle();

	/**
	 * This function set the title of environment
	 * @param		title		title of the environment
	 */
	void setTitle(String title);

	/**
	 * This function return the chord of environment
	 * @return					chord of the environment
	 */
	String getChord();

	/**
	 * This function set the title of environment
	 * @param		chord		chord of the environment, will be combined with the next lyric
	 */
	void setChord(String chord);

	/**
	 * This function return the lyric of environment, can be song lyric, comment, instruction, or tab/grid content
	 * @return					lyric of the environment
	 */
	String getLyric();

	/**
	 * This function set the lyric of environment, can be song lyric, comment, instruction, or tab/grid content
	 * @param		lyric		lyric of the environment, can be song lyric, comment, instruction, or tab/grid content
	 */
	void setLyric(String lyric);

	/**
	 * This function return true if comment is italic
	 * @return					true if comment is italic
	 */
	boolean getCommentIsItalic();

	/**
	 * This function set the title of environment
	 * @param		value		if the comment is italic
	 */
	void setCommentIsItalic(boolean value);

	/**
	 * This function return true if comment is in box
	 * @return					true if comment is in box
	 */
	boolean getCommentInBox();

	/**
	 * This function set the title of environment
	 * @param		value		if the comment is in box
	 */
	void setCommentInBox(boolean value);

	/**
	 * toString function of EnvironmentImpl.java
	 */
	String toString();

}