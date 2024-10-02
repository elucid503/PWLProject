import Classes.Topic;

public class Main {

    public static void main(String[] args) throws Exception {

        Topic myTopic = new Topic("Topic1");

        myTopic.Load();

        myTopic.articleList.getFirst().Read(true);

        System.out.println(myTopic.articleList.getFirst().stats.getUniqueWordCount());
        System.out.println(myTopic.articleList.getFirst().stopWordManager.getStopWordCount());
        System.out.println(myTopic.articleList.getFirst().stats.getWordCount());

    }

}
