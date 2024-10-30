package Classes.Articles;

import Classes.LexiconWord;
import Util.Files.FileReader;
import Util.Files.PathResolver;

import java.util.ArrayList;

public class SentimentRanker {

    Article relatedArticle;

    ArrayList<String> rawLexiconWords;
    ArrayList<LexiconWord> parsedLexiconWords;

    float sentimentRank;

    public SentimentRanker(Article article) {

        this.relatedArticle = article;

    }

    public ArrayList<String> readLexiconWords() throws Exception {

        FileReader reader = new FileReader(new PathResolver(("./ExternalFiles/Lexicon.txt")).Resolve());

        ArrayList<String> words = reader.readAsStringList(false);

        this.rawLexiconWords = words;

        return words;

    }

    public ArrayList<LexiconWord> parseLexiconWords() {

        final ArrayList<LexiconWord> lexiconWords = new ArrayList<>();

        this.rawLexiconWords.forEach((word) -> {

            LexiconWord lexiconWord = new LexiconWord(word);

            lexiconWord.split();

            lexiconWords.add(lexiconWord);

        });

        this.parsedLexiconWords = lexiconWords;

        return lexiconWords;

    }

    public float rank() throws Exception {

        relatedArticle.process();

        this.readLexiconWords();
        this.parseLexiconWords();

        // Rank the sentiment of the article

        float sentimentRank = 0;

        for (String word : relatedArticle.stopWordRemovedContents) {

            for (LexiconWord lexiconWord : parsedLexiconWords) {

                if (lexiconWord.contents.contains(word)) {

//                    System.out.println("Found word: " + word + " with rank: " + lexiconWord.ranking);

                    sentimentRank += lexiconWord.ranking;

                    break;

                }

            }

        }

        this.sentimentRank = sentimentRank;

        return sentimentRank;

    }

}
