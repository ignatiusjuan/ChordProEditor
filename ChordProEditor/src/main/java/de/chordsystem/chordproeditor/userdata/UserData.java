package de.chordsystem.chordproeditor.userdata;

import de.chordsystem.chordproeditor.constant.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.stream.Stream;

public class UserData {
	
	public static String getOpenPath() {
		File folderpath = new File(Filepath.folderpath);
		if (!folderpath.exists())
			return null;
		
		StringBuilder contentBuilder = new StringBuilder();
	    try (Stream<String> stream = Files.lines(Paths.get(Filepath.folderpath), StandardCharsets.UTF_8))
	    {
	        stream.forEach(s -> contentBuilder.append(s));
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    
	    File tmpDir = new File(contentBuilder.toString());
	    if (tmpDir.exists())
	    	return contentBuilder.toString();
	    else 
	    	return null;
	}
	
	public static void setOpenPath(String path) {
		try
		{
    		FileWriter fw=new FileWriter(Filepath.folderpath);
    		fw.write(path);
    		fw.close();
    	} 
		catch(IOException e)
		{
    		e.printStackTrace();
    	}
	}
	
	public static boolean getShowHelpOnStart() {
		File folderpath = new File(Filepath.showHelp);
		if (!folderpath.exists()) {
			
			setShowHelpOnStart(true);
			
			return true;
		}
		
		StringBuilder contentBuilder = new StringBuilder();
	    try (Stream<String> stream = Files.lines(Paths.get(Filepath.showHelp), StandardCharsets.UTF_8))
	    {
	        stream.forEach(s -> contentBuilder.append(s));
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    
	    if (contentBuilder.toString().equals("true"))
	    	return true;
	    else 
	    	return false;
	}
	
	public static void setShowHelpOnStart(Boolean value) {
		try
		{
    		FileWriter fw=new FileWriter(Filepath.showHelp);
    		if (value == true)
    			fw.write("true");
    		else
    			fw.write("false");
    		fw.close();
    	} 
		catch(IOException e)
		{
    		e.printStackTrace();
    	}
	}
	
	public static Locale getLocale() {
		File folderpath = new File(Filepath.locale);
		if (!folderpath.exists()) {
			setLocale("en","US");
			
			return new Locale("en","US");
		}
		
		StringBuilder contentBuilder = new StringBuilder();
	    try (Stream<String> stream = Files.lines(Paths.get(Filepath.locale), StandardCharsets.UTF_8))
	    {
	        stream.forEach(s -> contentBuilder.append(s));
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    
	    String[] values = contentBuilder.toString().split("_");
	    
	    return new Locale(values[0],values[1]);
	}
	
	public static void setLocale(String language, String country) {
		try
		{
    		FileWriter fw=new FileWriter(Filepath.locale);
    		fw.write(language + "_" + country);
    		fw.close();
    	} 
		catch(IOException e)
		{
    		e.printStackTrace();
    	}
	}
}
