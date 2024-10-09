import Classes.Article;
import Classes.Topic;

public class Main {

    public static void main(String[] args) throws Exception {

        Topic myTopic = new Topic("Topic1");

        myTopic.Load();
        
        Article firstArticle = myTopic.articleList.getFirst();

        firstArticle.Read(true);

        System.out.println(firstArticle.stats.getUniqueWordCount());
        System.out.println(firstArticle.stats.getWordCount());

        System.out.println(firstArticle.stopWordManager.getStopWordCount(firstArticle, firstArticle.arrayListContents));

        System.out.println(firstArticle.stopWordManager.removePunctuation(firstArticle, firstArticle.arrayListContents));
        System.out.println(firstArticle.stopWordManager.removeStopWords(firstArticle, firstArticle.punctuationRemovedContents));

    }

}
