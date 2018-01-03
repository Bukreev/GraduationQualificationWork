package ru.GraduationQualificationWork.Spider;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.url.WebURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.GraduationQualificationWork.Model.DAO.LinkDao;
import ru.GraduationQualificationWork.Model.Entity.Link;

import java.util.regex.Pattern;

/**
 * Created by suzuka on 18.11.2017.
 */

@Component
public class Spider extends WebCrawler {

//    private final LinkDao linkDao;

    @Value("url.base")
    private String baseDomain;

    public Spider() {
    }

//    @Autowired
//    public Spider(LinkDao linkDao) {
//        this.linkDao = linkDao;
//    }

    private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js|gif|jpg"
            + "|png|mp3|mp4|zip|gz))$");

    @Override
    public final boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();

        return !FILTERS.matcher(href).matches()
                && href.startsWith("http://www.ics.uci.edu/");
    }

    @Override
    public void visit(Page page) {
        String url = page.getWebURL().getURL();
        System.out.println("### " + url);

    }

}
