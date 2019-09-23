package de.chordsystem.Prototype;

import java.util.Arrays;

import de.chordsystem.chordproeditor.model.classes.*;
import de.chordsystem.chordproeditor.model.interfaces.*;

public class ChordProConverter {
	
	private static String combineChordLyric(String chord, String lyric) {
		//System.out.println(chord);
		//System.out.println(lyric);
		StringBuffer sb = new StringBuffer();
		int chordCounter = 0;
		int lyricCounter = 0;
		while (chordCounter < chord.length() && lyricCounter < lyric.length()) {
			if (chordCounter < chord.length())
				if (chord.charAt(chordCounter) == ' ') {
					sb.append(lyric.charAt(lyricCounter));
					chordCounter++;
					lyricCounter++;
				} else {
					sb.append("[");
					while (chord.charAt(chordCounter) != ' ') {
						sb.append(chord.charAt(chordCounter));
						chordCounter++;
					}
					sb.append("]");
				}
		}
		if (chordCounter < chord.length()) {
			sb.append("[");
			while (chordCounter < chord.length()) {
				sb.append(chord.charAt(chordCounter));
				chordCounter++;
			}
			sb.append("]");
		}
		while (lyricCounter < lyric.length()) {
			sb.append(lyric.charAt(lyricCounter));
			lyricCounter++;
		}
		
		return sb.toString();
	}
	
	public static String tryConvertToChordPro(Song song) {
		StringBuffer sb = new StringBuffer();
		
		if (!song.getTitle().isBlank())
			sb.append("{title: " + song.getTitle() + "}\n");
		if (!song.getSubtitle().isEmpty())
			for (String s : song.getSubtitle())
				sb.append("{subtitle: " + s + "}\n");
		if (!song.getArtist().isEmpty())
			for (String s : song.getArtist())
				sb.append("{artist: " + s + "}\n");
		if (!song.getComposer().isEmpty())
			for (String s : song.getComposer())
				sb.append("{composer: " + s + "}\n");
		if (!song.getLyricist().isEmpty())
			for (String s : song.getLyricist())
				sb.append("{lyricist: " + s + "}\n");
		if (!song.getCopyright().isEmpty())
			for (String s : song.getCopyright())
				sb.append("{copyright: " + s + "}\n");
		if (!song.getAlbum().isBlank())
			sb.append("{album: " + song.getAlbum() + "}\n");
		if (song.getYear() > 0)
			sb.append("{year: " + song.getYear() + "}\n");
		if (!song.getKey().isBlank())
			sb.append("{key: " + song.getKey() + "}\n");
		if (!song.getTime().isBlank())
			sb.append("{time: " + song.getTime() + "}\n");
		if (song.getTempo() > 0)
			sb.append("{tempo: " + song.getTempo() + "}\n");
		if (song.getDuration() > 0)
			sb.append("{duration: " + song.getDuration()/60 + ":" + song.getDuration()%60 + "}\n");
		if (song.getCapo() > 0)
			sb.append("{capo: " + song.getCapo() + "}\n");
		if (!song.isFinished())
			sb.append("{new_song}\n");
		if (!song.getTextfont().isEmpty())
			sb.append("{textfont: " + song.getTextfont() + "}\n");
		if (song.getTextsize() > 0)
			sb.append("{textsize: " + song.getTextsize() + "}\n");
		if (!song.getTextcolour().isEmpty())
			sb.append("{textcolour: " + song.getTextcolour() + "}\n");	
		if (!song.getChordcolour().isEmpty())
			sb.append("{chordcolour: " + song.getChordcolour() + "}\n");
		if (!song.getMeta().isEmpty())
			for (String s : song.getMeta())
				sb.append("{meta: " + s + "}\n");
		
		for (int i = 0; i < song.getFingeringSize(); i++) {
			Fingering f = song.getFingering(i);
			sb.append("{chord: " + f.getChordName());
			sb.append(" base-fret " + f.getBaseFret());
			sb.append(" frets");
			for (int j = 0; j < f.getFrets().length; j++) {
				if (f.getFrets()[j] == -1)
					sb.append(" x");
				else
					sb.append(" " + f.getFrets()[j]);
			}
				
			if (Arrays.stream(f.getFingers()).max().getAsInt() > -1) {
				sb.append(" fingers");
				for (int j = 0; j < f.getFingers().length; j++) {
					if (f.getFingers()[j] == -1)
						sb.append(" x");
					else
						sb.append(" " + f.getFingers()[j]);
				}
			}
			sb.append("}\n");
		}
		
		int lastType = -1;
		int openEnvironment = -1;
		String lastTitle = "";
		
		for (int i = 0; i < song.getEnvironmentSize(); i++) {
			Environment e = song.getEnvironment(i);
			if (e.getType() == EnvironmentImpl.TYPE_OTHER) {
				switch(lastType) {
					case EnvironmentImpl.TYPE_CHORUS:
						sb.append("{end_of_chorus}\n");
						break;
					case EnvironmentImpl.TYPE_VERSE:
						sb.append("{end_of_verse}\n");							
						break;
					case EnvironmentImpl.TYPE_TAB:
						sb.append("{end_of_tab}\n");
						break;
					case EnvironmentImpl.TYPE_GRID:
						sb.append("{end_of_grid}\n");
						break;
				}
				openEnvironment = -1;
			}
			if (e.getType() == EnvironmentImpl.TYPE_COMMENT) {
				if (e.getCommentInBox()) {
					sb.append("{comment_box: ");
				} else if (e.getCommentIsItalic()) {
					sb.append("{comment_italic: ");
				} else {
					sb.append("{comment: ");
				}
				sb.append(e.getLyric() + "}\n");
			} else {
				if (openEnvironment != -1 && lastType != e.getType() && !lastTitle.equals(e.getTitle())) {
					switch(lastType) {
						case EnvironmentImpl.TYPE_CHORUS:
							sb.append("{end_of_chorus}\n");
							break;
						case EnvironmentImpl.TYPE_VERSE:
							sb.append("{end_of_verse}\n");							
							break;
						case EnvironmentImpl.TYPE_TAB:
							sb.append("{end_of_tab}\n");
							break;
						case EnvironmentImpl.TYPE_GRID:
							sb.append("{end_of_grid}\n");
							break;
					}
					openEnvironment = -1;
				}
				if (e.getType() == EnvironmentImpl.TYPE_CHORUS) {
					if (openEnvironment == -1) {
						if (!e.getTitle().isBlank())
							sb.append("\n{start_of_chorus: " + e.getTitle() + "}\n");
						else
							sb.append("\n{start_of_chorus}\n");
					}
					String chord = e.getChord();
					String lyric = e.getLyric();
					sb.append(combineChordLyric(chord,lyric) + "\n");
					openEnvironment = e.getType();
					lastType = e.getType();
					lastTitle = e.getTitle();
				} else if (e.getType() == EnvironmentImpl.TYPE_VERSE) {
					if (openEnvironment == -1) {
						if (!e.getTitle().isBlank())
							sb.append("\n{start_of_verse: " + e.getTitle() + "}\n");
						else
							sb.append("\n{start_of_verse}\n");
					}
					String chord = e.getChord();
					String lyric = e.getLyric();
					sb.append(combineChordLyric(chord,lyric) + "\n");
					openEnvironment = e.getType();
					lastType = e.getType();
					lastTitle = e.getTitle();
				} else if (e.getType() == EnvironmentImpl.TYPE_TAB) {
					if (openEnvironment == -1) {
						if (!e.getTitle().isBlank())
							sb.append("\n{start_of_tab: " + e.getTitle() + "}\n");
						else
							sb.append("\n{start_of_tab}\n");
					}
					String lyric = e.getLyric();
					sb.append(lyric + "\n");
					openEnvironment = e.getType();
					lastType = e.getType();
					lastTitle = e.getTitle();
				} else if (e.getType() == EnvironmentImpl.TYPE_GRID) {
					if (openEnvironment == -1) {
						if (!e.getTitle().isBlank())
							sb.append("\n{start_of_grid: " + e.getTitle() + "}\n");
						else
							sb.append("\n{start_of_grid}\n");
					}
					String lyric = e.getLyric();
					sb.append(lyric + "\n");
					openEnvironment = e.getType();
					lastType = e.getType();
					lastTitle = e.getTitle();
				} else if (e.getType() == EnvironmentImpl.TYPE_INSTRUCTION) {
					sb.append("{" + e.getLyric() + "}\n");
				} else if (e.getType() == EnvironmentImpl.TYPE_OTHER) {
					if (!e.getChord().isBlank()) {
						String chord = e.getChord();
						String lyric = e.getLyric();
						sb.append(combineChordLyric(chord,lyric) + "\n");
					} else {
						sb.append(e.getLyric());
					}
				}
			}
		}
		
		if (openEnvironment != -1) {
			switch(openEnvironment) {
				case EnvironmentImpl.TYPE_CHORUS:
					sb.append("{end_of_chorus}\n");
					break;
				case EnvironmentImpl.TYPE_VERSE:
					sb.append("{end_of_verse}\n");							
					break;
				case EnvironmentImpl.TYPE_TAB:
					sb.append("{end_of_tab}\n");
					break;
				case EnvironmentImpl.TYPE_GRID:
					sb.append("{end_of_grid}\n");
					break;
			}
			openEnvironment = -1;
		}
		
		
		return sb.toString();
	}
}
