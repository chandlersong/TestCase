package me.study.java.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.concurrent.ExecutionException;

import static org.slf4j.LoggerFactory.getLogger;

public class SimplestCache {

    private static final Logger logger = getLogger(SimplestCache.class);

    @Test
    public void simpleCache() throws ExecutionException {
        final int[] times = {0};
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) {
                        logger.info("{} has been initialised", key);
                        times[0]++;
                        return String.valueOf(key);
                    }
                });


        try {
            Assert.assertEquals("1", cache.get(1));
            Assert.assertEquals("1", cache.get(1));
            Assert.assertEquals(1, times[0]);
        } catch (ExecutionException e) {
            logger.error("error", e);
        }

        Assert.assertEquals("2", cache.getUnchecked(2));
        Assert.assertEquals("2", cache.getUnchecked(2));
        Assert.assertEquals(2, times[0]);


        Assert.assertEquals("not_exist", cache.get(3, () -> "not_exist"));


        cache.put(5, "directly");
        Assert.assertEquals("directly", cache.getUnchecked(5));
    }
}
