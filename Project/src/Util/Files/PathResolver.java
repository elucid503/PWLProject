package Util.Files;

public class PathResolver {

    private final String Path;

    public PathResolver(String RelativePath) {

        this.Path = RelativePath;

    }

    public String Resolve() {

        // Resolve the path

        return System.getProperty("user.dir") + "/" + this.Path;

    }

}
