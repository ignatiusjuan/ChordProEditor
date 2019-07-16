package de.chordsystem.chordproeditor.model.interfaces;

public interface Fingering {

	/**
	 * @return the chordName
	 */
	String getChordName();

	/**
	 * @param chordName the chordName to set
	 */
	void setChordName(String chordName);

	/**
	 * @return the strings
	 */
	int getStrings();

	/**
	 * @param strings the strings to set
	 */
	void setStrings(int strings);

	/**
	 * @return the baseFret
	 */
	int getBaseFret();

	/**
	 * @param baseFret the baseFret to set
	 */
	void setBaseFret(int baseFret);

	/**
	 * @return the frets
	 */
	int[] getFrets();

	/**
	 * @param frets the frets to set
	 */
	void setFrets(int[] frets);

	/**
	 * @return the fingers
	 */
	int[] getFingers();

	/**
	 * @param fingers the fingers to set
	 */
	void setFingers(int[] fingers);

}