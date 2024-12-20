package CLI;

import Articles.Article;
import Articles.ArticleWithScore;
import Topics.Topic;
import Util.Files.Directories;
import Util.Files.Files;
import Util.Misc.Sorting;
import Util.Scraping.ArticleScraper;

import java.util.*; // As to import certain things (Scanner, ArrayList, etc.) from the java.util package since they are used a lot here

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

        Logging.lineBreak();

        Logging.logUI("Welcome to the Sentiment Analysis Tool", new String[]{Logging.BOLD, Logging.CYAN});
        Logging.smartHorizontalLine();

        Logging.lineBreak();

        printOptions(new String[] {
                "1. Create a Topic",
                "2. Manage All Topics",
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

        Logging.logUI(prompt + ": ", new String[]{Logging.BOLD, Logging.BLUE}, true);
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

            ArrayList<String> topics = Directories.listChildDirectories("./ExternalFiles/Topics");

            if (topics.isEmpty()) {

                Logging.logUI("No topics found. Create one first.", new String[]{Logging.BOLD, Logging.RED});
                return;

            }

            Logging.lineBreak();

            Collections.sort(topics); // Sorts them alphabetically
            Logging.logUI("Available Topics", new String[]{Logging.BOLD, Logging.CYAN});
            Logging.smartHorizontalLine();

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

            List<String> articles = Directories.read("./ExternalFiles/Topics/" + topicName);

            Logging.lineBreak();

            if (articles.isEmpty()) {

                Logging.logUI("No articles found in this topic.", new String[]{Logging.BOLD, Logging.YELLOW});

            } else {

                Logging.logUI("Articles in " + "\"" + topicName + "\"", new String[]{Logging.BOLD, Logging.CYAN});

                Logging.smartHorizontalLine();

                for (int i = 0; i < articles.size(); i++) {

                    Logging.logUI((i + 1) + ". " + articles.get(i), new String[]{});

                }

            }

            Logging.lineBreak();

            printOptions(new String[] {
                    "1. Add an Article",
                    "2. Remove an Article",
                    "3. Run Sentiment Analysis",
                    "4. Delete Topic and Articles"
            }, false);

            Logging.lineBreak();

            String action = getStringInput(scanner, "Enter an action number or type 'back' to return to the topics list");

            if (action.equalsIgnoreCase("back")) return; // As before, the upper level method will handle the menu change

            switch (action) {

                case "1": addArticle(scanner, topicName); break;
                case "2": removeArticle(scanner, topicName); break;
                case "3": runSentimentAnalysis(topic); break;
                case "4": {

                    boolean wasActuallyRemoved = removeTopic(scanner, topicName);

                    // Handle the UI differently based on whether the topic was actually removed or not

                    if (wasActuallyRemoved)
                        return; // Exit the loop and go back to the topics list
                    else 
                        break; // Continue the loop

                }
                default: Logging.logUI("Invalid input", new String[]{Logging.BOLD, Logging.RED});

            }

        }

    }

    // Methods for each of the options

    private static void createTopic(Scanner scanner) {

        String topicName = getStringInput(scanner, "Enter a name for the topic");

        try {

            Directories.create("./ExternalFiles/Topics/" + topicName);
            Logging.logUI("Topic created successfully!", new String[] { Logging.BOLD, Logging.GREEN });

        } catch (Exception e) {

            Logging.logUI("An error occurred while creating the topic folder.",
                    new String[] { Logging.BOLD, Logging.RED });

        }

    }

    /*
     * General UI Handler to remove a topic
     * @return boolean - whether the topic was actually removed or not
     */

    private static boolean removeTopic(Scanner scanner, String topicName) {

        // Can't use getStringInput b/c it assumes blue colour. This is a limitation. (same situation below in removeArticle)

        Logging.logUI("Are you sure? Type Yes (y) or No (n): ", new String[]{Logging.BOLD, Logging.YELLOW}, true);
        String userConfirm = scanner.nextLine();

        if (userConfirm.equals("yes") || userConfirm.equals("y")) {

            try {

                Directories.delete("./ExternalFiles/Topics/" + topicName);
                Logging.logUI("Topic deleted successfully!", new String[] { Logging.BOLD, Logging.GREEN });

                return true;
                
            } catch (Exception e) {

                Logging.logUI("An error occurred while deleting the topic folder.", new String[]{Logging.BOLD, Logging.RED});

            }

        } else {

            Logging.logUI("Topic deletion cancelled.", new String[] { Logging.BOLD, Logging.RED });

        }
        
        return false;

    }

    private static void runSentimentAnalysis(Topic topic) throws Exception {

        topic.load(); // Reload in case articles have been added or removed
        topic.process(); // Make sure the articles article processed

        ArrayList<ArticleWithScore> articleWithScoreAdded = new ArrayList<>();

        // Individual Article Analysis

        for (Article article : topic.articleList) {

            articleWithScoreAdded.add(new ArticleWithScore(article, article.sentimentRanker.rank()));

        }

        List<ArticleWithScore> articlesWithDescendingScore = Sorting.sortObjectByFloatProperyCount(articleWithScoreAdded, "score"); // We need to use the float version here

        // Print Top & Individual Analysis

        Article richestVocab = topic.articleManager.getArticleWithRichestVocab();

        Article mostPositiveArticle = articlesWithDescendingScore.getFirst().article;
        Article leastPositiveArticle = articlesWithDescendingScore.getLast().article;

        Logging.lineBreak();

        Logging.logUI("Sentiment Analysis For " + topic.topicName, new String[]{Logging.BOLD, Logging.CYAN});
        Logging.smartHorizontalLine();

        Logging.lineBreak();

        Logging.logUI("Article with Richest Vocabulary: " + richestVocab.getName(), new String[]{Logging.BOLD, Logging.GREEN});
        Logging.logUI("Most Positive Article: " +  mostPositiveArticle.getName(), new String[]{Logging.BOLD, Logging.GREEN});
        Logging.logUI("Least Positive Article: " + leastPositiveArticle.getName(), new String[]{Logging.BOLD, Logging.PURPLE});

        Logging.lineBreak();

        for (ArticleWithScore articleWithScore : articlesWithDescendingScore) {

            List<String> mostCommonWords = articleWithScore.article.wordManager.getMostUsedUniqueWords(15).stream().map(word -> word.contents).toList(); // Can be a default list here, doesn't really matter

            // Some colour customization for fun

            String articleSpecificColour = articleWithScore.score > 10 ? Logging.GREEN : Logging.YELLOW;
            articleSpecificColour = articleWithScore.score < -10 ? Logging.RED : articleSpecificColour;

            Logging.logUI("Article: " + articleWithScore.article.getName() + " | Score: " + String.format("%.2f", articleWithScore.score), new String[]{Logging.BOLD, articleSpecificColour}); // String.format can round the score to 2 decimal places using the %.2f format

            if (!mostCommonWords.isEmpty()) {

                Logging.logUI("Words repeated over 15 times: " + mostCommonWords, new String[]{Logging.ITALIC});

                Logging.smartHorizontalLine();

            } else {

                Logging.smartHorizontalLine(); // Will still use the log length of the log before the if statement (see the class)

            }

        }

        Logging.lineBreak();

        Logging.logUI("Sentiment Analysis Complete!", new String[]{Logging.BOLD});

        Thread.sleep(2_500); // Sleep for 2.5s for (better) UX

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

            Files.write("./ExternalFiles/Topics/" + topicName + "/" + articleContent.get("domain") + ".txt", articleContent.get("text"));

            Logging.logUI("Article downloaded and added successfully!", new String[]{Logging.BOLD, Logging.GREEN});

        } catch (Exception e) {

            Logging.logUI("Failed to download the article. The URL may be invalid.", new String[]{Logging.BOLD, Logging.RED});

        }

    }

    private static void addArticleFromFile(String topicName, String filePath) {

        try {

            Files.move(filePath, "./ExternalFiles/Topics/" + topicName, true);
            Logging.logUI("Article added successfully!", new String[]{Logging.BOLD, Logging.GREEN});

        } catch (Exception e) {

            // Try with relative (never nesters are sad)

            try {

                Files.move(filePath, "./ExternalFiles/Topics/" + topicName, false); // Telling it relative

            } catch (Exception e2) {

                Logging.logUI("Failed to add the article. The file path may be invalid.", new String[]{Logging.BOLD, Logging.RED});

            }

        }

    }


    private static void removeArticle(Scanner scanner, String topicName) {

        String articleName = getStringInput(scanner, "Enter the name of the article to remove");

        Logging.logUI("Are you sure? Type Yes (y) or No (n): ", new String[]{Logging.BOLD, Logging.YELLOW}, true);
        String userConfirm = scanner.nextLine();

        if (userConfirm.equals("yes") || userConfirm.equals("y")) {

            try {

                Files.delete("./ExternalFiles/Topics/" + topicName + "/" + articleName);
                Logging.logUI("Article removed successfully!", new String[]{Logging.BOLD, Logging.GREEN});

            } catch (Exception e) {

                Logging.logUI("Failed to remove the article. It may not exist.", new String[]{Logging.BOLD, Logging.RED});

            }
        }

        else {
            Logging.logUI("Article deletion cancelled.", new String[]{Logging.BOLD, Logging.RED});

        }

    }

    private static void exitProgram() {

        Logging.logUI("Exiting program...", new String[]{Logging.BOLD, Logging.RED});
        System.exit(0);

    }

}