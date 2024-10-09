package Classes;

import Util.Files.FileReader;
import Util.Misc.ArticleStats;
import Util.Misc.WordManager;

import java.util.ArrayList;

public class Article {

    private final String filePath;

    public String plainTextContents;
    public ArrayList<String> arrayListContents;

    public ArrayList<String> punctuationRemovedContents;
    public ArrayList<String> stopWordRemovedContents;

    public ArticleStats stats;
    public WordManager stopWordManager;

    public Article(String filePath) throws Exception {

        this.filePath = filePath;

        this.stats = new ArticleStats(this);
        this.stopWordManager = new WordManager();

    }

    public void Read(boolean lower) throws Exception {

        this.plainTextContents = new FileReader(this.filePath).readAsString();
        this.arrayListContents = new FileReader(this.filePath).readAsStringList(true); // Gets individual words

        if (lower) {

            this.plainTextContents = this.plainTextContents.toLowerCase();

            // Help from the function reference operator

            this.arrayListContents.replaceAll(String::toLowerCase);

        }

    }

}
