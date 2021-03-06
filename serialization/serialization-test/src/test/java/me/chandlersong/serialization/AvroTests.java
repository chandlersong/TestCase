package me.chandlersong.serialization;

import example.avro.Address;
import example.avro.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;

@Slf4j
public class AvroTests {

    @Test
    public void howToCreate() {
        User user1 = new User();
        user1.setName("Alyssa");


        user1.setFavoriteNumber(256);
        log.info("user:{}", user1);
        // Leave favorite color null


        // Alternate constructor
        User user2 = new User("Ben", 7, "red", new Address(), Instant.now(Clock.systemUTC()));
        log.info("time stamp of user2 is {}", user2.getCreateTimestamp());

        // Construct via builder
        User user3 = User.newBuilder()
                .setName("Charlie")
                .setFavoriteColor("blue")
                .setFavoriteNumber(3)
                .setCreateTimestamp(null)
                .build();

    }

    @Test
    public void exportAddress() throws IOException {
        Address.Builder builder = Address.newBuilder();
        builder.setName("Abc");
        Address address = builder.build();
        FileUtils.writeByteArrayToFile(new File("a.bin"),address.toByteBuffer().array());
    }

}
