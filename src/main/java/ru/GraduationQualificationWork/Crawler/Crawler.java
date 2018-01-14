package ru.GraduationQualificationWork.Crawler;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.GraduationQualificationWork.Model.DAO.LinkDao;
import ru.GraduationQualificationWork.Model.Entity.Link;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public final void setBaseUrl(String baseUrl) throws IOException {
        this.baseUrl = baseUrl;
        this.doc = Jsoup.connect(baseUrl).get();
    }


    // Возвращает ссылки со страницы

    private List<Element> getLinks(String url) {
        Elements links = doc.select("a[href]");
        return links.stream().filter(x -> x.attr("href").contains(url) || x.attr("href").startsWith("/"))
                .collect(Collectors.toList());
    }

    // Title страницы

    public final String getTitle() {
        return doc.title();
    }

    // Обходит все ссылки по заданной глубине

    public final void crawl(int deep) throws IOException {
        Connection code = Jsoup.connect(baseUrl);
        this.doc = code.get();
        for (int i = deep; i > 0; i--) {
            List<Element> list = getLinks(baseUrl);
            if (list.size() != 0) {
                for (Element link : list) {
                    saveLink(link.attr("href"), null);
                    Link link1 = linkDao.getLinkByAdress(link.attr("href"));
                    Long id = link1.getId();
//                    Long test = linkDao.getLinkByAdress(link.attr("href")).getParentIdSet();
                    System.out.println(String.format("%s --->", link1.getAdress()));
                    crawl(i, link.attr("href"), id);
                }

            }

        }

    }

    public final void crawl(int deep, String adress, Long index) throws IOException {
        Integer response;
        if (!adress.equals("/")) {
            String url = adress.startsWith("//") ? adress.substring(2) : adress;
            if (!url.startsWith("http://")) {
                url = "http://" + url;
            }
            try {
                Connection connect = Jsoup.connect(url);
                response = connect.response().statusCode();
                this.doc = connect.get();

            } catch (IllegalArgumentException e) {
                Connection connect = Jsoup.connect(baseUrl + url);
                response = connect.response().statusCode();
                try {
                    this.doc = connect.get();
                } catch (HttpStatusException er) {

                }
            }
            if (response != 404) {
                for (int i = deep; i > 0; i--) {
                    List<Element> list = getLinks(url);
                    if (list.size() != 0) {
                        for (Element link : list) {
                            saveLink(link.attr("href"), null);
                            System.out.println(String.format("---> %s", link.attr("href")));
                            if (!linkPresent(link.attr("href")))
                                crawl(i, link.attr("href"), index);
                        }
                    }

                }
            }
        }

    }

    // Сохраняет новую ссылку или обновляет существующую

    private void saveLink(String url, Long parentId) {

        if (linkPresent(url)) {
            Link link = new Link();
            link.setAdress(url);
            if (parentId != null)
                link.setParentId(parentId);
            linkDao.save(link);

        } else {
//            linkDao.save(url, parentId);
        }
    }

    // Ссылка присутствует в базе

    private boolean linkPresent(String url) {
        Link entity = linkDao.getLinkByAdress(url);
        return entity != null;
    }

}
