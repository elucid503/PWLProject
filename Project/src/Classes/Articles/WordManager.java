package Classes.Articles;

import Classes.Word;
import Util.Files.FileReader;

import java.util.ArrayList;
import java.util.HashMap;

public class WordManager {

    private final String stopWordPath = "./ExternalFiles/StopWords.txt";

    public ArrayList<String> stopWords;

    public WordManager() throws Exception {

        this.ReadStopWords();

    }

    private void ReadStopWords() throws Exception {

        // If cached

        if (this.stopWords != null) {

            return;

        }

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
