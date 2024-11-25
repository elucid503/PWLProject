package CLI;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class is responsible for logging messages in a specific format
 * It contains some enumerated values for the log levels
 * */

public class Logging {

    // Storage

    private static int lastMessageLength;
    
    // Enumerated values for log levels

    public static final int DEBUG = 0;
    public static final int WARNING = 1;
    public static final int ERROR = 2;
    
    // ANSI escape codes
    
    // Colours

    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";
    
    // Styles
    
    public static final String BOLD = "\u001B[1m";
    public static final String ITALIC = "\u001B[3m";
    public static final String UNDERLINE = "\u001B[4m";
    
    // Reset

    public static final String RESET = "\u001B[0m";

    /**
     * This method logs in the format of [TIME] [LEVEL] MESSAGE
     *
     * @param level - 0 for DEBUG, 1 for WARNING, 2 for ERROR (use class enums)
     * @param message - the message to log
     * @throws IllegalArgumentException - if the level is not 0, 1, or 2 (so use the class enums)
     */

    public static void logInternal(int level, String message) throws IllegalArgumentException {

        if (level > 2 || level < 0) {

            throw new IllegalArgumentException("Invalid log level");

        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        String currentTime = now.format(formatter);

        String[] levels = {"DEBUG", "WARNING", "ERROR"};

        System.out.println("[" + currentTime + "] [" + levels[level] + "] " + message);

    }

    // UI-Based Logging

    /**
     * This method logs with respect to the UI
     * It can optionally provide a colour and style
     *
     * @param message - the message to log
     */

    public static void logUI(String message, String[] styles) {

        lastMessageLength = message.length();

        StringBuilder styleString = new StringBuilder();

        for (String style : styles) {

            styleString.append(style);

        }

        System.out.println(styleString + message + RESET);

    }

    public static void logUI(String message, String[] styles, boolean noNewLine) {

        lastMessageLength = message.length();

        StringBuilder styleString = new StringBuilder();

        for (String style : styles) {

            styleString.append(style);

        }

        if (noNewLine) {

            System.out.print(styleString + message + RESET);

        } else {

            System.out.println(styleString + message + RESET);

        }

    }

    /**
     * This method creates a line break in the console
     **/

    public static void lineBreak() {

        System.out.println();

    }

    // Horizontal Line (and overloads)

    /**
     * This method creates a horizontal line in the console
     */

    public static void horizontalLine() {

        System.out.println("────────────────────────────────────────");

    }

    /**
     * This method creates a horizontal line in the console with a custom length
     *
     * @param length - the length of the line
     */

    public static void horizontalLine(int length) {

        System.out.println("─".repeat(Math.max(0, length)));

    }

    /**
     * This method creates a horizontal line in the console with the length of the last message logged
     **/

    public static void smartHorizontalLine() {

        int length = lastMessageLength;

        System.out.println("─".repeat(Math.max(0, length)));

    }

}
