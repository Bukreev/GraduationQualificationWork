//package ru.GraduationQualificationWork.Services;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import ru.GraduationQualificationWork.Model.Entity.Link;
//
//import static org.junit.Assert.assertEquals;
//
//@Component
//public class LinkServiceTest {
//
//    private final LinkService linkService;
//    private final String testUrl;
//
//    @Autowired
//    public LinkServiceTest(LinkService linkService) {
//        this.linkService = linkService;
//        this.testUrl = "https://www.google.ru/imghp?hl=ru&tab=wi";
//    }
//
//    @Test
//    public final void saveLinkTest() {
//        linkService.save(testUrl);
//        Link link = linkService.findById(1);
//        assertEquals(testUrl, link);
//    }
//}
