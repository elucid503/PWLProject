package Util.Files;

import java.nio.file.DirectoryStream;

import java.util.ArrayList;

/**
 * This class is responsible for reading a directory
 * */

public class DirReader {

    /**
     * Reads the directory and lists all file names
     * @return An ArrayList of file names
     * @throws Exception - If the directory cannot be read or if the directory does not exist
     * */

    public static ArrayList<String> read(String path) throws Exception {

        // read the directory and list all file names

        ArrayList<String> fileNames = new ArrayList<>();

        try (DirectoryStream<java.nio.file.Path> stream = java.nio.file.Files.newDirectoryStream(java.nio.file.Paths.get(path))) {

            for (java.nio.file.Path entry : stream) {

                fileNames.add(entry.getFileName().toString());

            }

        } catch (Exception e) {

            throw new Exception("Error reading directory: " + e.getMessage());

        }

        return fileNames;

    }

}
