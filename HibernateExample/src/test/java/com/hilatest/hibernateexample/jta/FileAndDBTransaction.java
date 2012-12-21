package com.hilatest.hibernateexample.jta;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:file-db-jta-transaction.xml")
public class FileAndDBTransaction extends AbstractJUnit4SpringContextTests {

}
