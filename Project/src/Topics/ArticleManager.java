package Topics;

import Articles.Article;
import Util.Misc.Logging;
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

                currentArticle.read(true);

                currentArticle.process();

            } catch (Exception e) {

                Logging.log(Logging.ERROR, "Failed to read and process article: " + currentArticle.getName());

            }

        });

        ArrayList<Article> sortedArticles = Sorting.sortByObjectPropertyCount(relatedTopic.articleList, "uniqueWordCount");

        return sortedArticles.getFirst();

    }

}
