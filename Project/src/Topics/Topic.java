package Topics;

import Articles.Article;
import Util.Files.DirReader;

import java.util.ArrayList;

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

    public void Load() throws Exception {

        DirReader dirReader = new DirReader(dirPath);

        ArrayList<String> articleFileList = dirReader.Read();

        for (String articleFile : articleFileList) {

            Article article = new Article(this.dirPath + articleFile);

             article.read(true);

            this.articleList.add(article);

        }

    }

}
