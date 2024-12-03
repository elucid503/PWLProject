package Util.Files;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;
import java.util.ArrayList;

public class Directories {

    /**
     * Creates a directory at the given path
     *
     * @param path - The path of the directory to be created
     * @throws Exception - If the directory cannot be created
     * @return true if the directory was created successfully
     */

    public static boolean create(String path) throws Exception {

        // resolve the path

        String resolvedPath = Util.resolvePath(path);

        // create the directory

        try {

            // again, we must use path.get on the existing (string) path

            java.nio.file.Files.createDirectories(java.nio.file.Paths.get(resolvedPath));

        } catch (Exception e) {

            throw new Exception("Error creating directory: " + e.getMessage());

        }

        return true;

    }

    /**
     * Deletes a directory at the given path
     *
     * @param path - The path of the directory to be deleted
     * @throws Exception - If the directory cannot be deleted
     * @return true if the directory was deleted successfully
     */

    public static boolean delete(String path) throws Exception {

        // resolve the path

        String resolvedPath = Util.resolvePath(path);

        // delete the directory

        try {

            // again, we must use path.get on the existing (string) path

            java.nio.file.Files.delete(java.nio.file.Paths.get(resolvedPath));

        } catch (Exception e) {

            throw new Exception("Error deleting directory: " + e.getMessage());

        }

        return true;

    }

    /**
     * Reads the directory and lists all file names
     * @return An ArrayList of file names
     * @throws Exception - If the directory cannot be read or if the directory does not exist
     * */

    public static ArrayList<String> read(String path) throws Exception {

        // read the directory and list all file names

        ArrayList<String> fileNames = new ArrayList<>();

        try (DirectoryStream<Path> stream = java.nio.file.Files.newDirectoryStream(java.nio.file.Paths.get(path))) {

            for (java.nio.file.Path entry : stream) {

                fileNames.add(entry.getFileName().toString());

            }

        } catch (Exception e) {

            throw new Exception("Error reading directory: " + e.getMessage());

        }

        return fileNames;

    }

    /**
     * Lists child directories of the given directory
     *
     * @param source - The path of the directory to be read for child directories
     * @throws Exception - If the directory cannot be read or if the directory does not exist
     * @return An ArrayList of child directories names
     */

    public static ArrayList<String> listChildDirectories(String source) throws Exception {

        // list child directories of the given directory

        ArrayList<String> directories = new ArrayList<>();

        try (DirectoryStream<Path> stream = java.nio.file.Files.newDirectoryStream(java.nio.file.Paths.get(source))) {

            for (java.nio.file.Path entry : stream) {

                if (java.nio.file.Files.isDirectory(entry)) {

                    directories.add(entry.getFileName().toString());

                }

            }

        } catch (Exception e) {

            throw new Exception("Error listing directories: " + e.getMessage());

        }

        return directories;

    }

}
