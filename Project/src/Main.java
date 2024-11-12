import Topics.Topic;
import Articles.Article;
import Articles.Words.ArticleWord;

import Util.Misc.Logging;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception {

        Topic myTopic = new Topic("StarshipCatch");

        myTopic.load();

        Logging.log(Logging.DEBUG, "Topic loaded: " + myTopic.topicName);

        Logging.log(Logging.DEBUG, "Article with richest vocab: " + myTopic.articleManager.getArticleWithRichestVocab().getName());

        Logging.newLine();

        for (Article article : myTopic.articleList) {

            float sentimentRank = article.sentimentRanker.rank();

            ArrayList<ArticleWord> wordsRepeatedOverFiveTimes = article.wordManager.getMostUsedUniqueWords(10);

            Logging.log(Logging.DEBUG, "Words repeated over 7 times in " + article.getName() + ": " + wordsRepeatedOverFiveTimes.stream().map(word -> word.contents).toList());

            Logging.log(Logging.DEBUG, "Sentiment rank for " + article.getName() + ": " + sentimentRank);

        }

    }

}