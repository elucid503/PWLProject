package Articles.Words;

/**
 * Represents a word in an article
 * */

public class ArticleWord {

    public String contents;
    public int timesSeen;

    public ArticleWord(String contents) {

        this.contents = contents;
        this.timesSeen = 1;

    }

    /**
     * Increments the times this word has been seen when we loop through an article and encounter it
     * */

    public void incrementTimesSeen() {

        this.timesSeen++;

    }

}
