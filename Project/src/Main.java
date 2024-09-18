import Util.Files.FileReader;
import Util.Files.PathResolver;

public class Main {

    public static void main(String[] args) {

        // Read the file

        try {

            PathResolver Resolver = new PathResolver("./ExternalFiles/Article1.txt");

            System.out.println(Resolver.Resolve());

            FileReader Reader = new FileReader(Resolver.Resolve());

            java.util.List<String> FileContents = Reader.ReadAsStringList();

            System.out.println(FileContents);

        } catch (Exception e) {

            // Error reading file
            System.out.println(e.getMessage());

        }

    }

}
