package Util.Files;

public class Util {

    /**
     * Resolves a relative path to an absolute path, helpful for making everything work across different systems
     * @param path - The relative path
     * @return The absolute path
     * */

    static public String resolvePath(String path) {

        // Resolves the relative path using the system property API
        // A bit niche, but does the job

        return System.getProperty("user.dir") + "/" + path;

    }

}
