package Classes;

public class Word {

    public String contents;
    public int timesSeen;

    public Word(String contents) {

        this.contents = contents;
        this.timesSeen = 1;

    }

    public void incrementTimesSeen() {

        this.timesSeen++;

    }

}
