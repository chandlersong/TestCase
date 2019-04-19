package me.chandlersong.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static com.google.protobuf.util.JsonFormat.parser;
import static me.chandlersong.protobuf.People.Person.newBuilder;

@Slf4j
public class PeopleTest {

    @Test
    public void testFirstProtoBuf() throws InvalidProtocolBufferException {
        People.Person.Builder builder = newBuilder();
        builder.setId(1);
        builder.setName("chandler");
        builder.setEmail("chandler605@gmail.com");

        People.Person person = builder.build();
        System.out.println("before:" + person);

        System.out.println("===Person Byte:");
        for (byte b : person.toByteArray()) {
            System.out.print(b);
        }
        System.out.println("================");

        String print = JsonFormat.printer().print(person);
        log.info("json:{}", print);

        People.Person.Builder jsonBuilder = newBuilder();
        parser().merge(print, jsonBuilder);
        log.info("person from json:{}", jsonBuilder.build());

        byte[] byteArray = person.toByteArray();
        People.Person p2 = People.Person.parseFrom(byteArray);
        System.out.println("after id:" + p2.getId());
        System.out.println("after name:" + p2.getName());
        System.out.println("after email:" + p2.getEmail());
    }
}
