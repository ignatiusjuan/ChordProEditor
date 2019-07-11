package de.chordsystem.Prototype;

import de.chordsystem.chordproeditor.model.abstracts.EnvironmentAbstract;
import de.chordsystem.chordproeditor.model.classes.*;
import de.chordsystem.chordproeditor.model.interfaces.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class RegEx {
	
	private static final String regexTitle 			= "^\\s*\\{\\s*title\\s*:\\s*(?<title>.*?)\\s*\\}\\s*$";
	private static final String regexSubtitle 		= "^\\s*\\{\\s*subtitle\\s*:\\s*(?<subtitle>.*?)\\s*\\}\\s*$";
	private static final String regexArtist 		= "^\\s*\\{\\s*artist\\s*:\\s*(?<artist>.*?)\\s*\\}\\s*$";
	private static final String regexComposer 		= "^\\s*\\{\\s*composer\\s*:\\s*(?<composer>.*?)\\s*\\}\\s*$";
	private static final String regexLyricist 		= "^\\s*\\{\\s*lyricist\\s*:\\s*(?<lyricist>.*?)\\s*\\}\\s*$";
	private static final String regexCopyright 		= "^\\s*\\{\\s*copyright\\s*:\\s*(?<copyright>.*?)\\s*\\}\\s*$";
	private static final String regexAlbum 			= "^\\s*\\{\\s*album\\s*:\\s*(?<album>.*?)\\s*\\}\\s*$";
	private static final String regexYear 			= "^\\s*\\{\\s*year\\s*:\\s*(?<year>\\d*?)\\s*\\}\\s*$";
	private static final String regexKey 			= "^\\s*\\{\\s*key\\s*:\\s*(?<key>.*?)\\s*\\}\\s*$";
	private static final String regexTime 			= "^\\s*\\{\\s*time\\s*:\\s*(?<a>\\d?)\\s*/\\s*(?<b>\\d?)\\s*\\}\\s*$";
	private static final String regexTempo 			= "^\\s*\\{\\s*tempo\\s*:\\s*(?<tempo>\\d*?)\\s*\\}\\s*$";
	private static final String regexDuration 		= "^\\s*\\{\\s*duration\\s*:\\s*(?<minute>\\d*?)\\s*:\\s*(?<second>\\d*?)\\s*\\}\\s*$";
	private static final String regexCapo 			= "^\\s*\\{\\s*capo\\s*:\\s*(?<capo>\\d*?)\\s*\\}\\s*$";
	private static final String regexMeta 			= "^\\s*\\{\\s*meta\\s*:\\s*(.*?)\\s*\\}\\s*$";

	private static final String regexStartofChorus	= "^\\s*\\{\\s*start_of_chorus\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexEndofChorus	= "^\\s*\\{\\s*end_of_chorus\\s*\\}\\s*$";
	private static final String regexStartofVerse	= "^\\s*\\{\\s*start_of_verse\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexEndofVerse		= "^\\s*\\{\\s*end_of_verse\\s*\\}\\s*$";
	private static final String regexStartofTab		= "^\\s*\\{\\s*start_of_tab\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexEndofTab		= "^\\s*\\{\\s*end_of_tab\\s*\\}\\s*$";
	private static final String regexStartofGrid	= "^\\s*\\{\\s*start_of_grid\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexEndofGrid		= "^\\s*\\{\\s*end_of_grid\\s*\\}\\s*$";
	
	private static final String regexChordLyric		= "^\\s*[^\\{\\}]*\\s*$";
	
	/*
	private String[] regexLists = new String[100];
	
	public RegEx() {
		addAllPattern();
	}
	
	private void addAllPattern() {
		regexLists[0] = regexTitle;
		regexLists[1] = regexSubtitle;
		regexLists[2] = regexArtist;
		regexLists[3] = regexComposer;
		regexLists[4] = regexLyricist;
		regexLists[5] = regexCopyright;
		regexLists[6] = regexAlbum;
		regexLists[7] = regexYear;
		regexLists[8] = regexKey;
		regexLists[9] = regexTime;
		regexLists[10] = regexTempo;
		regexLists[11] = regexDuration;
		regexLists[12] = regexCapo;
		regexLists[13] = regexMeta;
	}
	
	public String[] check(String toMatch) {
		String[] result = new String[10];
		for (int i = 0; i < regexLists.length && regexLists[i] != null; i++) {
			//Pattern p = Pattern.compile(regexLists[i]);
			//Matcher m = p.matcher(toMatch);
			//if (m.find()) {
			if (Pattern.compile(regexLists[i]).matcher(toMatch).find()) {
				Matcher m = Pattern.compile(regexLists[i]).matcher(toMatch);
				m.find();
				for (int j = 1; j <= m.groupCount(); j++) {
					result[j-1] = m.group(j);
				}
				break;
			}
		}
		
		return result;
		
	}
	*/
	
	public ChordLyric tryParseChordLyric(String toParse) {
		ChordLyric cl = new ChordLyricImpl();
		
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
		
		cl.setChord(chord.toString());
		cl.setLyric(lyric.toString());
		
		return cl;
	}
	
	public void tryParseLine(String toMatch, Song song) {
		if (Pattern.compile(regexTitle).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTitle).matcher(toMatch);
			m.find();
			song.setTitle(m.group(1));
		} else if (Pattern.compile(regexSubtitle).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexSubtitle).matcher(toMatch);
			m.find();
			song.setSubtitle(m.group(1));
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
		} else if (Pattern.compile(regexStartofChorus).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofChorus).matcher(toMatch);
			m.find();
			EnvironmentAbstract chorus = new ChorusImpl(m.group(1));
			song.setCurrentEnvironment(song.getEnvironmentSize());
			song.addEnvironment(chorus);
		} else if (Pattern.compile(regexEndofChorus).matcher(toMatch).find()) {
			song.setCurrentEnvironment(-1);
			
		} else if (Pattern.compile(regexStartofVerse).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofVerse).matcher(toMatch);
			m.find();
			EnvironmentAbstract verse = new VerseImpl(m.group(1));
			song.setCurrentEnvironment(song.getEnvironmentSize());
			song.addEnvironment(verse);
		} else if (Pattern.compile(regexEndofVerse).matcher(toMatch).find()) {
			song.setCurrentEnvironment(-1);
			
		} else if (Pattern.compile(regexStartofTab).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofTab).matcher(toMatch);
			m.find();
			EnvironmentAbstract tab = new TabImpl(m.group(1));
			song.setCurrentEnvironment(song.getEnvironmentSize());
			song.addEnvironment(tab);
		} else if (Pattern.compile(regexEndofTab).matcher(toMatch).find()) {
			song.setCurrentEnvironment(-1);
			
		} else if (Pattern.compile(regexStartofGrid).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofGrid).matcher(toMatch);
			m.find();
			EnvironmentAbstract grid = new GridImpl(m.group(1));
			song.setCurrentEnvironment(song.getEnvironmentSize());
			song.addEnvironment(grid);
		} else if (Pattern.compile(regexEndofGrid).matcher(toMatch).find()) {
			song.setCurrentEnvironment(-1);
		
		} else if (Pattern.compile(regexChordLyric).matcher(toMatch).find() && (false || song.getEnvironment(song.getCurrentEnvironment()).getType() <= EnvironmentAbstract.VERSE && song.getEnvironment(song.getCurrentEnvironment()).getType() >= 0)) {
			if (song.getCurrentEnvironment() == -1) {
				song.setCurrentEnvironment(song.getEnvironmentSize());
			}
			
			if (song.getEnvironment(song.getCurrentEnvironment()).getType() == EnvironmentAbstract.CHORUS) {
				Chorus temp = (Chorus)song.getEnvironment(song.getCurrentEnvironment());
				temp.addChordLyric(tryParseChordLyric(toMatch));
			} else if (song.getEnvironment(song.getCurrentEnvironment()).getType() == EnvironmentAbstract.VERSE) {
				Verse temp = (Verse)song.getEnvironment(song.getCurrentEnvironment());
				temp.addChordLyric(tryParseChordLyric(toMatch.trim()));
			}
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
		//File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\de\\chordsystem\\Prototype\\" + "Heaven.chordpro");
		File file = new File(System.getProperty("user.dir") + "\\src\\main\\java\\de\\chordsystem\\Prototype\\" + "10000 Reasons.chordpro");
		
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			Song song = new SongImpl();
			
			while ((line = reader.readLine()) != null)
			{
				if (line.trim().length() > 0 && !line.trim().isEmpty())
					tryParseLine(line.trim(),song);
			}
			//System.out.println(song.toString());
			for (int j = 0; j < song.getEnvironmentSize(); j++) {
				if (song.getEnvironment(j).getType() == EnvironmentAbstract.CHORUS) {
					Chorus chorus = (Chorus)song.getEnvironment(j);
					for (int i = 0; i < chorus.getChordLyricSize(); i++) {
						ChordLyric cl = chorus.getChordLyric(i);
						System.out.println(cl.getChord());
						System.out.println(cl.getLyric());
					}
				}
			}
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