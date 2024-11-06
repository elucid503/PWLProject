package Articles;

import Articles.Words.ArticleWord;
import Util.Files.FileReader;
import Util.Misc.Sorting;

import java.util.ArrayList;
import java.util.HashMap;

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

    private void readStopWords() throws Exception {

        // If cached

        if (this.stopWords != null) {

            return;

        }

        FileReader fileReader = new FileReader(this.stopWordPath);

        this.stopWords = fileReader.readAsStringList(true); // Gets individual words

    }

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
