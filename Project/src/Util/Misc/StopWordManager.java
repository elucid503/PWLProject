package Util.Misc;

import Classes.Article;
import Util.Files.FileReader;

import java.util.ArrayList;

public class StopWordManager {

    private final Article article;

    private final String stopWordPath = "./ExternalFiles/StopWords.txt";

    private ArrayList<String> stopWords;

    public StopWordManager(Article article) throws Exception {

        this.ReadStopWords();

        this.article = article;

    }

    private void ReadStopWords() throws Exception {

        FileReader fileReader = new FileReader(this.stopWordPath);

        this.stopWords = fileReader.readAsStringList();

    }

    public int getStopWordCount() {

        int stopWordCount = 0;

        System.out.println(this.stopWords);
        System.out.println(this.article.arrayListContents);

        for (String word : this.article.arrayListContents) {

            if (this.stopWords.contains(word)) {

                stopWordCount++;

            }

        }

        return stopWordCount;

    }

    public ArrayList<String> removeStopWords() {

        ArrayList<String> filteredList = new ArrayList<>();

        for (String word : this.article.arrayListContents) {

            if (!this.stopWords.contains(word)) {

                filteredList.add(word);

            }

        }

        return filteredList;

    }


}
