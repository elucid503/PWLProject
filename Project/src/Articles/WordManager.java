package Articles;

import Articles.Words.ArticleWord;
import Util.Misc.Sorting;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is responsible for doing operations / calculating statistics with the words in an article
 * */
public class WordManager {

    Article article;

    private final String stopWordPath = "./ExternalFiles/StopWords.txt";

    public ArrayList<String> stopWords;

    int uniqueWordCount;

    public WordManager(Article article) throws Exception {

        this.article = article;

        this.readStopWords();

    }

    /**
     * * Article must be loaded before calling this method
     * @return The number of words in the article
     * */
    public int getWordCount() {

        return article.plainTextContents.split(" ").length;

    }

    /**
     * Article must have its stopWordRemovedContents loaded before calling this method
     */
    public void getUniqueWords() {

        // Caching

        if (article.uniqueWords != null) {

            return; // Assuming article.uniqueWordsSize is not null also

        }

        // Hybrid approach with hashmap and a "Word" class

        HashMap<String, ArticleWord> uniqueWords = new HashMap<>();

        for (String word : article.stopWordRemovedContents) {

            if (uniqueWords.containsKey(word)) {

                // Word has been seen before

                uniqueWords.get(word).incrementTimesSeen();

            } else {

                // Word has not been seen before

                uniqueWords.put(word, new ArticleWord(word));

            }

        }

        ArrayList<ArticleWord> uniqueWordList = new ArrayList<>(uniqueWords.values());

        article.uniqueWords = uniqueWordList;
        this.uniqueWordCount = uniqueWordList.size();

    }

    /**
     * Article must have its uniqueWords loaded before calling this method
     * @param timesSeen The minimum number of times a word must have been seen to be included in the list
     * @return The most used unique words in the article
     */
    public ArrayList<ArticleWord> getMostUsedUniqueWords(int timesSeen) {

        ArrayList<ArticleWord> sortedWords = Sorting.sortByObjectPropertyCount(this.article.uniqueWords, "timesSeen");

        ArrayList<ArticleWord> mostUniqueWords = new ArrayList<>();

        sortedWords.forEach((word) -> {

            if (word.timesSeen >= timesSeen) {

                mostUniqueWords.add(word);

            }

        });

        return mostUniqueWords;

    }

    /**
     * Reads the stop words from StopWords.txt
     * @throws Exception - If the file cannot be read or if the file does not exist
     * */
    private void readStopWords() throws Exception {

        // If cached

        if (this.stopWords != null) {

            return;

        }

        Reader fileReader = new Reader(this.stopWordPath);

        this.stopWords = fileReader.readAsStringList(true); // Gets individual words

    }

    /**
     * Removes punctuation from the contents of the article
     * @param contents The contents of the article
     * @return The contents of the article with punctuation removed
     * */
    public ArrayList<String> removePunctuation(ArrayList<String> contents) {

        ArrayList<String> filteredList = new ArrayList<>();

        for (String word : contents) {

            word = word.replaceAll("[^a-zA-Z0-9]", "");

            if (!word.isEmpty()) {

                filteredList.add(word);

            }

        }

        this.article.punctuationRemovedContents = filteredList;

        return filteredList;

    }

    /**
     * Removes stop words from the contents of the article
     * @param contentsToUse The contents of the article
     * */
    public void removeStopWords(ArrayList<String> contentsToUse) {

        ArrayList<String> filteredList = new ArrayList<>();

        for (String word : contentsToUse) {

            if (!this.stopWords.contains(word) || word.isEmpty()) {

                filteredList.add(word);

            }

        }

        this.article.stopWordRemovedContents = filteredList;

    }

}