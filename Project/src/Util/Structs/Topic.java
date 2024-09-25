package Util.Structs;

import Util.Files.DirReader;

import java.util.ArrayList;

public class Topic {

    public String topicName;

    public ArrayList<Article> articleList;

    private final String dirPath;

    public Topic(String topicName) {

        this.topicName = topicName;

        this.dirPath = "./ExternalFiles/" + topicName + "/";

    }

    public void Load() throws Exception {

        DirReader dirReader = new DirReader(dirPath);

        ArrayList<String> articleFileList = dirReader.Read();

        for (String articleFile : articleFileList) {

            Article article = new Article(this.dirPath + articleFile);

            // article.Load(); implemented soon

            articleList.add(article);

        }

    }

}
