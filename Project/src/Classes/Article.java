package Classes;

import Util.Files.FileReader;
import Util.Misc.ArticleStats;
import Util.Misc.StopWordManager;

import java.util.ArrayList;

public class Article {

    private final String filePath;

    public String plainTextContents;
    public ArrayList<String> arrayListContents;

    public ArticleStats stats;
    public StopWordManager stopWordManager;

    public Article(String filePath) throws Exception {

        this.filePath = filePath;

        this.stats = new ArticleStats(this);
        this.stopWordManager = new StopWordManager(this);

    }

    public void Read() throws Exception {

        this.plainTextContents = new FileReader(this.filePath).readAsString();
        this.arrayListContents = new FileReader(this.filePath).readAsStringList();

    }

}
