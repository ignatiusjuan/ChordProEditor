package de.chordsystem.Prototype;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChordChecker {
	private static final String chordPattern = "(C|D|E|F|G|A|B)(#|b)?((add|sus|M|m|min|man|aug|dim)?(0|2|4|5|6|7|9|11|13)?(#|b)?)*+/?(C|D|E|F|G|A|B)?(#|b)?";
	
	public static boolean isAChord(String word) {
		boolean isAChord = true;
		
		Pattern.compile(chordPattern).matcher(word).find();
		Matcher m = Pattern.compile(chordPattern).matcher(word);
		m.find();
//		System.out.println(m.group(0));
		return isAChord;
	}

//	--------------Test with complicated chord--------------
//	public static void main(String[] args) {
//		isAChord("Bbsus7#9b9#11#5/Ab");
//		String temp = "Bbsus7#9b9#11#5/Ab";
//		for (int i = 0; i < 15; i++) {
//			System.out.println(temp);
//			temp = ChordTransposer.transposeDown(temp);
//			temp = ChordTransposer.transposeUp(temp);
//		}
//	}
}
