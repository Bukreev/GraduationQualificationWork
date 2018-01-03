package ru.GraduationQualificationWork.Model.DAO;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import ru.GraduationQualificationWork.Model.Entity.Link;
import ru.GraduationQualificationWork.Model.Repositories.LinkRepository;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LinkDaoTest {

    private final Link link1;
    private final Link link2;
    private final LinkDao linkDao;
    private final LinkRepository linkRepository;

    public LinkDaoTest() {
        this.link1 = new Link();
        this.link2 = new Link();
        List<Link> links = new LinkedList<>();
        {
            link1.setAdress("http://www.google.com");
            link2.setAdress("");
            links.add(link1);
            links.add(link2);
        }
        this.linkRepository = mock(LinkRepository.class);
        when(linkRepository.findAll()).thenReturn(links);
        when(linkRepository.findOne(1L)).thenReturn(links.get(0));
        this.linkDao = new LinkDao(linkRepository);
    }

    @Test
    public final void getAllTest(){
       List<Link> links = linkDao.getAllLinks();
       assertNotNull("Вернулся пустой список", links);
       assertEquals(links.size(), 2);
    }

    @Test
    public final void getOneTest() {
        Link link = linkDao.getLinkById(1L);
        assertEquals(link.getAdress(), "http://www.google.com");
    }
}
