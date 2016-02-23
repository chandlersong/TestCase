package com.hilatest.dom4j;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class TestDTD {

    @Test
    public void test() throws DocumentException, IOException {
        String xml = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n"
                + "<!DOCTYPE xml [<!ENTITY copy \"&#169;\"> "
                + "<!ENTITY logo SYSTEM \"http://www.xmlwriter.net/logo.gif\" NDATA gif>"
                + "<!ENTITY deg \"&#x00b0;\"> ]>\n" + "<root />";

        SAXReader reader = new SAXReader(false);
        reader.setIncludeInternalDTDDeclarations(true);
        reader.setIncludeExternalDTDDeclarations(true);

        Document doc = reader.read(new StringReader(xml));
        StringWriter wr = new StringWriter();
        XMLWriter writer = new XMLWriter(wr);
        writer.write(doc);

        String xml2 = wr.toString();
        System.out.println(xml2);

        Document doc2 = DocumentHelper.parseText(xml2);
    }
}
