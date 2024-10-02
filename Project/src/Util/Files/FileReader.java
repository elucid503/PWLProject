package Util.Files;

import Util.Strings.Words;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;

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

    public String readAsString() throws Exception {

        // Read the file as a string

        BufferedReader Reader = this.InitReader();

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

    public ArrayList<String> readAsStringList(boolean individualWords) throws Exception {

        // Read the file as a string list

        BufferedReader Reader = InitReader();

        ArrayList<String> FileContents = new java.util.ArrayList<>();

        String Line;

        try {

            while ((Line = Reader.readLine()) != null) {

                FileContents.add(Line);

            }

        } catch (Exception e) {

            // Read exception

            throw new Exception("Error reading file");

        }

        if (individualWords) {

            return Words.convertSentencesToWords(FileContents);

        }

        return FileContents;

    }

    private BufferedReader InitReader() throws Exception {

        // Initialize the reader

        return new BufferedReader(new java.io.FileReader(this.Path));

    }

}