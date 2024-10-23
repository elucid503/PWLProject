package Topics;

import Classes.Articles.Article;
import Util.Misc.Sorting;

import java.util.ArrayList;

public class ArticleManager {

    Topic relatedTopic;

    public ArticleManager(Topic relatedTopic) {

        this.relatedTopic = relatedTopic;

    }

    public Article getArticleWithRichestVocab() {

        relatedTopic.articleList.forEach((currentArticle) -> {

            try {

                currentArticle.Read(true);

                // TODO: Consolidate this

                currentArticle.wordManager.removeStopWords(currentArticle, currentArticle.wordManager.removePunctuation(currentArticle, currentArticle.arrayListContents));
                currentArticle.wordManager.removeStopWords(currentArticle, currentArticle.wordManager.removePunctuation(currentArticle, currentArticle.arrayListContents));
                currentArticle.stats.getUniqueWords();

            } catch (Exception e) {

                e.printStackTrace(); // TODO: Replace w logging system

            }

        });

        ArrayList<Article> sortedArticles = Sorting.sortByObjectPropertyCount(relatedTopic.articleList, "uniqueWordCount");

        return sortedArticles.getFirst(); // TODO: Verify this all works :)

    }

}
