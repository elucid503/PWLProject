package Util.Structs;

import Util.Files.FileReader;

import java.util.ArrayList;

public class Article {

    private final String filePath;

    public String plainTextContents;
    public ArrayList<String> arrayListContents;

    public Article(String filePath) {

        this.filePath = filePath;

    }

    public void Read() throws Exception {

        this.plainTextContents = new FileReader(this.filePath).readAsString();
        this.arrayListContents = new FileReader(this.filePath).readAsStringList();

    }

}
