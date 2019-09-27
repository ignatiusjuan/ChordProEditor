package de.chordsystem.chordproeditor.parser;

import java.util.Arrays;
import java.util.List;

import com.jfoenix.controls.JFXTextArea;

/**
 * This class contains all function used for transposing chords
 * @author IgnatiusJuanPradipta
 *
 */
public class ChordTransposer {

	private static final List<String> chordLineUpSharp = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
	private static final List<String> chordLineUpFlat = Arrays.asList("C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B");
	private static final List<String> chordLineUpInt = Arrays.asList("C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B");
	
	/**
	 * Check if a chord has it bass determined
	 * @param 		chord		chord to be checked
	 * @return		true if bass is determined, else false
	 */
	public static boolean chordHasBass(String chord) {
		return chord.contains("/") ? true : false;
	}
	
	/**
	 * Transpose up a chord
	 * @param 		chord		chord to be transposed
	 * @return		transposed chord
	 */
	public static String transposeUp(String chord) {
		String temp = "";
		int baseChordLength = 0;
		StringBuilder sb = new StringBuilder();
		if (chord.length() == 0)
			return null;
		if (chord.length() == 1) {
			temp = chord.substring(0,1);
			baseChordLength = 1;
		} else if (chord.charAt(1) == '#' || chord.charAt(1) == 'b') {
			temp = chord.substring(0,2);
			baseChordLength = 2;
		} else {
			temp = chord.substring(0,1);
			baseChordLength = 1;
		}
		for (int i = 0; i < chordLineUpInt.size(); i++) {
			if (temp.equals(chordLineUpSharp.get(i)) || temp.equals(chordLineUpFlat.get(i))) {
				sb.append(chordLineUpInt.get((i + 1 + chordLineUpInt.size()) % chordLineUpInt.size()));
				break;
			}
		}
		if (chord.length() > temp.length()) {
			if (chordHasBass(chord)) {
				sb.append(chord.substring(baseChordLength, chord.indexOf("/")+1));
				sb.append(transposeUp(chord.substring(chord.indexOf("/")+1,chord.length())));
			} else {
				sb.append(chord.substring(baseChordLength));
			}
		}
		return sb.toString();
	}
	
	/**
	 * Transpose down a chord
	 * @param 		chord		chord to be transposed
	 * @return		transposed chord
	 */
	public static String transposeDown(String chord) {
		String temp = "";
		int baseChordLength = 0;
		StringBuilder sb = new StringBuilder();
		if (chord.length() == 1) {
			temp = chord.substring(0,1);
			baseChordLength = 1;
		} else if (chord.charAt(1) == '#' || chord.charAt(1) == 'b') {
			temp = chord.substring(0,2);
			baseChordLength = 2;
		} else {
			temp = chord.substring(0,1);
			baseChordLength = 1;
		}
		for (int i = 0; i < chordLineUpInt.size(); i++) {
			if (temp.equals(chordLineUpSharp.get(i)) || temp.equals(chordLineUpFlat.get(i))) {
				sb.append(chordLineUpInt.get((i - 1 + chordLineUpInt.size()) % chordLineUpInt.size()));
				break;
			}
		}
		if (chord.length() > temp.length()) {
			if (chordHasBass(chord)) {
				sb.append(chord.substring(baseChordLength, chord.indexOf("/")+1));
				sb.append(transposeDown(chord.substring(chord.indexOf("/")+1,chord.length())));
			} else {
				sb.append(chord.substring(baseChordLength));
			}
		}
		return sb.toString();
	}
	
    /**
     * First function for changing chord. It is however limited to chords with 2 or more space 
     * @param 		valChange 		determine the direction of transpose. 1 for transpose up, -1 for transpose down
     * @param 		lines			lines to be checked for chords
     * @return 						text with transposed chords
     */
    public static String changeChord(int valChange, String text) {
    	String[] lines = text.split("\\n");
		StringBuffer newLines;
    	for (int i = 0; i < lines.length; i++) {
    		newLines = new StringBuffer();
    		if (TextParser.isChordRow(lines[i])) {
    			String[] chords = lines[i].trim().split("\\s+");
    			int chordCounter = 0;
    			for (int j = 0; j < lines[i].length(); j++) {
    				if (lines[i].charAt(j) == ' ') {
    					newLines.append(" ");
    				} else {
    					String oldChord = chords[chordCounter++];
    					String newChord = "";
    					if (valChange == 1)
    						newChord = ChordTransposer.transposeUp(oldChord);
    					else if (valChange == -1)
    						newChord = ChordTransposer.transposeDown(oldChord);
    					if (newChord.length() > oldChord.length()) {
    						j += oldChord.length() + 1;
    						newLines.append(newChord + " ");
    					} else if (newChord.length() < oldChord.length()){
    						j += oldChord.length();
    						newLines.append(newChord + "  ");
    					} else {
    						j += oldChord.length();
    						newLines.append(newChord + " ");
    					}
    				}
    			}
    			if (lines[i].trim().split("\\s+").length == newLines.toString().trim().split("\\s+").length)
    				lines[i] = newLines.toString();
    			else
    				lines[i] = changeChord2(valChange, lines[i]);
    		}
    	}
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < lines.length; i++) {
    		sb.append(lines[i] + "\n");
    	}
    	
    	return sb.toString();
    }
    
    /**
     * Second function for changing chord. It focuses more on keeping all chord intact and ignore the initial chord position.
     * It has disadvantages of moving the chord location a few spaces.
     * @param valChange		determine the direction of transpose. 1 for transpose up, -1 for transpose down
     * @param lines			chords to be transposed
     * @return				transposed chords
     */
    private static String changeChord2(int valChange, String lines) {
    	StringBuffer result = new StringBuffer();
    	String[] chords = lines.trim().split("\\s+");
    	int chordCounter = 0;
    	for (int i = 0; i < lines.length(); i++) {
    		if (Character.isWhitespace(lines.charAt(i))) {
    			result.append(" ");
    		} else {
    			if (valChange == 1)
    				result.append(ChordTransposer.transposeUp(chords[chordCounter]) + " ");
    			else if (valChange == -1)
    				result.append(ChordTransposer.transposeDown(chords[chordCounter]) + " ");
    			i += chords[chordCounter++].length();
    		}
    	}
    	return result.toString();
    }
}
