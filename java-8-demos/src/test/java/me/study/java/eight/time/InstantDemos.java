package me.study.java.eight.time;

import org.junit.Test;
import org.slf4j.Logger;

import java.time.Clock;
import java.time.Instant;

import static org.slf4j.LoggerFactory.getLogger;

public class InstantDemos {


    private static final Logger logger = getLogger(InstantDemos.class);

    @Test
    public void testDiff() {
        logger.info("instant now with utc:{}", Instant.now(Clock.systemUTC()));
        logger.info("instant now default zone:{}", Instant.now(Clock.systemDefaultZone()));
        logger.info("instant now from currentTime Millis:{}", Instant.ofEpochMilli(System.currentTimeMillis()));
        long date = System.currentTimeMillis();
        long date2 = date / 1000;
        logger.info("instant now from currentTime Millis:{}", Instant.ofEpochMilli(date));
        logger.info("instant now from currentTime Millis to second:{}", Instant.ofEpochSecond(date2));
    }
}
