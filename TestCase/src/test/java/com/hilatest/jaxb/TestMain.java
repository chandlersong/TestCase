package com.hilatest.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.hilatest.Constant;

public class TestMain {

    private static Logger logger = Logger.getLogger(TestMain.class);

    @Test
    public void testHelloWorld() {
        JAXBItem customer = new JAXBItem();
        customer.setId(100);
        customer.setName("suo");
        customer.setAge(29);
        customer.map1Put("test1", "test1");
        customer.map2Put("test2", "test2");
        customer.list1add("list1");
        customer.list1add("list2");
        try {
            File file = new File(outputFolder, "JAXB.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(JAXBItem.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            // output pretty printed
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(customer, file);
            jaxbMarshaller.marshal(customer, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void setup() {
        outputFolder = new File(Constant.OutputPath, "jaxbTest");
        logger.info("output folder:" + outputFolder.getAbsolutePath());
        FileUtils.deleteQuietly(outputFolder);
        outputFolder.mkdirs();
    }

    private File outputFolder;
}
