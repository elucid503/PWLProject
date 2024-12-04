package Articles;

/**
 * ArticleWithScore is a wrapper class that represents an article with a score. This is used in the sentiment analysis UI method to store the article and its score for display and sorting.
 * */
public class ArticleWithScore {

    public Article article;
    public float score;

    public ArticleWithScore(Article article, float score) {

        this.article = article;
        this.score = score;

    }

}
