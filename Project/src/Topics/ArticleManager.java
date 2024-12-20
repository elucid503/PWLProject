package Topics;

import Articles.Article;
import Articles.Managers.WordManager;
import CLI.Logging;
import Util.Misc.Sorting;

import java.util.ArrayList;

/**
 * This class is responsible for comparing articles statistics. Could be expanded more on in the future.
 * */

public class ArticleManager {

    Topic relatedTopic;

    public ArticleManager(Topic relatedTopic) {

        this.relatedTopic = relatedTopic;

    }

    /**
     * This uses the Sorting class to sort the articles by their word count and returns the article with the most words
     *
     * @return The article object with the most unique words
     * */

    public Article getArticleWithRichestVocab() {

        ArrayList<WordManager> wordManagers = new ArrayList<>();

        relatedTopic.articleList.forEach((currentArticle) -> {

            try {

                currentArticle.read(true);

                currentArticle.process();

                wordManagers.add(currentArticle.wordManager);

            } catch (Exception e) {

                Logging.logInternal(Logging.ERROR, "Failed to read and process article: " + currentArticle.getName());

            }

        });

        ArrayList<WordManager> sortedWordManagers = Sorting.sortObjectByIntPropertyCount(wordManagers, "uniqueWordCount");

        return sortedWordManagers.getFirst().article;

    }

}
