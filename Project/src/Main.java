import Classes.Articles.Article;
import Topics.Topic;

public class Main {

    public static void main(String[] args) throws Exception {

        Topic myTopic = new Topic("StarshipCatch");

        myTopic.Load();

        Article richest = myTopic.articleManager.getArticleWithRichestVocab();

        richest.stats.getMostUsedUniqueWords(10).forEach((word) -> System.out.println(word.contents)); // get words with the highest frequency

    }

}