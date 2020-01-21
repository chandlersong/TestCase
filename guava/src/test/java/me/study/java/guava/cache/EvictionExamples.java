package me.study.java.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.Weigher;
import org.junit.Test;
import org.slf4j.Logger;

import java.util.concurrent.ExecutionException;

import static org.slf4j.LoggerFactory.getLogger;

public class EvictionExamples {


    private static final Logger logger = getLogger(EvictionExamples.class);

    @Test
    public void simpleCache() throws ExecutionException {
        final int[] times = {0};
        LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
                .maximumWeight(500)
                .weigher((Weigher<Integer, String>) (key, value) -> 10 - key % 10)
                .build(new CacheLoader<Integer, String>() {
                    @Override
                    public String load(Integer key) {
                        logger.info("{} has been initialised", key);
                        times[0]++;
                        return String.valueOf(key);
                    }
                });


        for (int i = 0; i < 101; i++) {
            cache.get(i);
        }

        logger.info("cache size:{}", cache.size());
        for (int i = 0; i < 101; i++) {
            cache.get(i);
        }

        logger.info("load times:{}", times[0]);
    }
}
