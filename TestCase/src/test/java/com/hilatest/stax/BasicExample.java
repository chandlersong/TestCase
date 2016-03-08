package com.hilatest.stax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

public class BasicExample {

    private static Logger logger = Logger.getLogger(BasicExample.class);

    @Test
    public void testBasic() throws FileNotFoundException, XMLStreamException {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(
                                                                  new FileReader(XML_FILE));

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch (event.getEventType()) {
            case XMLStreamConstants.START_ELEMENT: {
                StartElement startElement = event.asStartElement();
                logger.info("start element");
                logger.info(startElement.getName());
                logger.info(startElement);
                break;
            }
            case XMLStreamConstants.CHARACTERS: {

                Characters characters = event.asCharacters();
                logger.info("CHARACTERS");
                logger.info(characters.getData());
                logger.info(characters.isWhiteSpace());
                break;
            }
            case XMLStreamConstants.END_ELEMENT: {
                EndElement endElement = event.asEndElement();
                logger.info("end element");
                logger.info(endElement.getName());
                logger.info(endElement);
                break;
            }

            }
        }
    }

    private static final File XML_FILE = new File("src/main/resources/xml/cars.xml");

    @Before
    public void initial() {
        System.out.println(XML_FILE.exists());
    }
}
