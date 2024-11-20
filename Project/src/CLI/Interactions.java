package CLI;

import java.util.Scanner;

/**
 * Welcome to the Sentiment Analysis Tool
 * -------------------
 * 1. Create a Topic > Prompts user for topic name, creates directory
 * 2. Select a Topic > Gives a list of topics, select one to run analysis
 * -------------------
 * 3. Add an Article > Prompts user for article URL (or file name), downloads article
 * 4. Remove an Article > Select topic, then prompts user to remove an article
 * -------------------
 * 5. Exit > Exits the program
 */

public class Interactions {

    public static void startUI() {

        // Print welcome message

        Interactions.printWelcomeMessage();

        // Listen for input

        Scanner inputScanner = new Scanner(System.in);

        // Handle input recursively

        Interactions.getAndHandleInput(inputScanner);

    }

    public static void printWelcomeMessage() {

        Logging.logUI("Welcome to the Sentiment Analysis Tool", new String[]{Logging.BOLD,Logging.CYAN});

        Logging.horizontalLine();

        Logging.logUI("1. Create a Topic", new String[]{Logging.ITALIC});
        Logging.smartHorizontalLine();
        Logging.logUI("2. Select a Topic", new String[]{Logging.ITALIC});
        Logging.smartHorizontalLine();
        Logging.logUI("3. Add an Article", new String[]{Logging.ITALIC});
        Logging.smartHorizontalLine();
        Logging.logUI("4. Remove an Article", new String[]{Logging.ITALIC});
        Logging.smartHorizontalLine();
        Logging.logUI("5. Exit", new String[]{Logging.BOLD,Logging.RED});

    }

    // NEXT UP: Custom message as arg; make do stuff

    public static void getAndHandleInput(Scanner inputScanner) {

        String input = inputScanner.nextLine();

        Interactions.handleInput(input, inputScanner);

    }

    public static void handleInput(String input, Scanner referencedScanner) {

        switch (input) {

            case "1":
                // Create a Topic
                Logging.logUI("Creating a Topic", new String[]{Logging.BOLD, Logging.CYAN});
                break;

            case "2":
                // Select a Topic
                Logging.logUI("Selecting a Topic", new String[]{Logging.BOLD, Logging.CYAN});
                break;

            case "3":
                // Add an Article
                Logging.logUI("Adding an Article", new String[]{Logging.BOLD, Logging.CYAN});
                break;

            case "4":
                // Remove an Article
                Logging.logUI("Removing an Article", new String[]{Logging.BOLD, Logging.CYAN});
                break;

            case "5":
                // Exit
                Logging.logUI("Exiting", new String[]{Logging.BOLD, Logging.RED});
                System.exit(0);
                break;

            default:
                Logging.logUI("Invalid input", new String[]{Logging.BOLD, Logging.RED});
                break;

        }

        Interactions.getAndHandleInput(referencedScanner);

    }

}


