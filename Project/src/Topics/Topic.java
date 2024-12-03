package Topics;

import Articles.Article;
import Util.Files.Directories;

import java.util.ArrayList;

/**
 * This class represents a topic, which is comprised of articles
 * This contains a method which parses the directory related to the topic and then reads the articles in the directory
 * */

public class Topic {

    public String topicName;

    public ArrayList<Article> articleList = new ArrayList<>();

    private final String dirPath;

    public ArticleManager articleManager;

    public Topic(String topicName) {

        this.topicName = topicName;

        this.dirPath = "./ExternalFiles/" + topicName + "/";

        this.articleManager = new ArticleManager(this);

    }

    /**
     * Reads the articles in the directory corresponding to the topic
     * @throws Exception - If the directory cannot be read or if the directory does not exist
     * */

    public void load() throws Exception {

        this.articleList.clear(); // Ensure that the article list is empty before loading the articles

        ArrayList<String> articleFileList = Directories.read(this.dirPath);

        for (String articleFile : articleFileList) {

            Article article = new Article(this.dirPath + articleFile);

            article.read(true);

            this.articleList.add(article);

        }

    }

    public void process() throws Exception {

        for (Article article : this.articleList) {

            article.process();

        }

    }

}
