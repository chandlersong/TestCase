package org.commmstudy.jibx.codefirst;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.commmstudy.jibx.codefirst.entity.Person;
import org.commmstudy.jibx.utility.JibxTransformer;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
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

    @Test
    public void testTwoUnMarshall() throws FileNotFoundException, JiBXException {
        IBindingFactory bfact = BindingDirectory.getFactory(Person.class);
        IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
        FileInputStream in = new FileInputStream(TWO_PERSON_FILE);
        uctx.setDocument(in, "UTF-8");
        System.out.println(uctx.getStackDepth());
    }

    private JibxTransformer<Person> trasformer;

    @Before
    public void initial() throws JiBXException {
        trasformer = new JibxTransformer<Person>(Person.class);
    }

    private final static String ONE_PERSON_FILE = "src/test/resources/testData/OnePerson.xml";

    private final static String TWO_PERSON_FILE = "src/test/resources/testData/TwoPerson.xml";
}
