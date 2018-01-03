package ru.GraduationQualificationWork.Spider;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by suzuka on 18.11.2017.
 */

@Component
public class SpiderController {
//
//    @Value("url.base")
//    private String basePath;
//
//    @Value("dir.temp")
//    private String crawlStorageFolder;
//
//    @Value("spider.threads")
//    private String numberOfCrawlers;
//
//    @Value("spider.pages.max")
//    private String depth;

    private final Integer threads;
    private final Integer maxPages;

    public SpiderController() {
        this.threads = 1;
        this.maxPages = 2;
    }

    public final void crawl() throws Exception {
        CrawlConfig crawlConfig = new CrawlConfig();
        crawlConfig.setMaxPagesToFetch(5);
        crawlConfig.setCrawlStorageFolder("C:\\Users\\suzuka\\IdeaProjects\\GraduationQualificationWork\\src\\main\\resources\\temp");

        PageFetcher pageFetcher = new PageFetcher(crawlConfig);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController crawlController = new CrawlController(crawlConfig, pageFetcher, robotstxtServer);

//        crawlController.addSeed("http://www.ics.uci.edu/");

        crawlController.addSeed("http://www.techport.ru");

         crawlController.start(Spider.class, threads);
    }
}
