package Util.Files;

import Util.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * This class is responsible for reading a file
 * It is not static as it instates and checks if the file exists. Afterward, is lazy until a read method is called
 * */

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

    /**
     * Reads the file as a string
     * @return The file contents as a string
     * @throws Exception - If the file cannot be read
     */

    public String readAsString() throws Exception {

        // read the file as a string

        BufferedReader Reader = this.InitReader();

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

        return FileContents.toString();

    }

    /**
     * Reads the file as a string list
     * @param individualWords - If true, the file will be read as a list of individual words
     * @return The file contents as a string ArrayList
     * @throws Exception - If the file cannot be read
     */

    public ArrayList<String> readAsStringList(boolean individualWords) throws Exception {

        // read the file as a string list

        BufferedReader Reader = InitReader();

        ArrayList<String> FileContents = new java.util.ArrayList<>();

        String Line;

        while ((Line = Reader.readLine()) != null) {

            FileContents.add(Line);

        }

        if (individualWords) {

            return Strings.convertSentencesToWords(FileContents);

        }

        return FileContents;

    }

    /**
     * An internal method to handle the initialization of the reader
     * @return The BufferedReader object
     * @throws FileNotFoundException - If the file cannot be found, but should not happen due to class constructor
     */

    private BufferedReader InitReader() throws FileNotFoundException {

        // Initialize the reader

        return new BufferedReader(new java.io.FileReader(this.Path));

    }

}