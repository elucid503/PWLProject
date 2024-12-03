package CLI;

import Topics.Topic;
import Util.Files.Directories;
import Util.Files.Files;
import Util.Scraping.ArticleScraper;

import java.util.*;

/**
 * Interactions handles all user interaction with the program.
 * This also provides wrapper functionality for analysis, creation, selection, addition, or deletion of topics and articles.
 */

public class Interactions {

    public static void startUI() throws Exception {

        Scanner inputScanner = new Scanner(System.in);

        while (true) {

            // The method below is blocking, so the program keeps running until it is done (exitProgram() is called)

            showMainMenu(inputScanner);

        }

    }

    // Misc methods for the UI levels

    private static void showMainMenu(Scanner scanner) throws Exception {

        printWelcomeMessage();
        handleMainUI(scanner);

    }

    private static void printWelcomeMessage() {

        Logging.logUI("Welcome to the Sentiment Analysis Tool", new String[]{Logging.BOLD});
        Logging.horizontalLine();

        printOptions(new String[] {
                "1. Create a Topic",
                "2. List and Manage Topics",
                "3. Exit The Application"
        }, true);

    }

    private static void printOptions(String[] options, boolean lineBreakUnder) {

        for (String option : options) {

            Logging.logUI(option, new String[]{Logging.ITALIC});
            Logging.smartHorizontalLine();

        }

        if (lineBreakUnder) Logging.lineBreak();

    }

    private static String getStringInput(Scanner scanner, String prompt) {

        Logging.logUI(prompt + ": ", new String[]{Logging.BOLD, Logging.CYAN}, true);
        return scanner.nextLine();

    }

    // Methods for each of the UI levels

    private static void handleMainUI(Scanner scanner) throws Exception {

        Logging.logUI("Please select an option: ", new String[]{Logging.BOLD, Logging.BLUE}, true);
        String input = scanner.nextLine();

        switch (input) {
            case "1": createTopic(scanner); break;
            case "2": handleListTopicsUI(scanner); break;
            case "3": exitProgram(); break;
            default: Logging.logUI("Invalid input", new String[]{Logging.BOLD, Logging.RED});
        }

    }

    private static void handleListTopicsUI(Scanner scanner) throws Exception {

        while (true) {

            ArrayList<String> topics = Directories.listChildDirectories("./ExternalFiles");

            if (topics.isEmpty()) {

                Logging.logUI("No topics found. Create one first.", new String[]{Logging.BOLD, Logging.RED});
                return;

            }

            Collections.sort(topics); // Sorts them alphabetically
            Logging.logUI("Available Topics:", new String[]{Logging.BOLD, Logging.CYAN});

            for (int i = 0; i < topics.size(); i++) { // not using an enhanced for loop b/c we can use the index

                Logging.logUI((i + 1) + ". " + topics.get(i), new String[]{Logging.ITALIC});

            }

            Logging.lineBreak();
            String selection = getStringInput(scanner, "Select a topic by number or name, or type 'back' to return to the main menu");

            if (selection.equalsIgnoreCase("back")) return; // Let the upper level method handle the menu change

            // A bit more logic to handle both number and name input

            try {

                int index = Integer.parseInt(selection) - 1;

                if (index >= 0 && index < topics.size()) {

                    handleManageTopicUI(scanner, topics.get(index)); // Move on to the next UI level

                } else {

                    Logging.logUI("Invalid topic number.", new String[]{Logging.BOLD, Logging.RED});

                }

            } catch (NumberFormatException e) {

                // Same thing as above, really, but can just check the name against the list

                if (topics.contains(selection)) {

                    handleManageTopicUI(scanner, selection);

                } else {

                    Logging.logUI("Invalid topic name.", new String[]{Logging.BOLD, Logging.RED});

                }

            }

        }

    }

    private static void handleManageTopicUI(Scanner scanner, String topicName) throws Exception {

        Topic topic = new Topic(topicName); // Instantiate the topic object (lazy at this point)

        try {

            topic.load();

        } catch (Exception e) {

            // Does not exist or something else blew up

            Logging.logUI("Failed to load the topic.", new String[]{Logging.BOLD, Logging.RED});
            return;

        }

        while (true) {

            Logging.horizontalLine();
            Logging.logUI("Managing Topic: " + topicName, new String[]{Logging.BOLD, Logging.CYAN});

            List<String> articles = Directories.read("./ExternalFiles/" + topicName);

            if (articles.isEmpty()) {

                Logging.logUI("No articles found in this topic.", new String[]{Logging.BOLD, Logging.YELLOW});

            } else {

                Logging.logUI("Articles:", new String[]{Logging.BOLD, Logging.GREEN});

                for (int i = 0; i < articles.size(); i++) {

                    Logging.logUI((i + 1) + ". " + articles.get(i), new String[]{Logging.ITALIC});
                }

            }

            Logging.lineBreak();

            printOptions(new String[] {
                    "1. Add an Article",
                    "2. Remove an Article",
                    "3. Run Sentiment Analysis",
                    "4. Delete Topic"
            }, false);

            String action = getStringInput(scanner, "Enter an action number or type 'back' to return to the topics list");

            if (action.equalsIgnoreCase("back")) return; // As before, the upper level method will handle the menu change

            switch (action) {

                case "1": addArticle(scanner, topicName); break;
                case "2": removeArticle(scanner, topicName); break;
                case "3": runSentimentAnalysis(scanner, topic); break;
                case "4": removeTopic(topicName); return;
                default: Logging.logUI("Invalid input", new String[]{Logging.BOLD, Logging.RED});

            }

        }

    }

    // Methods for each of the options

    private static void createTopic(Scanner scanner) {

        String topicName = getStringInput(scanner, "Enter a name for the topic");

        try {

            Directories.create("./ExternalFiles/" + topicName);
            Logging.logUI("Topic created successfully!", new String[]{Logging.BOLD, Logging.GREEN});

        } catch (Exception e) {

            Logging.logUI("An error occurred while creating the topic folder.", new String[]{Logging.BOLD, Logging.RED});

        }

    }

    private static void removeTopic(String topicName) {

        try {

            Directories.delete("./ExternalFiles/" + topicName);
            Logging.logUI("Topic deleted successfully!", new String[]{Logging.BOLD, Logging.GREEN});

        } catch (Exception e) {

            Logging.logUI("An error occurred while deleting the topic folder.", new String[]{Logging.BOLD, Logging.RED});

        }

    }

    private static void runSentimentAnalysis(Scanner scanner, Topic topic) {

        // TODO: Implement

    }

    private static void addArticle(Scanner scanner, String topicName) {

        String articleInput = getStringInput(scanner, "Enter the file path or URL of the article")
                ;
        if (articleInput.contains("http")) { // Is a link

            addArticleFromURL(topicName, articleInput);

        } else {

            addArticleFromFile(topicName, articleInput);

        }

    }

    private static void addArticleFromURL(String topicName, String url) {

        try {

            HashMap<String, String> articleContent = ArticleScraper.detailsAndText(url);

            if (articleContent.isEmpty()) {

                Logging.logUI("Couldn't retrieve article content. The URL may be invalid.", new String[]{Logging.BOLD, Logging.RED});
                return;

            }

            Files.write("./ExternalFiles/" + topicName + "/" + articleContent.get("title") + ".txt", articleContent.get("text"));

            Logging.logUI("Article downloaded and added successfully!", new String[]{Logging.BOLD, Logging.GREEN});

        } catch (Exception e) {

            Logging.logUI("Failed to download the article. The URL may be invalid.", new String[]{Logging.BOLD, Logging.RED});

        }

    }

    private static void addArticleFromFile(String topicName, String filePath) {

        try {

            Files.move(filePath, "./ExternalFiles/" + topicName);
            Logging.logUI("Article added successfully!", new String[]{Logging.BOLD, Logging.GREEN});

        } catch (Exception e) {

            Logging.logUI("Failed to add the article. The file path may be invalid.", new String[]{Logging.BOLD, Logging.RED});
        }

    }


    private static void removeArticle(Scanner scanner, String topicName) {

        String articleName = getStringInput(scanner, "Enter the name of the article to remove");

        try {

            Files.delete("./ExternalFiles/" + topicName + "/" + articleName);
            Logging.logUI("Article removed successfully!", new String[]{Logging.BOLD, Logging.GREEN});

        } catch (Exception e) {

            System.out.println(e);

            Logging.logUI("Failed to remove the article. It may not exist.", new String[]{Logging.BOLD, Logging.RED});

        }

    }

    private static void exitProgram() {

        Logging.logUI("Exiting program...", new String[]{Logging.BOLD, Logging.RED});
        System.exit(0);

    }

}