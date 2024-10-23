package Classes.Articles;

import Classes.Word;
import Util.Files.FileReader;

import java.util.ArrayList;
import java.util.HashMap;

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

    public ArrayList<Word> getUniqueWords(Article article, ArrayList<String> contentsToUse) {

        // Hybrid approach with hashmap and a "Word" class

        HashMap<String, Word> uniqueWords = new HashMap<>();

        for (String word : contentsToUse) {

            if (uniqueWords.containsKey(word)) {

                // Word has been seen before

                uniqueWords.get(word).incrementTimesSeen();

            } else {

                // Word has not been seen before

                uniqueWords.put(word, new Word(word));

            }

        }

        ArrayList<Word> uniqueWordList = new ArrayList<>(uniqueWords.values());

        article.uniqueWords = uniqueWordList;
        article.uniqueWordCount = uniqueWordList.size();

        return uniqueWordList;

    }


}
