package ru.GraduationQualificationWork.Crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.GraduationQualificationWork.Model.DAO.LinkDao;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Crawler {


    private final LinkDao linkDao;
    private String baseUrl;
    private Document doc;

    @Autowired
    public Crawler(LinkDao linkDao) throws IOException {
        this.linkDao = linkDao;
    }

    // Устанавливает BaseURl

    public final void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    // Возвращает объект документа

    public final Document getDocument() {
        return doc;
    }

    // Возвращает ссылки со страницы

    public final List<Element> getLinks(String url) {
        Elements links = doc.select("a[href]");
        return links.stream().filter(x -> x.attr("href").contains(url) || x.attr("href").startsWith("/")).collect(Collectors.toList());
    }

    // Обходит все ссылки по заданной глубине

    public final void crawl(int deep) throws IOException {
        this.doc = Jsoup.connect(baseUrl).get();
        for (int i = deep; i > 0; i--) {
            List<Element> list = getLinks(baseUrl);
            if (list.size() != 0) {
                for (Element link : list) {
                    linkDao.save(link.attr("href"));
                    Long id = linkDao.getLinkByAdress(link.attr("href")).getId();
                    System.out.println(String.format("%s", link.attr("href")));
                    crawl(i, link.attr("href"), id);
                }

            }

        }

    }

    public final void crawl(int deep, String url, Long index) throws IOException {
        try {
            this.doc = Jsoup.connect(url).get();
        } catch (IllegalArgumentException e) {
            this.doc = Jsoup.connect(baseUrl + url).get();
        }
        for (int i = deep; i > 0; i--) {
            List<Element> list = getLinks(url);
            if (list.size() != 0) {
                for (Element link : list) {
                    System.out.println(String.format("---> %s", link.attr("href")));
                    crawl(i, link.attr("href"), index);
                }
            }

        }

    }

}
