package ru.GraduationQualificationWork.Spider;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

import java.util.Map;

/**
 * Created by suzuka on 18.11.2017.
 */
public class SpiderControllerFactory implements CrawlController.WebCrawlerFactory {


    Map<String, String> metadata;

    @Override
    public WebCrawler newInstance() throws Exception {
        return null;
    }
}
