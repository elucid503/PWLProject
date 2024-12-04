package Util.Files;

import Util.Strings;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.nio.file.Paths;

public class Files {

    /**
     * Reads the file as a string
     * @return The file contents as a string
     * @throws Exception - If the file cannot be read
     */

    public static String readAsString(String path) throws Exception {

        path = Paths.get(path).toString(); // Normalize the path

        // This uses a try-with-resources block to automatically close the reader when it's done.
        // Without this done, subsequent file system calls do not like us...

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {

            StringBuilder fileContents = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {

                fileContents.append(line).append("\n");

            }

            return fileContents.toString();

        } catch (Exception e) {

            throw new Exception("Error reading file", e);

        }

    }

    /**
     * Reads the file as a string list
     * @param individualWords - If true, the file will be read as a list of individual words
     * @return The file contents as a string ArrayList
     * @throws Exception - If the file cannot be read
     */

    public static ArrayList<String> readAsStringList(String path, boolean individualWords) throws Exception {

        path = Paths.get(path).toString(); // Normalize the path

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(path))) {

            ArrayList<String> fileContents = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {

                fileContents.add(line);

            }

            if (individualWords) {

                return Strings.convertSentencesToWords(fileContents);

            }

            return fileContents;

        }

    }

    /**
     * Writes the contents to a file
     * @param contents - The contents to write
     * @throws Exception - If the file cannot be written
     */

    public static void write(String path, String contents) throws Exception {

        path = Paths.get(path).toString(); // Normalize the path

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

    public static void move(String oldPath, String newPath, boolean absolutePath) throws Exception {

        // This method was especially finicky with the path resolution. Docs on the java.nio.Paths class were helpful here.

        if (absolutePath) {
            
            // Remove " from start and end if they copied from Windows explorer 

            if (oldPath.startsWith("\"") && oldPath.endsWith("\"")) {

                oldPath = oldPath.substring(1, oldPath.length() - 1); // Gets the content without the first and last character

            }

            oldPath = Paths.get(oldPath).normalize().toString(); // Normalize the path

        } else {

            oldPath = Util.resolvePath(oldPath); // Resolve the path

        }
        
        newPath = Util.resolvePath(newPath); // Resolve the path

        // move the file

        try {

            java.nio.file.Files.move(java.nio.file.Paths.get(oldPath), java.nio.file.Paths.get(newPath + "/" + java.nio.file.Paths.get(oldPath).getFileName())); // Nice trick to avoid doing oldPath.split here

        } catch (Exception e) {

            e.printStackTrace();

            throw new Exception("Error moving file " + e.getMessage());

        }

    }

    /**
     * Deletes a file
     * @param path - The path of the file to delete
     * @throws Exception - If the file cannot be deleted
     */

    static public void delete(String path) throws Exception {

        path = Paths.get(path).toString(); // Normalize the path
        
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
