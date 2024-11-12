import Topics.Topic;
import Articles.Article;

import Util.Misc.Logging;

public class Main {

    public static void main(String[] args) throws Exception {

        Topic myTopic = new Topic("StarshipCatch");

        myTopic.load();

        for (Article article : myTopic.articleList) {

            float sentimentRank = article.sentimentRanker.rank();

            Logging.log(Logging.DEBUG, "Sentiment rank for " + article.filePath + ": " + sentimentRank);

        }

    }

}