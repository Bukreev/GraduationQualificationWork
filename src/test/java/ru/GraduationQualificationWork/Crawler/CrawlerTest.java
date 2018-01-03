package ru.GraduationQualificationWork.Crawler;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.jsoup.nodes.Document;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.GraduationQualificationWork.Model.DAO.LinkDao;
import ru.GraduationQualificationWork.Model.Entity.Link;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class CrawlerTest implements ApplicationContextAware {

    private Crawler crawler;

    @Autowired
    private ApplicationContext applicationContext;

    private final String url = "http://localhost:8080";
    private  LinkDao linkDao;

    public CrawlerTest() {

    }


    @Before
    public final void setUp() {
        this.linkDao = mock(LinkDao.class);
//        doAnswer((Answer<Void>) invocation -> {
//            System.out.println(String.format("Мтод %s вызван с параметрами %s", invocation.getMethod()
//            , Arrays.toString(invocation.getArguments())));
//            return null;
//        }).when(linkDao).save(anyString());
    }
    @Test
    public final void getConnectionTest() {
        Document doc = crawler.getDocument();
        assertEquals("Google", doc.title());
    }

    @Test
    public final void getLinksTest() {
        List<Element> links = crawler.getLinks(url);
        assertNotNull(links);
        for(Element link : links) {
            System.out.println("### " + link.attr("href"));
            assertTrue(String.format("Ссылка %s не с целевого сайта", link.attr("href")), link.attr("href").contains("google"));
        }
    }

    @Test
    public final void crawlTest() {
        int count = 3;
        try {
            crawler.setBaseUrl(url);
            crawler.crawl(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        verify(linkDao, times(count)).save(anyString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.crawler = (Crawler) applicationContext.getBean(Crawler.class);
    }

//    @Test
//    public final void saveLinkTest() {
//        LinkServices
//    }

}
