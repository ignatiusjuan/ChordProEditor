package de.chordsystem.chordproeditor.model.abstracts;

import de.chordsystem.chordproeditor.model.interfaces.Environment;

public abstract class EnvironmentAbstract implements Environment {
	
	public static final int CHORUS 	= 0;
	public static final int VERSE	= 1;
	public static final int TAB		= 2;
	public static final int GRID	= 3;
	
	protected int type;
	protected String name;
	protected String colour;				//colour in RGB Hex-Code
	
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}
	
	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
	}
	
	public String toString() {
		return "";
	}
	
	
}
