import Classes.Topic;

public class Main {

    public static void main(String[] args) throws Exception {

        Topic myTopic = new Topic("Topic1");

        myTopic.Load();

        myTopic.articleList.getFirst().Read();

        System.out.println(myTopic.articleList.getFirst().stats.getWordCount());

    }

}
