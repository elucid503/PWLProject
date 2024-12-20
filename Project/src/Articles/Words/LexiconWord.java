package Articles.Words;

import java.util.Arrays;

/**
 * Represents a word with a lexicon ranking, found in Lexicon.txt
 * */

public class LexiconWord {

    public String lexiconLine;

    public String contents;
    public float ranking;

    public LexiconWord(String lexiconLine) {

        this.lexiconLine = lexiconLine;

    }

    /**
     * Splits the lexicon line into the contents and ranking based on the predetermined syntax of the lexicon.txt file
     * * */

    public void split() {

        String[] strings = this.lexiconLine.split("\t");

        String[] lowerCaseAndTrimmed = Arrays.stream(strings).map(String::trim).toArray(String[]::new);

        this.contents = lowerCaseAndTrimmed[0];
        this.ranking = Float.parseFloat(lowerCaseAndTrimmed[1]);

    }

}