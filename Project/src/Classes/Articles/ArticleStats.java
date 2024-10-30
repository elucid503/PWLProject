package Classes.Articles;

import Classes.ArticleWord;
import Util.Misc.Sorting;

import java.util.ArrayList;
import java.util.HashMap;

public class ArticleStats {

    Article article;

    public ArticleStats(Article article) {

        this.article = article;

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

    public ArrayList<ArticleWord> getUniqueWords() {

        // Caching

        if (article.uniqueWords != null) {

            return article.uniqueWords; // Assuming article.uniqueWordsSize is not null also

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

}