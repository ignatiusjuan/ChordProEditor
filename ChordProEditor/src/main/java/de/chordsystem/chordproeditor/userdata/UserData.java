package de.chordsystem.chordproeditor.userdata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class UserData {
	
	public static String getPath() {
		File folderpath = new File("src/main/java/de/chordsystem/chordproeditor/userdata/folderpath");
		if (!folderpath.exists())
			return null;
		
		StringBuilder contentBuilder = new StringBuilder();
	    try (Stream<String> stream = Files.lines(Paths.get("src/main/java/de/chordsystem/chordproeditor/userdata/folderpath"), StandardCharsets.UTF_8))
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
	
	public static void setPath(String path) {
		try
		{
    		FileWriter fw=new FileWriter("src/main/java/de/chordsystem/chordproeditor/userdata/folderpath");
    		fw.write(path);
    		fw.close();
    	} 
		catch(IOException e)
		{
    		e.printStackTrace();
    	}
	}
}
