package de.chordsystem.chordproeditor.parser;

import java.util.Arrays;
import java.util.List;

public class ChordTransposer {

	private static final List<String> chordLineUpSharp = Arrays.asList("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B");
	private static final List<String> chordLineUpFlat = Arrays.asList("C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B");
	private static final List<String> chordLineUpInt = Arrays.asList("C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B");
	
	public static boolean chordHasBass(String chord) {
		return chord.contains("/") ? true : false;
	}
	
	public static String transposeUp(String chord) {
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
}
