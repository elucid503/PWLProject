import java.io.File;

public class FileReader {

    public FileReader(String Path) {

        // Resolve the path and throw an error if not found

        File ResolvedFile = new File(Path);

        if (!ResolvedFile.exists()) {

            throw new IllegalArgumentException("File not found");

        }

    }

}