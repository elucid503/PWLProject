import Classes.Articles.Article;
import Classes.Topic;
import Classes.Word;
import Util.Misc.Sorting;

public class Main {

    public static void main(String[] args) throws Exception {

        Topic myTopic = new Topic("Topic1");

        myTopic.Load();
        
        Article firstArticle = myTopic.articleList.getFirst();

        firstArticle.Read(true);

        System.out.println(firstArticle.stats.getUniqueWordCount());
        System.out.println(firstArticle.stats.getWordCount());

        System.out.println(firstArticle.wordManager.getStopWordCount(firstArticle, firstArticle.arrayListContents));

        System.out.println(firstArticle.wordManager.removePunctuation(firstArticle, firstArticle.arrayListContents));
        System.out.println(firstArticle.wordManager.removeStopWords(firstArticle, firstArticle.punctuationRemovedContents));

        firstArticle.wordManager.getUniqueWords(firstArticle, firstArticle.stopWordRemovedContents);

        for (Word word : Sorting.sortByObjectPropertyCount((firstArticle.uniqueWords), "timesSeen")) {
            System.out.println(word.contents + " " + word.timesSeen);
        }

    }

}