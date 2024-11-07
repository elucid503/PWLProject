package Util.Misc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for logging messages in a specific format
 * It contains some enumerated values for the log levels
 * */

public class Logging {

    public static final int DEBUG = 0;
    public static final int WARNING = 1;
    public static final int ERROR = 2;

    /**
     * This method logs in the format of [TIME] [LEVEL] MESSAGE
     *
     * @param level - 0 for DEBUG, 1 for WARNING, 2 for ERROR (use class enums)
     * @param message - the message to log
     * @throws IllegalArgumentException - if the level is not 0, 1, or 2 (so use the class enums)
     */

    public static void log(int level, String message) throws IllegalArgumentException {

        if (level > 2 || level < 0) {

            throw new IllegalArgumentException("Invalid log level");

        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String currentTime = now.format(formatter);

        String[] levels = {"DEBUG", "WARNING", "ERROR"};

        System.out.println("[" + currentTime + "] [" + levels[level] + "] " + message);

    }

}
