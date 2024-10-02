package Util.Misc;

import Classes.Article;

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

    public int getUniqueWordCount() {

        // Use hashmap

        ArrayList<String> articleList = article.arrayListContents;
        ArrayList<String> uniqueList = new ArrayList<>();

        ArrayList<String> uniqueWords = new ArrayList<>();

        HashMap<String, Integer> wordCount = new HashMap<>();

        for (String word : articleList) {

            if (wordCount.containsKey(word)) {

                wordCount.put(word, wordCount.get(word) + 1);

            } else {

                wordCount.put(word, 1);

            }

        }

        for (String word : wordCount.keySet()) {

            if (wordCount.get(word) == 1) {

                uniqueWords.add(word);

            }

        }

        return uniqueWords.size();

    }

}
