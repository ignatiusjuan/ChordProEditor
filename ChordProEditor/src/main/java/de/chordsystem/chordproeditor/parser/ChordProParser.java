package de.chordsystem.chordproeditor.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.chordsystem.chordproeditor.model.classes.EnvironmentImpl;
import de.chordsystem.chordproeditor.model.classes.FingeringImpl;
import de.chordsystem.chordproeditor.model.classes.SongImpl;
import de.chordsystem.chordproeditor.model.interfaces.Environment;
import de.chordsystem.chordproeditor.model.interfaces.Fingering;
import de.chordsystem.chordproeditor.model.interfaces.Song;

/**
 * This class parse a ChordPro file and return it as a Song class
 * @author IgnatiusJuanPradipta
 */
public class ChordProParser {
	
	private static final String regexChordLyric			= "^\\s*[^\\{\\}]*\\s*$";
	
	private static final String regexNewSong			= "^\\s*\\{\\s*\\b(new_song|ns)\\b\\s*\\}\\s*$";
	
	private static final String regexTitle				= "^\\s*\\{\\s*\\b(title|t)\\b\\s*:\\s*(?<title>.*?)\\s*\\}\\s*$";
	private static final String regexSubtitle			= "^\\s*\\{\\s*\\b(subtitle|st)\\b\\s*:\\s*(?<subtitle>.*?)\\s*\\}\\s*$";
	private static final String regexArtist 			= "^\\s*\\{\\s*\\b(artist|a)\\b\\s*:\\s*(?<artist>.*?)\\s*\\}\\s*$";
	private static final String regexComposer 			= "^\\s*\\{\\s*composer\\s*:\\s*(?<composer>.*?)\\s*\\}\\s*$";
	private static final String regexLyricist 			= "^\\s*\\{\\s*lyricist\\s*:\\s*(?<lyricist>.*?)\\s*\\}\\s*$";
	private static final String regexCopyright 			= "^\\s*\\{\\s*copyright\\s*:\\s*(?<copyright>.*?)\\s*\\}\\s*$";
	private static final String regexAlbum 				= "^\\s*\\{\\s*album\\s*:\\s*(?<album>.*?)\\s*\\}\\s*$";
	private static final String regexYear 				= "^\\s*\\{\\s*year\\s*:\\s*(?<year>\\d*?)\\s*\\}\\s*$";
	private static final String regexKey 				= "^\\s*\\{\\s*key\\s*:\\s*(?<key>.*?)\\s*\\}\\s*$";
	private static final String regexTime 				= "^\\s*\\{\\s*time\\s*:\\s*(?<a>\\d?)\\s*/\\s*(?<b>\\d?)\\s*\\}\\s*$";
	private static final String regexTempo 				= "^\\s*\\{\\s*tempo\\s*:\\s*(?<tempo>\\d*?)\\s*\\}\\s*$";
	private static final String regexDuration 			= "^\\s*\\{\\s*duration\\s*:\\s*(?<minute>\\d*?)\\s*:\\s*(?<second>\\d*?)\\s*\\}\\s*$";
	private static final String regexCapo 				= "^\\s*\\{\\s*capo\\s*:\\s*(?<capo>\\d*?)\\s*\\}\\s*$";
	private static final String regexMeta 				= "^\\s*\\{\\s*meta\\s*:\\s*(.*?)\\s*\\}\\s*$";

	private static final String regexStartofChorusv1	= "^\\s*\\{\\s*\\b(start_of_chorus|soc)\\b\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofChorusv2	= "^\\s*\\{\\s*\\b(start_of_chorus|soc)\\b\\s*\\}\\s*$";
	private static final String regexEndofChorus		= "^\\s*\\{\\s*\\b(end_of_chorus|eoc)\\b\\s*\\}\\s*$";
	
	private static final String regexStartofVersev1		= "^\\s*\\{\\s*\\b(start_of_verse|sov)\\b\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofVersev2		= "^\\s*\\{\\s*\\b(start_of_verse|sov)\\b\\s*\\}\\s*$";
	private static final String regexEndofVerse			= "^\\s*\\{\\s*\\b(end_of_verse|eov)\\b\\s*\\}\\s*$";
	
	private static final String regexStartofTabv1		= "^\\s*\\{\\s*\\b(start_of_tab|sot)\\b\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofTabv2		= "^\\s*\\{\\s*\\b(start_of_tab|sot)\\b\\s*\\}\\s*$";
	private static final String regexEndofTab			= "^\\s*\\{\\s*\\b(end_of_tab|eot)\\b\\s*\\}\\s*$";
	
	private static final String regexStartofGridv1		= "^\\s*\\{\\s*\\b(start_of_grid|sog)\\b\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofGridv2		= "^\\s*\\{\\s*\\b(start_of_grid|sog)\\b\\s*\\}\\s*$";
	private static final String regexEndofGrid			= "^\\s*\\{\\s*\\b(end_of_grid|eog)\\b\\s*\\}\\s*$";
	
	private static final String regexGoToChorusv1		= "^\\s*\\{\\s*chorus\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexGoToChorusv2		= "^\\s*\\{\\s*chorus\\s*\\}\\s*$";
	private static final String regexGoToVersev1		= "^\\s*\\{\\s*verse\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexGoToVersev2		= "^\\s*\\{\\s*verse\\s*\\}\\s*$";
	
	private static final String regexCommentNormal		= "^\\s*\\{\\s*\\b(comment|c)\\b\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexCommentBox			= "^\\s*\\{\\s*\\b(comment_box|cb)\\b\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexCommentItalic		= "^\\s*\\{\\s*\\b(comment_italic|ci)\\b\\s*:\\s*(.*?)\\s*\\}\\s*$";
	
	private static final String regexDefineChordSimple	= "^\\s*\\{\\s*\\b(define|chord)\\b\\s*:?\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s*\\}\\s*$";
	private static final String regexDefineChordFingers	= "^\\s*\\{\\s*\\b(define|chord)\\b\\s*:?\\s+(.*?)\\s+base-fret\\s+(.*?)frets\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+fingers\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s*\\}\\s*$";
	private static final String regexDefineChord		= "^\\s*\\{\\s*\\b(define|chord)\\b\\s*:?\\s+(.*?)\\s+base-fret\\s+(.*?)frets\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s*\\}\\s*$";
	
	private static final String regexTextfont			= "^\\s*\\{\\s*textfont\\s*:\\s*(?<textfont>.*?)\\s*\\}\\s*$";
	private static final String regexTextsize			= "^\\s*\\{\\s*textsize\\s*:\\s*(?<textsize>.*?)\\s*\\}\\s*$";
	private static final String regexTextcolour			= "^\\s*\\{\\s*textcolour\\s*:\\s*(?<textcolour>.*?)\\s*\\}\\s*$";;
	private static final String regexChordcolour		= "^\\s*\\{\\s*chordcolour\\s*:\\s*(?<chordcolour>.*?)\\s*\\}\\s*$";
	
	private static String tempTitle = "";
	private static int tempType = EnvironmentImpl.TYPE_NULL;
	private int verseCounter = 0;
	private int chorusCounter = 0;
	private int tabCounter = 0;
	private int gridCounter = 0;
	
	private static List<Integer> errorList = new ArrayList<Integer>();
	private static List<String> errorStrings = new ArrayList<String>();
	
	/**
	 * Convert a String which contains lyric and chord into an environment
	 * @param toParse
	 * @return
	 */
	private Environment tryParseChordLyric(String toParse) {
		Environment env = new EnvironmentImpl();
		
		StringBuffer chord = new StringBuffer();
		StringBuffer lyric = new StringBuffer();
		
		boolean isChord = false;
		
		for (int i = 0; i < toParse.length(); i++) {
			char c = toParse.charAt(i);
			if (c == '[' || c == ']') {
				isChord = !isChord;
			} else {
				if (isChord) {
					chord.append(c);
				} else {
					chord.append(' ');
					lyric.append(c);
				}
			}
		}
		
		env.setChord(chord.toString());
		env.setLyric(lyric.toString());
		
		return env;
	}
	
	/**
	 * Check if a line is a valid ChordPro syntax
	 * @param 			toMatch			String to be checked
	 * @param 			song			a Song object to be updated
	 * @return
	 */
	private boolean tryParseLine(String toMatch, Song song) {
		if (Pattern.compile(regexChordLyric).matcher(toMatch).find()) {
			Environment env = tryParseChordLyric(toMatch);
			env.setTitle(tempTitle);
			env.setType(tempType);
			song.addEnvironment(env);
		} else if (Pattern.compile(regexStartofChorusv1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofChorusv1).matcher(toMatch);
			m.find();
			tempTitle = m.group(2);
			tempType = EnvironmentImpl.TYPE_CHORUS;
		} else if (Pattern.compile(regexStartofChorusv2).matcher(toMatch).find()) {
			chorusCounter++;
			tempTitle = "Chorus " + chorusCounter;
			tempType = EnvironmentImpl.TYPE_CHORUS;
		} else if (Pattern.compile(regexEndofChorus).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
		} else if (Pattern.compile(regexStartofVersev1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofVersev1).matcher(toMatch);
			m.find();
			tempTitle = m.group(2);
			tempType = EnvironmentImpl.TYPE_VERSE;
		} else if (Pattern.compile(regexStartofVersev2).matcher(toMatch).find()) {
			verseCounter++;
			tempTitle = "Verse " + verseCounter;
			tempType = EnvironmentImpl.TYPE_VERSE;
		} else if (Pattern.compile(regexEndofVerse).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
		} else if (Pattern.compile(regexStartofTabv1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofTabv1).matcher(toMatch);
			m.find();
			tempTitle = m.group(2);
			tempType = EnvironmentImpl.TYPE_TAB;
		} else if (Pattern.compile(regexStartofTabv2).matcher(toMatch).find()) {
			tabCounter++;
			tempTitle = "Tab " + tabCounter;
			tempType = EnvironmentImpl.TYPE_TAB;
		} else if (Pattern.compile(regexEndofTab).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
		} else if (Pattern.compile(regexStartofGridv1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofGridv1).matcher(toMatch);
			m.find();
			tempTitle = m.group(2);
			tempType = EnvironmentImpl.TYPE_GRID;
		} else if (Pattern.compile(regexStartofGridv2).matcher(toMatch).find()) {
			gridCounter++;
			tempTitle = "Grid " + gridCounter;
			tempType = EnvironmentImpl.TYPE_GRID;
		} else if (Pattern.compile(regexEndofGrid).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
		} else if (Pattern.compile(regexGoToChorusv1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexGoToChorusv1).matcher(toMatch);
			m.find();
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("chorus: " + m.group(1));
			song.addEnvironment(env);
		} else if (Pattern.compile(regexGoToChorusv2).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("chorus");
			song.addEnvironment(env);
		} else if (Pattern.compile(regexGoToVersev1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexGoToVersev1).matcher(toMatch);
			m.find();
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("verse: " + m.group(1));
			song.addEnvironment(env);
		} else if (Pattern.compile(regexGoToVersev2).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("verse");
			song.addEnvironment(env);
		} else if (Pattern.compile(regexCommentNormal).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexCommentNormal).matcher(toMatch);
			m.find();
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_COMMENT);
			env.setLyric(m.group(2));
			song.addEnvironment(env);
		} else if (Pattern.compile(regexCommentItalic).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexCommentItalic).matcher(toMatch);
			m.find();
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_COMMENT);
			env.setLyric(m.group(2));
			env.setCommentIsItalic(true);
			song.addEnvironment(env);
		} else if (Pattern.compile(regexCommentBox).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexCommentBox).matcher(toMatch);
			m.find();
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_COMMENT);
			env.setLyric(m.group(2));
			env.setCommentInBox(true);
			song.addEnvironment(env);
		} else if (Pattern.compile(regexDefineChordFingers).matcher(toMatch).find()){
			Matcher m = Pattern.compile(regexDefineChordFingers).matcher(toMatch);
			m.find();
			String name = m.group(2);
			int basefret = m.group(3).charAt(0) - '0';
			int[] frets = {-1,-1,-1,-1,-1,-1};
			for (int i = 0; i < 6; i++) {
				if ((m.group(4+i).charAt(0) - '0' >= 0) && (m.group(4+i).charAt(0) - '0' <= 9)) {
					frets[i] = m.group(4+i).charAt(0) - '0';
				}
			}
			int[] fingers = {-1,-1,-1,-1,-1,-1};
			for (int i = 0; i < 6; i++) {
				if ((m.group(10+i).charAt(0) - '0' >= 0) && (m.group(10+i).charAt(0) - '0' <= 9)) {
					fingers[i] = m.group(10+i).charAt(0) - '0';
				}
			}
			Fingering f = new FingeringImpl(name, 6, basefret, frets, fingers);
			song.addFingering(f);
			//System.out.println(f);
		} else if (Pattern.compile(regexDefineChord).matcher(toMatch).find()){
			Matcher m = Pattern.compile(regexDefineChord).matcher(toMatch);
			m.find();
			String name = m.group(2);
			int basefret = m.group(3).charAt(0) - '0';
			int[] frets = {-1,-1,-1,-1,-1,-1};
			for (int i = 0; i < 6; i++) {
				if ((m.group(4+i).charAt(0) - '0' >= 0) && (m.group(4+i).charAt(0) - '0' <= 9)) {
					frets[i] = m.group(4+i).charAt(0) - '0';
				}
			}
			Fingering f = new FingeringImpl(name, 6, basefret, frets);
			song.addFingering(f);
		} else if (Pattern.compile(regexDefineChordSimple).matcher(toMatch).find()){
			Matcher m = Pattern.compile(regexDefineChordSimple).matcher(toMatch);
			m.find();
			String name = m.group(2);
			int basefret = m.group(3).charAt(0) - '0';
			int[] frets = {-1,-1,-1,-1,-1,-1};
			for (int i = 0; i < 6; i++) {
				if ((m.group(4+i).charAt(0) - '0' >= 0) && (m.group(4+i).charAt(0) - '0' <= 9)) {
					frets[i] = m.group(4+i).charAt(0) - '0';
				}
			}
			Fingering f = new FingeringImpl(name, 6, basefret, frets);
			song.addFingering(f);
		} else if (Pattern.compile(regexTitle).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTitle).matcher(toMatch);
			m.find();
			song.setTitle(m.group(2));
		} else if (Pattern.compile(regexSubtitle).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexSubtitle).matcher(toMatch);
			m.find();
			song.setSubtitle(m.group(2));
		} else if (Pattern.compile(regexArtist).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexArtist).matcher(toMatch);
			m.find();
			song.setArtist(m.group(1));
		} else if (Pattern.compile(regexComposer).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexComposer).matcher(toMatch);
			m.find();
			song.setComposer(m.group(1));
		} else if (Pattern.compile(regexLyricist).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexLyricist).matcher(toMatch);
			m.find();
			song.setLyricist(m.group(1));
		} else if (Pattern.compile(regexCopyright).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexCopyright).matcher(toMatch);
			m.find();
			song.setCopyright(m.group(1));
		} else if (Pattern.compile(regexAlbum).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexAlbum).matcher(toMatch);
			m.find();
			song.setAlbum(m.group(1));
		} else if (Pattern.compile(regexYear).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexYear).matcher(toMatch);
			m.find();
			song.setYear(Integer.parseInt(m.group(1)));
		} else if (Pattern.compile(regexKey).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexKey).matcher(toMatch);
			m.find();
			song.setKey(m.group(1));
		} else if (Pattern.compile(regexTime).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTime).matcher(toMatch);
			m.find();
			song.setTime(m.group(1) + "/" + m.group(2));
		} else if (Pattern.compile(regexTempo).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTempo).matcher(toMatch);
			m.find();
			song.setTempo(Integer.parseInt(m.group(1)));
		} else if (Pattern.compile(regexDuration).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexDuration).matcher(toMatch);
			m.find();
			song.setDuration(Integer.parseInt(m.group(1))*60 + Integer.parseInt(m.group(2)));
		} else if (Pattern.compile(regexCapo).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexCapo).matcher(toMatch);
			m.find();
			song.setCapo(Integer.parseInt(m.group(1)));
		} else if (Pattern.compile(regexMeta).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexMeta).matcher(toMatch);
			m.find();
			song.setMeta(m.group(1));
		} else if (Pattern.compile(regexTextfont).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTextfont).matcher(toMatch);
			m.find();
			song.setTextfont(m.group(1));
		} else if (Pattern.compile(regexTextsize).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTextsize).matcher(toMatch);
			m.find();
			song.setTextsize(Integer.parseInt(m.group(1)));
		} else if (Pattern.compile(regexTextcolour).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTextcolour).matcher(toMatch);
			m.find();
			song.setTextcolour(m.group(1));
		} else if (Pattern.compile(regexChordcolour).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexChordcolour).matcher(toMatch);
			m.find();
			song.setChordcolour(m.group(1));
		} else if (Pattern.compile(regexNewSong).matcher(toMatch).find()) {
			song.setFinished(false);
		} else {
			System.out.println("Parse error: " + toMatch);
			return false;
		}
		return true;
	}
	
	/**
	 * This function will try to parse a ChordPro file
	 * @param 			pathname		filepath
	 * @return							parsed Song object
	 */
	public Song tryParseChordPro(String pathname) {
		errorList = new ArrayList<Integer>();
		errorStrings = new ArrayList<String>();
		
		File file = new File(pathname);
		Song song = new SongImpl();
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;	
			int i = 0;
			while ((line = reader.readLine()) != null)
			{
				if (line.trim().length() > 0 && !line.trim().isEmpty()) {
					if (!tryParseLine(line,song))
						errorList.add(i);
						errorStrings.add(line);
				}
				else {
					Environment env = new EnvironmentImpl();
					env.setTitle(tempTitle);
					env.setType(tempType);
					song.addEnvironment(env);
				}
				i++;
			}
			reader.close();
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", file);
			e.printStackTrace();
		}
		
		return song;
	}
	
	/**
	 * This function will try to parse the content of a ChordPro file
	 * @param 			lines		content of a ChordPro file
	 * @return						parsed Song object
	 */
	public Song tryParseChordProString(String lines) {
		errorList = new ArrayList<Integer>();
		errorStrings = new ArrayList<String>();
		Song song = new SongImpl();
		
		try
		{
			String[] separatedLines = lines.split("\n");
			int i = 0;
			for (String line : separatedLines)
			{
				if (line.trim().length() > 0 && !line.trim().isEmpty()) {
					if (!tryParseLine(line,song))
						errorList.add(i);
						errorStrings.add(lines);
				}
				else {
					Environment env = new EnvironmentImpl();
					env.setTitle(tempTitle);
					env.setType(tempType);
					song.addEnvironment(env);
				}
				i++;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return song;
	}
	
	/**
	 * return lines with error from last parse
	 * @return
	 */
	public static List<Integer> getErrorLines(){
		return errorList != null ? errorList : null;
	}
	
	/**
	 * return lines with errorstrings from last parse
	 * @return
	 */
	public static List<String> getErrorStrings(){
		return errorStrings != null ? errorStrings : null;
	}
	
//	public void start() {
//		Song song = tryParseChordPro(System.getProperty("user.dir") + "\\src\\main\\java\\de\\chordsystem\\Prototype\\" + "Heaven.chordpro");
//		//Song song = tryParseChordPro(System.getProperty("user.dir") + "\\src\\main\\java\\de\\chordsystem\\Prototype\\" + "10000 Reasons.chordpro");
//		
//		//System.out.println(song.toString());
//		//File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\de\\chordsystem\\Prototype\\" + "Heaven.chordpro");
//		//File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\de\\chordsystem\\Prototype\\" + "10000 Reasons.chordpro");
//		
//		de.chordsystem.chordproeditor.parser.ChordProConverter converter = new de.chordsystem.chordproeditor.parser.ChordProConverter();
//		
//		//System.out.println(converter.tryConvertToChordPro(song));
//	}

//	public static void main(String[] args) {
//		ChordProParser regex = new ChordProParser();
//		regex.start();
//	}
}