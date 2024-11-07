package Util;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class contains misc methods for manipulating strings
 * */

public class Strings {

    /**
     * Converts a list of sentences to a list of words
     * @param sentences - The list of sentences
     * @return The list of words
     * */

    public static ArrayList<String> convertSentencesToWords(ArrayList<String> sentences) {

        ArrayList<String> result = new ArrayList<>();

        for (String sentence : sentences) {

            result.addAll(Arrays.asList(sentence.split(" ")));

        }

        return result;

    }

}
