package ru.GraduationQualificationWork.Crawler;

import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.GraduationQualificationWork.Model.DAO.LinkDao;
import ru.GraduationQualificationWork.Model.Entity.Link;

import java.io.DataInput;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class Crawler {


    private final LinkDao linkDao;
    private String baseUrl;
    private Document doc;
    private Set<String> extentions;

    @Autowired
    public Crawler(LinkDao linkDao) throws IOException {
        this.linkDao = linkDao;
        extentions = new HashSet<>();
    }

    // Устанавливает BaseURl

    public final void setBaseUrl(String baseUrl) throws IOException {
        this.baseUrl = baseUrl;
        this.doc = Jsoup.connect(baseUrl).get();
    }


    // Возвращает ссылки со страницы

    private List<String> getLinks(String url) {
        List<String> clearLinks = new ArrayList<>();
        Elements linksElement = doc.select("a[href]");
        String domainName = getDomainName(baseUrl);
        List<String> links = linksElement.stream().map(x -> x.attr("href")).filter(x -> x.contains(domainName))
                .collect(Collectors.toList());
        for (String link : links) {
            if (exceptExtention(extentions, link))
                continue;
            if (link.startsWith("//")) {
                link = "http:" + link;
                clearLinks.add(link);
            } else if (link.startsWith("/") && link.length() > 1) {
                link = link.substring(1);
                clearLinks.add(link);
            } else if (!link.equals("/")) {
                clearLinks.add(link);
            }
        }
        return clearLinks;
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
            List<String> list = getLinks(baseUrl);
            if (list.size() != 0) {
                for (String link : list) {
                    saveLink(link, null, i);
                    Long id = linkDao.getLinkByAdress(link).getId();
//                    System.out.println(String.format("%s --->", link));
                    crawl(i, link, id);
                }

            }

        }

    }

    private void crawl(int deep, String url, Long index) throws IOException {
        Integer response;
        try {
            Connection connect = Jsoup.connect(url);
            response = connect.response().statusCode();
            this.doc = connect.get();

        } catch (IllegalArgumentException e) {
            Connection connect = Jsoup.connect(baseUrl + "/" + url);
            response = connect.response().statusCode();
            try {
                this.doc = connect.get();
            } catch (HttpStatusException er) {

            }
        }

        if (response != 404) {
            for (int i = deep; i > 0; i--) {
                List<String> list = getLinks(url);
                if (list.size() != 0) {
                    for (String link : list) {
                        if (linkPresent(link)) {
                            addLinkParent(link, index);
                        } else {
                            saveLink(link, null, i);
//                            System.out.println(String.format("---> %s", link));
                            crawl(i, link, index);
                        }
                    }
                }

            }
        }
    }

    // Сохраняет новую ссылку или обновляет существующую

    private void saveLink(String url, Link parentId, Integer level) {
        Link link = new Link();
        link.setAdress(url);
        link.setLevel(level);
        if (parentId != null)
            link.addParent(parentId);
        linkDao.save(link);

    }

    // Ссылка присутствует в базе

    private boolean linkPresent(String url) {
        Link entity = linkDao.getLinkByAdress(url);
        return entity != null;
    }

    // Получает доменное имя

    private String getDomainName(String link) {
        String result = null;
        String pattern = "(?<=www.)(.*)(?=\\.)";
        Pattern compailed = Pattern.compile(pattern);
        Matcher matcher = compailed.matcher(link);

        while (matcher.find()) {
            result = link.substring(matcher.start(), matcher.end());
        }

        return result;

    }

    // Добавляет предка ссылке

    private void addLinkParent(String url, Long id) {
        linkDao.addParentLink(url, id);
    }

    // Исключить файлы с расширением

    private boolean exceptExtention(Set<String> ext, String link) {
        for (String e : ext) {
            if (link.endsWith("." + e))
                return true;
        }
        return false;
    }

    // Установить исключаемые расширения

    public void setExcludeExtensions(String... args) {
        extentions.addAll(Arrays.asList(args));
    }

}
