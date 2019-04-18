package me.chandlersong.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;
import org.junit.Test;

public class PeopleTest {

    @Test
    public void testFirstProtoBuf() throws InvalidProtocolBufferException {
        People.Person.Builder builder = People.Person.newBuilder();
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

        byte[] byteArray = person.toByteArray();
        People.Person p2 = People.Person.parseFrom(byteArray);
        System.out.println("after id:" + p2.getId());
        System.out.println("after name:" + p2.getName());
        System.out.println("after email:" + p2.getEmail());
    }
}
