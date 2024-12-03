package Util.Files;

import Util.Strings;

import java.io.BufferedReader;
import java.util.ArrayList;

public class Files {

    /**
     * Reads the file as a string
     * @return The file contents as a string
     * @throws Exception - If the file cannot be read
     */

    static public String readAsString(String path) throws Exception {

        // read the file as a string

        BufferedReader Reader = new BufferedReader(new java.io.FileReader(path));

        StringBuilder FileContents = new StringBuilder();
        String Line;

        try {

            while ((Line = Reader.readLine()) != null) {

                FileContents.append(Line);
                FileContents.append("\n");

            }

        } catch (Exception e) {

            // read exception

            throw new Exception("Error reading file");

        }

        // Close the reader (important as to avoid other file system issues)

        Reader.close();

        return FileContents.toString();

    }

    /**
     * Reads the file as a string list
     * @param individualWords - If true, the file will be read as a list of individual words
     * @return The file contents as a string ArrayList
     * @throws Exception - If the file cannot be read
     */

    static public ArrayList<String> readAsStringList(String path, boolean individualWords) throws Exception {

        // read the file as a string list

        BufferedReader Reader = new BufferedReader(new java.io.FileReader(path));

        ArrayList<String> FileContents = new java.util.ArrayList<>();

        String Line;

        while ((Line = Reader.readLine()) != null) {

            FileContents.add(Line);

        }

        if (individualWords) {

            return Strings.convertSentencesToWords(FileContents);

        }

        // Close the reader

        Reader.close();

        return FileContents;

    }

    /**
     * Writes the contents to a file
     * @param contents - The contents to write
     * @throws Exception - If the file cannot be written
     */

    static public void write(String path, String contents) throws Exception {

        // write the contents to a file

        try {

            java.io.FileWriter Writer = new java.io.FileWriter(path);

            Writer.write(contents);
            Writer.close();

        } catch (Exception e) {

            // write exception

            throw new Exception("Error writing file");

        }

    }

    /**
     * Moves a file to a new location
     * @param oldPath - The old path
     * @param newPath - The new path
     * @throws Exception - If the file cannot be moved
     */

    static public void move(String oldPath, String newPath) throws Exception {

        // resolve the paths

        String resolvedOldPath = Util.resolvePath(oldPath);
        String resolvedNewPath = Util.resolvePath(newPath);

        // move the file to a new location

        try {

            // To use this "move" method, we must wrap the string path representations in Paths.get (for some reason)

            java.nio.file.Files.move(java.nio.file.Paths.get(resolvedOldPath), java.nio.file.Paths.get(resolvedNewPath));

        } catch (Exception e) {

            throw new Exception("Error moving file " + e.getMessage());

        }

    }

    /**
     * Deletes a file
     * @param path - The path of the file to delete
     * @throws Exception - If the file cannot be deleted
     */

    static public void delete(String path) throws Exception {

        // resolve the path

        String resolvedPath = Util.resolvePath(path);

        // delete the file

        try {

            // To use this "delete" method, we must wrap the string path representations in Paths.get (for some reason)

            java.nio.file.Files.delete(java.nio.file.Paths.get(resolvedPath));

        } catch (Exception e) {

            throw new Exception("Error deleting file " + e.getMessage());

        }

    }

}
