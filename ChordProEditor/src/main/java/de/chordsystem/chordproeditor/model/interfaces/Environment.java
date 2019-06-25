package de.chordsystem.chordproeditor.model.interfaces;

public interface Environment {

	/**
	 * @return the type
	 */
	int getType();

	/**
	 * @param type the type to set
	 */
	void setType(int type);

	/**
	 * @return the name
	 */
	String getName();

	/**
	 * @param name the name to set
	 */
	void setName(String name);

	/**
	 * @return the colour
	 */
	String getColour();

	/**
	 * @param colour the colour to set
	 */
	void setColour(String colour);

}