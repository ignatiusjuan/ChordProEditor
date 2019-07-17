package de.chordsystem.Prototype;

import de.chordsystem.chordproeditor.model.classes.*;
import de.chordsystem.chordproeditor.model.interfaces.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class RegEx {
	
	private static final String regexChordLyric			= "^\\s*[^\\{\\}]*\\s*$";
	
	private static final String regexNewSong			= "^\\s*\\{\\s*\\b(new_song|ns)\\b\\s*\\}\\s*$";
	
	private static final String regexTitle				= "^\\s*\\{\\s*\\b(title|t)\\b\\s*:\\s*(?<title>.*?)\\s*\\}\\s*$";
	private static final String regexSubtitle			= "^\\s*\\{\\s*\\b(subtitle|st)\\b\\s*:\\s*(?<subtitle>.*?)\\s*\\}\\s*$";
	private static final String regexArtist 			= "^\\s*\\{\\s*artist\\s*:\\s*(?<artist>.*?)\\s*\\}\\s*$";
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
	
	private static final String regexDefineChord		= "^\\s*\\{\\s*\\b(define|chord)\\b\\s*:?\\s+(.*?)\\s+base-fret\\s+(.*?)frets\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s*\\}\\s*$";
	private static final String regexDefineChordFingers	= "^\\s*\\{\\s*\\b(define|chord)\\b\\s*:?\\s+(.*?)\\s+base-fret\\s+(.*?)frets\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+fingers\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s*\\}\\s*$";
	
	private String tempTitle = "";
	private int tempType = EnvironmentImpl.TYPE_NULL;
	
	public Environment tryParseChordLyric(String toParse) {
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
	
	public void tryParseLine(String toMatch, Song song) {
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
			tempTitle = "";
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
			tempTitle = "";
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
			tempTitle = "";
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
			tempTitle = "";
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
			env.setLyric("CHORUS: " + m.group(1));
			song.addEnvironment(env);
		} else if (Pattern.compile(regexGoToChorusv2).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("CHORUS");
			song.addEnvironment(env);
		} else if (Pattern.compile(regexGoToVersev1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexGoToVersev1).matcher(toMatch);
			m.find();
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("VERSE: " + m.group(1));
			song.addEnvironment(env);
		} else if (Pattern.compile(regexGoToVersev2).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("VERSE");
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
		} else if (Pattern.compile(regexNewSong).matcher(toMatch).find()) {
			song.setFinished(false);
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
			System.out.println(f);
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
			System.out.println(f);
		} else {
			System.out.println("Parse error: " + toMatch);
			//later --> else = error
		}
		//System.out.println(toMatch);
		/*else if (Pattern.compile(regexTitle).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTitle).matcher(toMatch);
			m.find();
			song.setTitle(m.group(1));
		}*/
	}
	
	public void start() {
		//System.out.println("Working Directory = " + System.getProperty("user.dir"));
		File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\de\\chordsystem\\Prototype\\" + "Heaven.chordpro");
		//File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\de\\chordsystem\\Prototype\\" + "10000 Reasons.chordpro");
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			Song song = new SongImpl();
			
			while ((line = reader.readLine()) != null)
			{
				if (line.trim().length() > 0 && !line.trim().isEmpty())
					tryParseLine(line.trim(),song);
				else if (!(tempTitle.equals("")) && (tempType != EnvironmentImpl.TYPE_NULL)){
					Environment env = new EnvironmentImpl();
					env.setTitle(tempTitle);
					env.setType(tempType);
					song.addEnvironment(env);
				}
			}
			//System.out.println(song.toString());
			reader.close();
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", file);
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		RegEx regex = new RegEx();
		regex.start();
	}
}