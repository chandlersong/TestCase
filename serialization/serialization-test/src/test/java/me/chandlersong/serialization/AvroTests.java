package me.chandlersong.serialization;

import example.avro.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

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
        User user2 = new User("Ben", 7, "red");

        // Construct via builder
        User user3 = User.newBuilder()
                         .setName("Charlie")
                         .setFavoriteColor("blue")
                         .setFavoriteNumber(null)
                         .build();
    }
}
