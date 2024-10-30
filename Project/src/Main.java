import Classes.Articles.Article;
import Topics.Topic;

public class Main {

    public static void main(String[] args) throws Exception {

        Topic myTopic = new Topic("Starliner");

        myTopic.Load();

        for (Article article : myTopic.articleList) {

            float sentimentRank = article.sentimentRanker.rank();

            System.out.println("Sentiment for " + article.getName() + ": " + sentimentRank);

        }

    }

}