package Articles;

import Articles.Words.LexiconWord;
import Util.Files.Files;
import Util.Files.Util;

import java.util.ArrayList;

/**
 * This class is responsible for ranking the sentiment of an article
 * based on a lexicon of words with associated sentiment values.
 * */

public class SentimentRanker {

    Article relatedArticle;

    ArrayList<String> rawLexiconWords;
    ArrayList<LexiconWord> parsedLexiconWords;

    float sentimentRank;

    public SentimentRanker(Article article) {

        this.relatedArticle = article;

    }

    /**
     * Reads the lexicon words from Lexicon.txt
     * @throws Exception - If the file cannot be read or if the file does not exist
     * */

    public void readLexiconWords() throws Exception {

        this.rawLexiconWords = Files.readAsStringList(Util.resolvePath("./ExternalFiles/Lexicon.txt"), false);

    }

    /**
     * Parses the lexicon words into LexiconWord objects using the LexiconWord class's split method
     * */

    public void parseLexiconWords() {

        final ArrayList<LexiconWord> lexiconWords = new ArrayList<>();

        this.rawLexiconWords.forEach((word) -> {

            LexiconWord lexiconWord = new LexiconWord(word);

            lexiconWord.split();

            lexiconWords.add(lexiconWord);

        });

        this.parsedLexiconWords = lexiconWords;

    }

    /**
     * Ranks the sentiment of the article based on the lexicon words
     * @return The sentiment rank of the article
     * @throws Exception - If the lexicon words cannot be read or if the lexicon words file does not exist
     * */

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
