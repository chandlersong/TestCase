package org.commmstudy.ehcache.selfpopulatingcache;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.constructs.blocking.CacheEntryFactory;
import net.sf.ehcache.constructs.blocking.SelfPopulatingCache;

public class ExampleCacheProvider {
    private CacheManager cacheManager;
    private CacheEntryFactory updatingFactory;
    public SelfPopulatingCache selfPopulatingCache;

    public ExampleCacheProvider(String cacheType) throws Exception {
        cacheManager = CacheManager.create();
        if (!cacheManager.cacheExists("abc")) {
            cacheManager.addCache("abc");
        }

        Ehcache originalCache = cacheManager.getCache("abc");

        if (cacheType == null || cacheType.equals("create")) {
            updatingFactory = new ExampleCacheEntryFactory();
        } else {
            updatingFactory = new ExampleUpdatingCacheEntryFactory();
        }
        selfPopulatingCache = new SelfPopulatingCache(originalCache, updatingFactory);
        // chache refresh thread
        Thread updatingThread = new Thread() {
            public void run() {
                while (true) {
                    System.out.println("!!!!! Doing refresh !!!!!");
                    selfPopulatingCache.refresh();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("Interrupted");
                    }
                }
            }
        };
        updatingThread.setDaemon(true);
        updatingThread.start();

    }

    public Ehcache getCache() {
        return selfPopulatingCache;
    }
}