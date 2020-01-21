package me.chandlersong.serialization;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import me.chandlersong.protobuf.PersonFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

import static com.google.protobuf.util.JsonFormat.parser;
import static me.chandlersong.protobuf.PersonFactory.Person.newBuilder;

@Slf4j
public class ProtoBufTest {

    @Test
    public void testFirstProtoBuf() throws InvalidProtocolBufferException {
        PersonFactory.Person.Builder builder = newBuilder();
        builder.setId(1);
        builder.setName("chandler");
        builder.setEmail("chandler605@gmail.com");

        PersonFactory.Person person = builder.build();
        log.info("before:" + person);

        log.info("===Person Byte:");
        log.info("person byte:{}", Hex.encodeHexString(person.toByteArray()));
        log.info("================");

        String print = JsonFormat.printer().print(person);
        log.info("json:{}", print);

        PersonFactory.Person.Builder jsonBuilder = newBuilder();
        parser().merge(print, jsonBuilder);
        log.info("person from json:{}", jsonBuilder.build());

        byte[] byteArray = person.toByteArray();
        PersonFactory.Person p2 = PersonFactory.Person.parseFrom(byteArray);
        log.info("after id:" + p2.getId());
        log.info("after name:" + p2.getName());
        log.info("after email:" + p2.getEmail());
    }

    @Test
    public void testTranslated() throws InvocationTargetException, IllegalAccessException {

        PersonObj personObj = new PersonObj();
        personObj.setId(123);
        personObj.setName("chandler");
        personObj.setEmail("chandler605@gmail.com");

        PersonFactory.Person.Builder builder = newBuilder();
        PersonFactory.Person person = builder.build();

        BeanUtils.copyProperties(person, personObj);

        log.info("person bean of protobuf:{}", person.getEmail());
    }

    @Test
    public void testAny() throws InvocationTargetException, IllegalAccessException {

        PersonObj personObj = new PersonObj();
        personObj.setId(123);
        personObj.setName("chandler");
        personObj.setEmail("chandler605@gmail.com");

        PersonFactory.Person.Builder builder = newBuilder();
        builder.setDetails(1, Any.newBuilder().build());
        PersonFactory.Person person = builder.build();

        BeanUtils.copyProperties(person, personObj);

        log.info("person bean of protobuf:{}", person.getEmail());
    }

    @Test
    public void testOneOf() {


        PersonFactory.Person.Builder builder = newBuilder();
        builder.setSubMessage1("subMessage1");
        builder.setSubMessage2("subMessage2");
        PersonFactory.Person person = builder.build();
        log.info("sub message 2:{}", person.getSubMessage2());
        // submessage1 has been cleared
        log.info("sub message 1:{}", person.getSubMessage1());


    }
}

