package Util.Misc;

import Classes.Article;

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

    public int uniqueWords() {

        // Use hashmap

        return 0;

    }

    public int countWords () {

        String text = article.plainTextContents.toLowerCase(); // Normalize text to lowercase

        String[] words = text.split("\\s+|(?=[.,;!?])|(?<=\\b)"); // Split by whitespace or punctuations as delimiters

        int wordCount = 0;

        for (String word : words) {

            word = word.replaceAll("[^a-zA-Z]", ""); // Remove non-alphabetic characters

            if (!word.isEmpty()) {

                wordCount++;

                }
            }

        return wordCount;

    }

}



