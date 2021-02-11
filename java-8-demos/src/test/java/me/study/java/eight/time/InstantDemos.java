package me.study.java.eight.time;

import org.junit.Test;
import org.slf4j.Logger;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import static org.slf4j.LoggerFactory.getLogger;

public class InstantDemos {


    private static final Logger logger = getLogger(InstantDemos.class);

    @Test
    public void helloWorld(){
        Date now = new Date();
        logger.debug("now is {}",now.getTime());
    }

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

    @Test
    public void testTimeZone() {
        Instant now = Instant.now(Clock.systemUTC());
        logger.info("instant now with utc:{}", now);
        logger.info("instant now with utc,epochMilli:{}", now.toEpochMilli());
        logger.info("date from now epoch second:{}", new Date(now.toEpochMilli()));

        ZonedDateTime zoneNow = now.atZone(ZoneId.systemDefault());
        logger.info("zone now is {}", zoneNow);
        long timeZoneSeconds = zoneNow.toEpochSecond();
        logger.info("zone now with utc,epoch second:{}", timeZoneSeconds);
        logger.info("date from zone epoch second:{}", new Date(timeZoneSeconds * 1000));

        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        logger.info("offset date time is {}", offsetDateTime);
        logger.info("offset date epoch second {}", offsetDateTime.toEpochSecond());

    }

    @Test
    public void testTemporal() {

        ZonedDateTime zoneNow = ZonedDateTime.now();
        logger.info("zone now:{}", zoneNow);
        logger.info("next friday:{}", zoneNow.with(TemporalAdjusters.lastDayOfMonth()));
        logger.info("with hour 2:{}", zoneNow.withHour(2));

        LocalDateTime localNow = LocalDateTime.now();
        logger.info("local now:{}", localNow);
        logger.info("local now to instant:{}", localNow.atZone(ZoneId.systemDefault()).toInstant());
        logger.info("local now to instant:{}", localNow.toInstant(ZoneOffset.UTC));

        Instant now = Instant.now(Clock.systemUTC());
        logger.info("instant now:{}", now);
        logger.info("instant now:{}", now.minus(30, ChronoUnit.DAYS));

    }
}
