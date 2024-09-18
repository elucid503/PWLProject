import Util.Files.FileReader;
import Util.Files.PathResolver;

public class Main {

    public static void main(String[] args) {

        // Read the file

        try {

            PathResolver Resolver = new PathResolver("./ExternalFiles/Example.txt");

            System.out.println(Resolver.Resolve());

            FileReader Reader = new FileReader(Resolver.Resolve());

            String FileContents = Reader.ReadAsString();

            System.out.println(FileContents);

        } catch (Exception e) {

            // Error reading file
            System.out.println(e.getMessage());

        }

    }

}
