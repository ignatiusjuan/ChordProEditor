package de.chordsystem.Prototype;
/*
import java.io.*;
import java.util.Locale;
import java.net.URL;

import com.dropbox.core.DbxAppInfo;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.DbxWebAuthNoRedirect;
import com.dropbox.core.http.SSLConfig;
import com.dropbox.core.v1.DbxEntry;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.FolderMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;
*/
public class DropboxConnection {
  /*      
    private static final String ACCESS_TOKEN = "s83D4qQRYqAAAAAAAAAZeWLOcHm01CT_-yY_0sbNdxLgtXDqVPQ3-fHG9L8yPx07";
    private DbxRequestConfig config = null;
    DbxClientV2 client = null;
    FullAccount account = null;
    
    DropboxConnection()
    {
        // Create Dropbox client
        config = new DbxRequestConfig("dropbox/java-tutorial");
        client = new DbxClientV2(config, ACCESS_TOKEN);
        
        try {
            FullAccount account = client.users().getCurrentAccount();
//            System.out.println(account.getName().getDisplayName());
//            System.out.println(account.getCountry());
//            System.out.println(account.getDisabled());
//            System.out.println(account.getEmail());
//            System.out.println(account.getEmailVerified());
//            System.out.println(account.getIsPaired());
//            System.out.println(account.getLocale());
//            System.out.println(account.getProfilePhotoUrl());
//            System.out.println(account.getTeamMemberId());
//            System.out.println(account.getAccountType().toString());
//            
            
        }
        catch (DbxException dbxe)
        {
            dbxe.printStackTrace();
        }
    }
    
    DropboxConnection(String ACCESS_TOKEN)
    {
        // Create Dropbox client
        config = new DbxRequestConfig("dropbox/java-tutorial");
        client = new DbxClientV2(config, ACCESS_TOKEN);
        
        try {
            FullAccount account = client.users().getCurrentAccount();
//            System.out.println(account.getName().getDisplayName());
//            System.out.println(account.getCountry());
//            System.out.println(account.getDisabled());
//            System.out.println(account.getEmail());
//            System.out.println(account.getEmailVerified());
//            System.out.println(account.getIsPaired());
//            System.out.println(account.getLocale());
//            System.out.println(account.getProfilePhotoUrl());
//            System.out.println(account.getTeamMemberId());
//            System.out.println(account.getAccountType().toString());
//            
            
        }
        catch (DbxException dbxe)
        {
            dbxe.printStackTrace();
        }
    }
    
    public void createFolder(String folderName) throws DbxException {
        try {
            FolderMetadata folder = client.files().createFolder(folderName);
            System.out.println(folder.getName());
        } catch (CreateFolderErrorException err) {
            if (err.errorValue.isPath() && err.errorValue.getPathValue().isConflict()) {
                System.out.println("Something already exists at the path.");
            } else {
                System.out.print("Some other CreateFolderErrorException occurred...");
                System.out.print(err.toString());
            }
        } catch (Exception err) {
            System.out.print("Some other Exception occurred...");
            System.out.print(err.toString());
        }
    }

    public void listFolder()
    {
        try
        {
            // Get files and folder metadata from Dropbox root directory
            ListFolderResult result = client.files().listFolder("");
            while (true) {
                for (Metadata metadata : result.getEntries()) {
                    System.out.println(metadata.getPathLower());
                }
    
                if (!result.getHasMore()) {
                    break;
                }
    
                result = client.files().listFolderContinue(result.getCursor());
            }
        }
        catch (DbxException dbxe)
        {
            dbxe.printStackTrace();
        }         
    }
    
    public void uploadFile(String path, String foldername)
    {
//        // Upload "test.txt" to Dropbox
//        try {
//            InputStream in = new FileInputStream(path);
//            FileMetadata metadata = client.files().uploadBuilder(foldername).uploadAndFinish(in);
//        }
//        catch (FileNotFoundException fne)
//        {
//            fne.printStackTrace();
//        }
//        catch (IOException ioe)
//        {
//            ioe.printStackTrace();
//        }
//        catch (DbxException dbxe)
//        {
//            dbxe.printStackTrace();
//        }
    }
    
    public void readFile(String foldername, String filename)
    {
//        try
//        {
//            //output file for download --> storage location on local system to download file
//            FileOutputStream downloadFile = new FileOutputStream("c:/" + filename);
//            try {
//                FileMetadata metadata = client.files().downloadBuilder(foldername).download(downloadFile);
//                } finally
//                {
//                    downloadFile.close();
//                }
//        }
//        //exception handled
//        catch (DbxException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            e.printStackTrace();
//        }
    }
    
    public void deleteFile(String path)
    {
//        try
//        {
//            Metadata metadata = client.files().delete(path);
//        }
//        catch (DbxException dbxe)
//        {
//            dbxe.printStackTrace();
//        }
    }
    
    public static void main(String args[]) throws DbxException, IOException {
        DropboxConnection m = new DropboxConnection();        
        //m.listFolder();
        m.readFile("/", "icon 64x64.png");
        //String folderName = "/test_java_createFolder" + System.currentTimeMillis();
        //m.createFolder(folderName);
        //m.uploadFile("C:/b2b/Overview of Archival and Purge Process.pdf", folderName + "/Overview of Archival and Purge Process.pdf");  
        // m.readFile(folderName + "/Overview of Archival and Purge Process.pdf", "Overview of Archival and Purge Process.pdf");
        //m.deleteFile(folderName+"/Overview of Archival and Purge Process.pdf");
    }*/
}