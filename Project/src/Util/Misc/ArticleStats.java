package Util.Misc;

import Classes.Article;

public class ArticleStats {

    Article article;

    public ArticleStats(Article article) {

        this.article = article;

    }

    public int getWordCount() {

        return article.plainTextContents.split(" ").length;

    }

    public int getStopWordCount() {

        return (int) 0; // TODO: Implement this

    }

}
