package de.chordsystem.chordproeditor.model.classes;

import java.util.Arrays;

import de.chordsystem.chordproeditor.model.interfaces.Fingering;

/**
 * This class save a Chord Diagram
 * @author IgnatiusJuanPradipta
 *
 */
public class FingeringImpl implements Fingering {
	
	private String chordName;
	private int strings;
	private int baseFret = -1;
	private int[] frets;
	private int[] fingers;
	
	/**
	 * Helper function to initialise the array if no value given.
	 * @param strings
	 * @param array
	 * @return
	 */
	private final static int[] fill(int strings, int[] array) {
		for (int i = 0; i < strings; i++) {
			array[i] = -1;
		}
		return array;
	}
	
	/**
	 * 3 Parameter Constructor for FingeringImpl
	 * @param chordName			Name of the chord
	 * @param strings			amount of instrument strings
	 */
	public FingeringImpl(String chordName, int strings) {
		this(chordName, strings, 0, fill(strings, new int[strings]));
	}
	
	/**
	 * 4 Parameter Constructor for FingeringImpl
	 * @param chordName			Name of the chord
	 * @param strings			amount of instrument strings
	 * @param baseFret			base fret of the chord
	 * @param frets				fret descriptions of the chord (which frets should be played or ignored)
	 */
	public FingeringImpl(String chordName, int strings, int baseFret, int[] frets) {
		this(chordName, strings, baseFret, frets, fill(strings, new int[strings]));
	}
	
	/**
	 * 5 Parameter Constructor for FingeringImpl
	 * @param chordName			Name of the chord
	 * @param strings			amount of instrument strings
	 * @param baseFret			base fret of the chord
	 * @param frets				fret descriptions of the chord (which frets should be played or ignored)
	 * @param fingers			finger position on the frets
	 */
	public FingeringImpl(String chordName, int strings, int baseFret, int[] frets, int[] fingers) {
		setChordName(chordName);
		setStrings(strings);
		setBaseFret(baseFret);
		setFrets(frets);
		setFingers(fingers);
	}

	/**
	 * @return the chordName
	 */
	@Override
	public String getChordName() {
		return chordName;
	}

	/**
	 * @param chordName the chordName to set
	 */
	@Override
	public void setChordName(String chordName) {
		this.chordName = chordName;
	}

	/**
	 * @return the strings
	 */
	@Override
	public int getStrings() {
		return strings;
	}

	/**
	 * @param strings the strings to set
	 */
	@Override
	public void setStrings(int strings) {
		this.strings = strings;
	}

	/**
	 * @return the baseFret
	 */
	@Override
	public int getBaseFret() {
		return baseFret;
	}

	/**
	 * @param baseFret the baseFret to set
	 */
	@Override
	public void setBaseFret(int baseFret) {
		this.baseFret = baseFret;
	}

	/**
	 * @return the frets
	 */
	@Override
	public int[] getFrets() {
		return frets;
	}

	/**
	 * @param frets the frets to set
	 */
	@Override
	public void setFrets(int[] frets) {
		this.frets = frets;
	}

	/**
	 * @return the fingers
	 */
	@Override
	public int[] getFingers() {
		return fingers;
	}

	/**
	 * @param fingers the fingers to set
	 */
	@Override
	public void setFingers(int[] fingers) {
		this.fingers = fingers;
	}
	
	/**
	 * toString function
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Chord: " + chordName + "\n");
		if (baseFret == 0) {
			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0)
					sb.append('=');
				else {
					if (frets[i / 2] == -1)
						sb.append('x');
					else
						sb.append('=');
				}
			}
			sb.append('\n');
		} else {
			sb.append("=============\n");
			for (int i = 0; i < baseFret-1; i++) {
				sb.append("| | | | | | |\n");
				sb.append("-------------\n");
			}
			sb.append("|=|=|=|=|=|=|\n");
			for (int i = 0; i < 13; i++) {
				if (i % 2 == 0)
					sb.append('-');
				else {
					if (frets[i / 2] == -1)
						sb.append('x');
					else
						sb.append('-');
				}
			}
			sb.append('\n');
		}
		char[] fingersChar = new char[strings];
		for (int i = 0; i < strings; i++) {
			if (fingers[i] == -1) {
				fingersChar[i] = 'o';
			} else {
				fingersChar[i] = (char)('0' + fingers[i]);
			}
		}
		for (int i = 0; i < Arrays.stream(frets).max().getAsInt(); i++) {
			for (int j = 0; j < 13; j++) {
				if (j % 2 == 0)
					sb.append('|');
				else {
					if (frets[j / 2]-1 == i) {
						sb.append(fingersChar[j / 2]);
					} else 
						sb.append(' ');
				}
			}
			sb.append("\n-------------\n");
		}
		return sb.toString();
	}
	
}