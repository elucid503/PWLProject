package Util.Files;

import java.nio.file.DirectoryStream;

import java.util.ArrayList;

public class DirReader {

    private final String path;

    public DirReader(String path) {

        this.path = path;

    }

    public ArrayList<String> Read() throws Exception {

        // Read the directory and list all file names

        ArrayList<String> fileNames = new ArrayList<>();

        try (DirectoryStream<java.nio.file.Path> stream = java.nio.file.Files.newDirectoryStream(java.nio.file.Paths.get(this.path))) {

            for (java.nio.file.Path entry : stream) {

                fileNames.add(entry.getFileName().toString());

            }

        } catch (Exception e) {

            throw new Exception("Error reading directory: " + e.getMessage());

        }

        return fileNames;

    }

}
