package de.chordsystem.chordproeditor.view.javafx.helperclasses;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GetStringFromFile {
	
	public static String get(String pathname) {
		StringBuilder sb = new StringBuilder();
		File file = new File(pathname);
		try
		{
			System.out.println(pathname);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			reader.close();
		}
		catch (Exception e)
		{
			System.err.format("Exception occurred trying to read '%s'.", file);
			e.printStackTrace();
		}
		return sb.toString();
	}
}
