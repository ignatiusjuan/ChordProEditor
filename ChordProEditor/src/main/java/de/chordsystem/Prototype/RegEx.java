package de.chordsystem.Prototype;

import de.chordsystem.chordproeditor.model.classes.*;
import de.chordsystem.chordproeditor.model.interfaces.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

public class RegEx {
	
	private static final String regexTitlev1		= "^\\s*\\{\\s*title\\s*:\\s*(?<title>.*?)\\s*\\}\\s*$";
	private static final String regexTitlev2		= "^\\s*\\{\\s*t\\s*:\\s*(?<title>.*?)\\s*\\}\\s*$";
	private static final String regexSubtitlev1		= "^\\s*\\{\\s*subtitle\\s*:\\s*(?<subtitle>.*?)\\s*\\}\\s*$";
	private static final String regexSubtitlev2		= "^\\s*\\{\\s*st\\s*:\\s*(?<subtitle>.*?)\\s*\\}\\s*$";
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

	private static final String regexStartofChorusv1= "^\\s*\\{\\s*start_of_chorus\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofChorusv2= "^\\s*\\{\\s*soc\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofChorusv3= "^\\s*\\{\\s*start_of_chorus\\s*\\}\\s*$";
	private static final String regexStartofChorusv4= "^\\s*\\{\\s*soc\\s*\\}\\s*$";
	private static final String regexEndofChorusv1	= "^\\s*\\{\\s*end_of_chorus\\s*\\}\\s*$";
	private static final String regexEndofChorusv2	= "^\\s*\\{\\s*eoc\\s*\\}\\s*$";
	
	private static final String regexStartofVersev1	= "^\\s*\\{\\s*start_of_verse\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofVersev2	= "^\\s*\\{\\s*sov\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofVersev3	= "^\\s*\\{\\s*start_of_verse\\s*\\}\\s*$";
	private static final String regexStartofVersev4	= "^\\s*\\{\\s*sov\\s*\\}\\s*$";
	private static final String regexEndofVersev1	= "^\\s*\\{\\s*end_of_verse\\s*\\}\\s*$";
	private static final String regexEndofVersev2	= "^\\s*\\{\\s*eov\\s*\\}\\s*$";
	
	private static final String regexStartofTabv1	= "^\\s*\\{\\s*start_of_tab\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofTabv2	= "^\\s*\\{\\s*sot\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofTabv3	= "^\\s*\\{\\s*start_of_tab\\s*\\}\\s*$";
	private static final String regexStartofTabv4	= "^\\s*\\{\\s*sot\\s*\\}\\s*$";
	private static final String regexEndofTabv1		= "^\\s*\\{\\s*end_of_tab\\s*\\}\\s*$";
	private static final String regexEndofTabv2		= "^\\s*\\{\\s*eot\\s*\\}\\s*$";
	
	private static final String regexStartofGridv1	= "^\\s*\\{\\s*start_of_grid\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofGridv2	= "^\\s*\\{\\s*sog\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexStartofGridv3	= "^\\s*\\{\\s*start_of_grid\\s*\\}\\s*$";
	private static final String regexStartofGridv4	= "^\\s*\\{\\s*sog\\s*\\}\\s*$";
	private static final String regexEndofGridv1	= "^\\s*\\{\\s*end_of_grid\\s*\\}\\s*$";
	private static final String regexEndofGridv2	= "^\\s*\\{\\s*eog\\s*\\}\\s*$";
	
	private static final String regexGoToChorusv1	= "^\\s*\\{\\s*chorus\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexGoToChorusv2	= "^\\s*\\{\\s*c\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexGoToChorusv3	= "^\\s*\\{\\s*chorus\\s*\\}\\s*$";
	private static final String regexGoToVersev1	= "^\\s*\\{\\s*verse\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexGoToVersev2	= "^\\s*\\{\\s*v\\s*:\\s*(.*?)\\s*\\}\\s*$";
	private static final String regexGoToVersev3	= "^\\s*\\{\\s*verse\\s*\\}\\s*$";
	
	
	private static final String regexChordLyric		= "^\\s*[^\\{\\}]*\\s*$";
	
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
		if (Pattern.compile(regexTitlev1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTitlev1).matcher(toMatch);
			m.find();
			song.setTitle(m.group(1));
		} else if (Pattern.compile(regexTitlev2).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexTitlev2).matcher(toMatch);
			m.find();
			song.setTitle(m.group(1));
		} else if (Pattern.compile(regexSubtitlev1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexSubtitlev1).matcher(toMatch);
			m.find();
			song.setSubtitle(m.group(1));
		} else if (Pattern.compile(regexSubtitlev2).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexSubtitlev2).matcher(toMatch);
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
		} else if (Pattern.compile(regexStartofChorusv1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofChorusv1).matcher(toMatch);
			m.find();
			tempTitle = m.group(1);
			tempType = EnvironmentImpl.TYPE_CHORUS;
		} else if (Pattern.compile(regexStartofChorusv2).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofChorusv2).matcher(toMatch);
			m.find();
			tempTitle = m.group(1);
			tempType = EnvironmentImpl.TYPE_CHORUS;
		} else if (Pattern.compile(regexStartofChorusv3).matcher(toMatch).find() || Pattern.compile(regexStartofChorusv4).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_CHORUS;
		} else if (Pattern.compile(regexEndofChorusv1).matcher(toMatch).find() || Pattern.compile(regexEndofChorusv2).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
		} else if (Pattern.compile(regexStartofVersev1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofVersev1).matcher(toMatch);
			m.find();
			tempTitle = m.group(1);
			tempType = EnvironmentImpl.TYPE_VERSE;
		} else if (Pattern.compile(regexStartofVersev2).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofVersev2).matcher(toMatch);
			m.find();
			tempTitle = m.group(1);
			tempType = EnvironmentImpl.TYPE_VERSE;
		} else if (Pattern.compile(regexStartofVersev3).matcher(toMatch).find() || Pattern.compile(regexStartofVersev4).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_VERSE;
		} else if (Pattern.compile(regexEndofVersev1).matcher(toMatch).find() || Pattern.compile(regexEndofVersev2).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
		} else if (Pattern.compile(regexStartofTabv1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofTabv1).matcher(toMatch);
			m.find();
			tempTitle = m.group(1);
			tempType = EnvironmentImpl.TYPE_TAB;
		} else if (Pattern.compile(regexStartofTabv2).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofTabv2).matcher(toMatch);
			m.find();
			tempTitle = m.group(1);
			tempType = EnvironmentImpl.TYPE_TAB;
		} else if (Pattern.compile(regexStartofTabv3).matcher(toMatch).find() || Pattern.compile(regexStartofTabv4).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_TAB;
		} else if (Pattern.compile(regexEndofTabv1).matcher(toMatch).find() || Pattern.compile(regexEndofTabv2).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
		} else if (Pattern.compile(regexStartofGridv1).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofGridv1).matcher(toMatch);
			m.find();
			tempTitle = m.group(1);
			tempType = EnvironmentImpl.TYPE_GRID;
		} else if (Pattern.compile(regexStartofGridv2).matcher(toMatch).find()) {
			Matcher m = Pattern.compile(regexStartofGridv2).matcher(toMatch);
			m.find();
			tempTitle = m.group(1);
			tempType = EnvironmentImpl.TYPE_GRID;
		} else if (Pattern.compile(regexStartofGridv3).matcher(toMatch).find() || Pattern.compile(regexStartofGridv4).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_GRID;
		} else if (Pattern.compile(regexEndofGridv1).matcher(toMatch).find() || Pattern.compile(regexEndofGridv2).matcher(toMatch).find()) {
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
			Matcher m = Pattern.compile(regexGoToChorusv2).matcher(toMatch);
			m.find();
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("CHORUS: " + m.group(1));
			song.addEnvironment(env);
		} else if (Pattern.compile(regexGoToChorusv3).matcher(toMatch).find()) {
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
			Matcher m = Pattern.compile(regexGoToVersev2).matcher(toMatch);
			m.find();
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("VERSE: " + m.group(1));
			song.addEnvironment(env);
		} else if (Pattern.compile(regexGoToVersev3).matcher(toMatch).find()) {
			tempTitle = "";
			tempType = EnvironmentImpl.TYPE_NULL;
			Environment env = new EnvironmentImpl();
			env.setType(EnvironmentImpl.TYPE_INSTRUCTION);
			env.setLyric("VERSE");
			song.addEnvironment(env);
		} else if (Pattern.compile(regexChordLyric).matcher(toMatch).find()) {
			Environment env = tryParseChordLyric(toMatch);
			env.setTitle(tempTitle);
			env.setType(tempType);
			song.addEnvironment(env);
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
			System.out.println(song.toString());
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