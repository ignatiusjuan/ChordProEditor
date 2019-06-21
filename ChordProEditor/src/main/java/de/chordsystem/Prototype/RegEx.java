package de.chordsystem.Prototype;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private String[] regexLists = new String[100];
	
	public RegEx() {
		addAllPattern();
	}
	
	private void addAllPattern() {
		regexLists[0] = regexTitle;
		regexLists[1] = regexSubtitle;
		regexLists[2] = regexArtist;
		regexLists[2] = regexComposer;
		regexLists[3] = regexLyricist;
		regexLists[4] = regexCopyright;
		regexLists[5] = regexAlbum;
		regexLists[6] = regexYear;
		regexLists[7] = regexKey;
		regexLists[8] = regexTime;
		regexLists[9] = regexTempo;
		regexLists[10] = regexDuration;
		regexLists[11] = regexCapo;
		regexLists[12] = regexMeta;
	}
	
	
	public String[] check(String toMatch) {
		String[] result = new String[10];
		for (int i = 0; i < regexLists.length && regexLists[i] != null; i++) {
			Pattern p = Pattern.compile(regexLists[i]);
			Matcher m = p.matcher(toMatch);
			if (m.find()) {
				for (int j = 1; j <= m.groupCount(); j++) {
					result[j-1] = m.group(j);
				}
				break;
			}
		}
		
		return result;
	}
	
	public void start() {
		String[] results = check("{tempo: 150}");
		for (String i : results)
			if (i != null)
				System.out.println(i);
			else
				break;
	}

	public static void main(String[] args) {
		RegEx regex = new RegEx();
		regex.start();
	}
}