package Classes;

import java.util.Arrays;

public class LexiconWord {

    public String lexiconLine;

    public String contents;
    public float ranking;

    public LexiconWord(String lexiconLine) {

        this.lexiconLine = lexiconLine;

    }

    public void split() {

        String[] strings = this.lexiconLine.split("\t");

        String[] lowerCaseAndTrimmed = Arrays.stream(strings).map(String::trim).toArray(String[]::new);

        this.contents = lowerCaseAndTrimmed[0];
        this.ranking = Float.parseFloat(lowerCaseAndTrimmed[1]);

    }

}