package org.commmstudy.hibernate.utils;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.stat.Statistics;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.statistics.StatisticsGateway;

public class MonitorPrinter {

    private static final Logger logger = Logger.getLogger(MonitorPrinter.class);

    private Statistics defaultStats;

    private CacheManager defaultCacheManager;

    public void printStats(String tag) {
        if (defaultStats == null) {
            logger.info("no defaultStats, return!!!");
        }
        printStats(tag, defaultStats);
    }

    public void printStats(String tag, Statistics stats) {
        logger.info("***** " + tag + " *****");

        logger.info("enabled="
                + stats.isStatisticsEnabled());
        logger.info("TransactionCount="
                + stats.getTransactionCount());
        logger.info("Fetch Count="
                + stats.getEntityFetchCount());
        logger.info("Second Level Hit Count="
                + stats.getSecondLevelCacheHitCount());
        logger.info("Second Level Miss Count="
                + stats.getSecondLevelCacheMissCount());
        logger.info("Second Level Put Count="
                + stats.getSecondLevelCachePutCount());
        logger.info("***********************");
    }

    public void printCachePool(String tag, String cacheId) {
        printCachePool(tag, cacheId, false);
    }

    public void printCachePool(String tag, CacheManager cacheManager, String cacheId) {
        printCachePool(tag, cacheManager, cacheId, false);
    }

    public void printCachePool(String tag, String cacheId, boolean printElm) {
        if (defaultCacheManager == null) {
            logger.info("no defaultCacheManager, return!!!");
        }
        printCachePool(tag, defaultCacheManager, cacheId, printElm);
    }

    public void printCachePool(String tag, CacheManager cacheManager, String cacheId, boolean printElm) {
        logger.info("***** " + tag + " *****");
        Cache cache = cacheManager.getCache(cacheId);
        if (cache == null) {
            logger.info("cache not exist!");
            return;
        }

        logger.info("cache :" + cache.getSize());
        StatisticsGateway statics = cache.getStatistics();
        logger.info("Local Heap  Size:" + statics.getLocalHeapSize());
        logger.info("Size:" + statics.getSize());
        logger.info("Writer Queue Length Size:" + statics.getWriterQueueLength());
        logger.info("print keys :");

        if (printElm) {
            List<Object> keys = cache.getKeys();
            for (Object key : keys) {
                logger.info("keyclass:" + key.getClass() + ",key value" + key);
                logger.info("value class:" + key.getClass() + ",value" + cache.get(key).getObjectValue());
            }
        }

        logger.info("***********************");
    }

    public void printALLCachePool(String tag) {

        printALLCachePool(tag, false);
    }

    public void printALLCachePool(String tag, boolean printElm) {
        if (defaultCacheManager == null) {
            logger.info("no defaultCacheManager, return!!!");
        }
        printALLCachePool(tag, defaultCacheManager, printElm);
    }

    public void printALLCachePool(String tag, CacheManager cacheManager, boolean printElm) {
        logger.info("***** " + tag + " *****");
        String[] cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames) {
            logger.info("cache :" + cacheName);
            printCachePool(tag, cacheManager, cacheName, printElm);
        }
        logger.info("***********************");
    }

    public Statistics getDefaultStats() {
        return defaultStats;
    }

    public void setDefaultStats(Statistics defaultStats) {
        this.defaultStats = defaultStats;
    }

    public CacheManager getDefaultCacheManager() {
        return defaultCacheManager;
    }

    public void setDefaultCacheManager(CacheManager defaultCacheManager) {
        this.defaultCacheManager = defaultCacheManager;
    }

}
