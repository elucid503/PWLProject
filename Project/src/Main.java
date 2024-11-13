import CLI.Interactions;

public class Main {

    public static void main(String[] args) throws Exception {

//
//        Topic myTopic = new Topic("StarshipCatch");
//
//        myTopic.load();
//
//        Logging.logInternal(Logging.DEBUG, "Topic loaded: " + myTopic.topicName);
//
//        Logging.logInternal(Logging.DEBUG, "Article with richest vocab: " + myTopic.articleManager.getArticleWithRichestVocab().getName());
//
//        Logging.lineBreak();
//
//        for (Article article : myTopic.articleList) {
//
//            float sentimentRank = article.sentimentRanker.rank();
//
//            ArrayList<ArticleWord> wordsRepeatedOverFiveTimes = article.wordManager.getMostUsedUniqueWords(10);
//
//            Logging.logInternal(Logging.DEBUG, "Words repeated over 10 times in " + article.getName() + ": " + wordsRepeatedOverFiveTimes.stream().map(word -> word.contents).toList());
//
//            Logging.logInternal(Logging.DEBUG, "Sentiment rank for " + article.getName() + ": " + String.format("%.2f", sentimentRank)); // rounds to 2 decimal places
//
//        }

        Interactions.startUI();

    }

}