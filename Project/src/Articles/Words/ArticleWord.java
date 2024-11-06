package Articles.Words;

public class ArticleWord {

    public String contents;
    public int timesSeen;

    public ArticleWord(String contents) {

        this.contents = contents;
        this.timesSeen = 1;

    }

    public void incrementTimesSeen() {

        this.timesSeen++;

    }

}
