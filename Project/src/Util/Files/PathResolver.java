package Util.Files;

public class PathResolver {

    private final String Path;

    public PathResolver(String RelativePath) {

        this.Path = RelativePath;

    }

    public String Resolve() {

        // Resolves the relative path using the system property API
        // A bit niche, but does the job

        return System.getProperty("user.dir") + "/" + this.Path;

    }

}
