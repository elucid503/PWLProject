package CLI;

import Topics.Topic;
import Util.Files.Directories;
import Util.Files.Files;
import Util.Scraping.ArticleScraper;

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

    public static void getAndHandleInput(Scanner inputScanner) {

        String input = inputScanner.nextLine();

        Interactions.handleInput(input, inputScanner);

    }

    public static String getStringInputFromUser(Scanner inputScanner, String prompt) {

        Logging.logUI(prompt, new String[]{Logging.BOLD, Logging.CYAN});
        return inputScanner.nextLine();

    }

    public static void handleInput(String input, Scanner referencedScanner) {

        switch (input) {

            case "1": {

                // Prompt the user for the topic name

                String topicName = Interactions.getStringInputFromUser(referencedScanner, "Enter a name for the topic: ");

                // Create the topic folder

                try {

                    Directories.create("./ExternalFiles/Topics/" + topicName);

                } catch (Exception e) {

                    Logging.logUI("An error occurred while creating the topic folder", new String[]{Logging.BOLD, Logging.RED});
                    break;

                }

                Logging.logUI("Topic created successfully. You now may add articles to this topic by selecting it within the \"Add an Article\" option and then providing a URL or file path.", new String[]{Logging.BOLD, Logging.GREEN});

            } break;

            case "2": {

                // Select a topic and run analysis

                String topicName = Interactions.getStringInputFromUser(referencedScanner, "Enter the name of the topic you would like to analyze: ");

                // Check if the topic exists by creating it

                Topic foundTopic = new Topic(topicName);

                try {

                    foundTopic.load();

                } catch (Exception e) {

                    Logging.logUI("The topic does not exist", new String[]{Logging.BOLD, Logging.RED});
                    break;

                }

                // Run the analysis

                Logging.logUI("Running analysis on the topic", new String[]{Logging.BOLD, Logging.CYAN});

                // TODO: Actually do this

            } break;

            case "3": {

                // TODO: Maybe make this consolidated since it is similar to the code in case 2

                String topicNameToAdd = Interactions.getStringInputFromUser(referencedScanner, "Enter the name of the topic you would like to add an article to: ");

                // Check if the topic exists by creating it

                Topic foundTopic = new Topic(topicNameToAdd);

                try {

                    foundTopic.load();

                } catch (Exception e) {

                    Logging.logUI("The topic does not exist. Create it using the \"Create a Topic\" option.", new String[]{Logging.BOLD, Logging.RED});
                    break;

                }

                // Actually new stuff

                String topicFilePathOrURL = Interactions.getStringInputFromUser(referencedScanner, "Enter the file path or URL of the article: ");

                // Check if URL or path

                if (topicFilePathOrURL.contains("http")) {

                    // Download the article

                    Logging.logUI("Downloading the article...", new String[]{Logging.BOLD, Logging.CYAN});

                    try {

                        String articleScrapedText = ArticleScraper.text(topicFilePathOrURL);

                        if (articleScrapedText.isEmpty()) {

                            Logging.logUI("Couldn't get the article contents. The URL you entered may be invalid.", new String[]{Logging.BOLD, Logging.RED});
                            break;

                        }

                        Files.write("./ExternalFiles/Topics/" + topicNameToAdd + "/article.txt", articleScrapedText);

                        Logging.logUI("Article downloaded and added successfully!", new String[]{Logging.BOLD, Logging.GREEN});

                    } catch(Exception e) {

                        Logging.logUI("The URL you entered may be invalid.", new String[]{Logging.BOLD, Logging.RED});
                        break;

                    }


                } else {

                    // Try moving, handling errors

                    try {

                        Files.move(topicFilePathOrURL, "./ExternalFiles/Topics/" + topicNameToAdd);

                    } catch(Exception e) {

                        Logging.logUI("The path you entered may be invalid.", new String[]{Logging.BOLD, Logging.RED});
                        break;

                    }

                    Logging.logUI("Article added successfully!", new String[]{Logging.BOLD, Logging.GREEN});

                }




            } break;

            case "4": {

                // TODO: As above, consolidate this with the code in case 2 / 3

                // Remove an article

                String topicNameToRemove = Interactions.getStringInputFromUser(referencedScanner, "Enter the name of the topic you would like to remove an article from: ");

                // Check if the topic exists by creating it

                Topic foundTopic = new Topic(topicNameToRemove);

                try {

                    foundTopic.load();

                } catch (Exception e) {

                    Logging.logUI("The topic does not exist", new String[]{Logging.BOLD, Logging.RED});
                    break;

                }

                String articleToRemove = Interactions.getStringInputFromUser(referencedScanner, "Enter the name of the article you would like to remove: ");

                try {

                    Files.delete("./ExternalFiles/Topics/" + topicNameToRemove + "/" + articleToRemove);

                    Logging.logUI("Article removed successfully!", new String[]{Logging.BOLD, Logging.GREEN});

                } catch(Exception e) {

                    Logging.logUI("The article you entered may not exist.", new String[]{Logging.BOLD, Logging.RED});
                    break;

                }


            } break;

            case "5": {

                // Exit

                Logging.logUI("Exiting", new String[]{Logging.BOLD, Logging.RED});
                System.exit(0);

            } break;

            default: {

                Logging.logUI("Invalid input", new String[]{Logging.BOLD, Logging.RED});

            } break;

        }

        Interactions.getAndHandleInput(referencedScanner);

    }

}


