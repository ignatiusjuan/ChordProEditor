package de.chordsystem.chordproeditor.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class check if a String is a Chord
 * @author IgnatiusJuanPradipta
 *
 */
public class ChordChecker {
	private static final String chordPattern = "^\\s*(C|D|E|F|G|A|B)(#|b)?((add|sus|M|m|min|man|aug|dim)?(0|2|4|5|6|7|9|11|13)?(#|b)?)*+/?(C|D|E|F|G|A|B)?(#|b)?\\s*$";
	public static List<String> customChord = new ArrayList<String>();
	
	/**
	 * This static class check if a String is a Chord
	 * @param 		word		word to be checked
	 * @return					true if the String is a determined chord or a given custom chord
	 */
	public static boolean isAChord(String word) {
		Pattern.compile(chordPattern).matcher(word).find();
		Matcher m = Pattern.compile(chordPattern).matcher(word);
		if (m.find())
			return true;
		for (String s : customChord)
			if (word.equals(s))
				return true;
		return false;
	}

//--------------Test with complicated chord--------------
//	public static void main(String[] args) {
//		System.out.println(isAChord("Cadd"));
//		isAChord("Bbsus7#9b9#11#5/Ab");
//		String temp = "Bbsus7#9b9#11#5/Ab";
//		//for (int i = 0; i < 15; i++) {
//		//	System.out.println(temp);
//		//	temp = ChordTransposer.transposeDown(temp);
//		//	temp = ChordTransposer.transposeUp(temp);
//		//}
//	}
}
