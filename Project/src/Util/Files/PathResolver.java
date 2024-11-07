package Util.Files;

/**
 * This class is responsible for resolving paths using the static "resolve" method
 * */

public class PathResolver {

    /**
     * Resolves a relative path to an absolute path, helpful for making everything work across different systems
     * @param path - The relative path
     * @return The absolute path
     * */

    static public String resolve(String path) {

        // Resolves the relative path using the system property API
        // A bit niche, but does the job

        return System.getProperty("user.dir") + "/" + path;

    }

}
