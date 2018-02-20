package ru.GraduationQualificationWork.Spider;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.GraduationQualificationWork.Model.DAO.LinkDao;

import java.io.IOException;

@RunWith(SpringRunner.class)
@ContextConfiguration
public class SpiderTest implements ApplicationContextAware {

    private Spider spider;

    @Autowired
    private ApplicationContext applicationContext;

    private final String url = "http://google.com";
    private  LinkDao linkDao;

    public SpiderTest() {

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
        assertEquals("Google", spider.getTitle());
    }

    @Test
    public final void crawlTest() {
        int count = 3;
        try {
            spider.setBaseUrl(url);
            spider.crawl(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        verify(linkDao, times(count)).save(anyString());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.spider = applicationContext.getBean(Spider.class);
    }

//    @Test
//    public final void saveLinkTest() {
//        LinkServices
//    }

}
