package Util.Scraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * This class is responsible for downloading the text content of an article from a URL
 * It uses the JSoup library to fetch and parse the HTML
 * */

public class ArticleScraper {

    /**
     * Downloads the text content of an article from a URL
     * @param url - The URL of the article
     * @return The text content of the article
     * @throws IOException - If the URL is invalid or if the article cannot be downloaded
     * */

    public static String text(String url) throws IOException {

        Document document = Jsoup.connect(url).get();

        return document.body().text();
        
    }

}
