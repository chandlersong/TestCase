package org.commmstudy.jibx.codefirst;

import java.io.FileNotFoundException;

import org.commmstudy.jibx.codefirst.entity.Person;
import org.commmstudy.jibx.utility.JibxTransformer;
import org.jibx.runtime.JiBXException;
import org.junit.Before;
import org.junit.Test;

public class SimpleExample {

    @Test
    public void testSimpleMarshall() throws FileNotFoundException, JiBXException {
        Person person = new Person();
        person.setAge(30);
        person.setName("chandler");
        System.out.println(trasformer.marshalling(person));
    }

    @Test
    public void testSimpleUnMarshall() throws FileNotFoundException, JiBXException {
        Person person = trasformer.unmarshalling(ONE_PERSON_FILE);
        System.out.println(person);
    }

    private JibxTransformer<Person> trasformer;

    @Before
    public void initial() throws JiBXException {
        trasformer = new JibxTransformer<Person>(Person.class);
    }

    private final static String ONE_PERSON_FILE = "src/test/resources/testData/OnePerson.xml";
}
