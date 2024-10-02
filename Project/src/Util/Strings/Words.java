package Util.Strings;

import java.util.ArrayList;
import java.util.Arrays;

public class Words {

    public static ArrayList<String> convertSentencesToWords(ArrayList<String> sentences) {

        ArrayList<String> result = new ArrayList<>();

        for (String sentence : sentences) {

            result.addAll(Arrays.asList(sentence.split(" ")));

        }

        return result;

    }

}
