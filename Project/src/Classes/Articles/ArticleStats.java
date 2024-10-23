package Classes.Articles;

import Classes.Word;
import Util.Misc.Sorting;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleStats {

    Article article;

    public ArticleStats(Article article) {

        this.article = article;

    }

    /**
     * Article and Stop Words must be loaded before calling this method
     * @return The number of stop words in the article
     */

    public int getStopWordCount(ArrayList<String> contentsToUse) {

        if (this.article.wordManager.stopWords == null) {

            return -1; // Must have stop words loaded

        }

        int stopWordCount = 0;

        for (String word : contentsToUse) {

            if (this.article.wordManager.stopWords.contains(word)) {

                stopWordCount++;

            }

        }

        return stopWordCount;

    }

    /**
     * Article must be loaded before calling this method
     * @return The number of words in the article
     */

    public int getWordCount() {

        return article.plainTextContents.split(" ").length;

    }
    
    /**
     * Article must have its stopWordRemovedContents loaded before calling this method
     * @return The number of characters in the article
     */

    public ArrayList<Word> getUniqueWords() {

        // Caching

        if (article.uniqueWords != null) {

            return article.uniqueWords; // Assuming article.uniqueWordsSize is not null also

        }

        // Hybrid approach with hashmap and a "Word" class

        HashMap<String, Word> uniqueWords = new HashMap<>();

        for (String word : article.stopWordRemovedContents) {

            if (uniqueWords.containsKey(word)) {

                // Word has been seen before

                uniqueWords.get(word).incrementTimesSeen();

            } else {

                // Word has not been seen before

                uniqueWords.put(word, new Word(word));

            }

        }

        ArrayList<Word> uniqueWordList = new ArrayList<>(uniqueWords.values());

        article.uniqueWords = uniqueWordList;
        article.uniqueWordCount = uniqueWordList.size();

        return uniqueWordList;

    }

    public int getUniqueWordCount() {

        return this.article.uniqueWords.size();

    }

    /**
     * Article must have its uniqueWords loaded before calling this method
     * @param timesSeen The minimum number of times a word must have been seen to be included in the list
     * @return The most used unique words in the article
     */
    
    public ArrayList<Word> getMostUsedUniqueWords(int timesSeen) {
        
        ArrayList<Word> sortedWords = Sorting.sortByObjectPropertyCount(this.article.uniqueWords, "timesSeen");

        ArrayList<Word> mostUniqueWords = new ArrayList<>();

        sortedWords.forEach((word) -> {

            if (word.timesSeen >= timesSeen) {

                mostUniqueWords.add(word);

            }

        });

        return mostUniqueWords;
        
    }

}