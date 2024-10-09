package Util.Misc;

import Classes.Article;
import Util.Files.FileReader;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class WordManager {

    private final String stopWordPath = "./ExternalFiles/StopWords.txt";

    private ArrayList<String> stopWords;

    public WordManager() throws Exception {

        this.ReadStopWords();

    }

    private void ReadStopWords() throws Exception {

        FileReader fileReader = new FileReader(this.stopWordPath);

        this.stopWords = fileReader.readAsStringList(true); // Gets individual words

    }

    public ArrayList<String> removePunctuation(Article article, ArrayList<String> contents) {

        ArrayList<String> filteredList = new ArrayList<>();

        for (String word : contents) {

            word = word.replaceAll("[^a-zA-Z0-9]", "");

            if (!word.isEmpty()) {

                filteredList.add(word);

            }

        }

        article.punctuationRemovedContents = filteredList;

        return filteredList;

    }

    public int getStopWordCount(Article article, ArrayList<String> contentsToUse) {

        int stopWordCount = 0;

        for (String word : contentsToUse) {

            if (this.stopWords.contains(word)) {

                stopWordCount++;

            }

        }

        return stopWordCount;

    }

    public ArrayList<String> removeStopWords(Article article, ArrayList<String> contentsToUse) {

        ArrayList<String> filteredList = new ArrayList<>();

        for (String word : contentsToUse) {

            if (!this.stopWords.contains(word) || word.isEmpty()) {

                filteredList.add(word);

            }

        }

        article.stopWordRemovedContents = filteredList;

        return filteredList;

    }


}
