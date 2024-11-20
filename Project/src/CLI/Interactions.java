package CLI;

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

        // Call printWelcomeMessage
        // Listen for inputs
        // Handle inputs recursively

        Interactions.printWelcomeMessage();


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

}


