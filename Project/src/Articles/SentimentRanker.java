package Articles;

import Articles.Words.LexiconWord;
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

    public void readLexiconWords() throws Exception {

        FileReader reader = new FileReader(new PathResolver(("./ExternalFiles/Lexicon.txt")).Resolve());

        this.rawLexiconWords = reader.readAsStringList(false);

    }

    public void parseLexiconWords() {

        final ArrayList<LexiconWord> lexiconWords = new ArrayList<>();

        this.rawLexiconWords.forEach((word) -> {

            LexiconWord lexiconWord = new LexiconWord(word);

            lexiconWord.split();

            lexiconWords.add(lexiconWord);

        });

        this.parsedLexiconWords = lexiconWords;

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

                    sentimentRank += lexiconWord.ranking;

                    break;

                }

            }

        }

        this.sentimentRank = sentimentRank;

        return sentimentRank;

    }

}
