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

        BufferedReader Reader = new BufferedReader(new java.io.FileReader(Path));

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

}