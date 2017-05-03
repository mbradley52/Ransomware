import java.io.IOException;
import java.nio.file.*;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.File;
import java.io.*;
import java.util.*;
import java.security.*;
import javax.crypto.*;

public class IterateDirectory
{
    private static class WalkDir
    {

        private final Path m_dirPath;       // The Path we are going to walk
        public int m_numFiles;              // The number of Files Found
        public int m_numDirs;               // The number of Directories Found

        // Constructor setting everything up
        public WalkDir(Path dir) 
        {
            m_dirPath = dir;
            m_numFiles = 0;
            m_numDirs = 0;
        }

        // Walk Directory is a recursive function that walks a directory and all
        // of its sub directories
        public void walkDirectory(byte[] key, int cipherMode, Path path) 
        {

            try 
            {
                Files.walkFileTree(m_dirPath, new SimpleFileVisitor<Path>()
                    {
                        // Call preVisitDirectory if the next object we iterate is a Directory
                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException 
                        {
                            // Increment the number of Directories Found
                            m_numDirs++;

                            // Simply print out the directory name to show we were there
                            System.out.println("Directory: " + dir);

                            // Return continue until we achieve the last directory we are walking
                            return FileVisitResult.CONTINUE;

                        }

                        // Upon visiting a file print out the file name and increment the file counter
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException                   
                        {
                            // Increment the number of files found
                            m_numFiles++;

                            File fileToEncrypt = new File(file.toString());
                            // Output the File Name
                            System.out.println("File: " + file);

                            //Encrypt the file
                            try{
                                //System.out.println("function check encrypt inside walk");
                                System.out.println(fileToEncrypt);
                                Encrypt.doCrypto(cipherMode, key, fileToEncrypt);
                            }
                            catch(Exception e)
                            {
                                e.printStackTrace();
                            }
                            // Continue on to the next
                            return FileVisitResult.CONTINUE;
                        }
                    });

            } 
            catch (IOException ex)
            {
                Logger.getLogger(IterateDirectory.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    public static void iterateDir(String directory, byte[] key, int cipherMode) throws IOException 
    {
        // The Path we are going to iterate through. In this case it is my
        // B: and the Test Folder with all its sub folders

        Path start = Paths.get(directory);

        // Lets Create a long and set it to our current time and date so we 
        // can keep track of the time it takes.
        long lStartTime = new Date().getTime();
        System.out.format("Iterating %s ...\n", start);

        // Create our recursive walk class, pass it in a starting directory as a
        // parameter and call walkDirectory() to output all the files and directories
        WalkDir walk = new WalkDir(start);

        walk.walkDirectory(key, cipherMode, start);
       
        // Calculate the end time and then get the difference between start and finish
        // time.
        long lEndTime = new Date().getTime();
        long difference = lEndTime - lStartTime;

        // Print out some stats
        System.out.println("Num Directories:" + walk.m_numDirs);
        System.out.println("Num Files: " + walk.m_numFiles); 
        System.out.println("Elapsed milliseconds: " + difference);

    }
}