package Util.Files;

import java.io.BufferedReader;
import java.io.File;

public class FileReader {

    private final String Path;

    public FileReader(String Path) throws IllegalArgumentException {

        // Resolve the path and throw an error if not found

        File ResolvedFile = new File(Path);

        if (!ResolvedFile.exists()) {

            throw new IllegalArgumentException("File not found");

        }

        this.Path = Path;

    }

    public String ReadAsString() throws Exception {

        // Read the file as a string

        BufferedReader Reader = InitReader();

        StringBuilder FileContents = new StringBuilder();
        String Line;

        try {

            while ((Line = Reader.readLine()) != null) {

                FileContents.append(Line);
                FileContents.append("\n");

            }

        } catch (Exception e) {

            // Read exception

            throw new Exception("Error reading file");

        }

        return FileContents.toString();

    }

    // Read as string list

    public java.util.List<String> ReadAsStringList() throws Exception {

        // Read the file as a string list

        BufferedReader Reader = InitReader();

        java.util.List<String> FileContents = new java.util.ArrayList<>();

        String Line;

        try {

            while ((Line = Reader.readLine()) != null) {

                FileContents.add(Line);

            }

        } catch (Exception e) {

            // Read exception

            throw new Exception("Error reading file");

        }

        return FileContents;

    }

    private BufferedReader InitReader() throws Exception {

        // Initialize the reader

        return new BufferedReader(new java.io.FileReader(Path));

    }

}