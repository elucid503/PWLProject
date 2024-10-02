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

        return (int) article.arrayListContents.stream().distinct().count();

    }

}
