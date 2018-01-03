package ru.GraduationQualificationWork.LinkHandler;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LinkParserTest {

    private final LinkParser linkParser;

    public LinkParserTest() {
        this.linkParser = new LinkParser();
    }

//    @Test
//    public final void parseVariablesTest() {
//        Map<String, String> variablesList = linkParser.parseVariables("https://www.google.ru/imghp?hl=ru&tab=wi");
//        assertEquals(variablesList.get("hl"), "ru");
//        assertEquals(variablesList.get("tab"), "wi");
//    }
}
