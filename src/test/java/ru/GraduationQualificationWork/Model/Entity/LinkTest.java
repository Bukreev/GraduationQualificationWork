package ru.GraduationQualificationWork.Model.Entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinkTest {

    private final Link link;

    public LinkTest() {
        this.link = new Link();

        {
            this.link.setId(1L);
            this.link.setAdress("http://www.google.com");
            this.link.setLevel(1);
        }
    }

    @Test
    public final void getIdTest() {
        assertTrue(link.getId().equals(1L));
    }

    @Test
    public final void getAdress() {
        assertEquals(link.getAdress(), "http://www.google.com");
    }

    @Test
    public final void getLevel() {
        assertTrue(link.getLevel() == 1);
    }


}
