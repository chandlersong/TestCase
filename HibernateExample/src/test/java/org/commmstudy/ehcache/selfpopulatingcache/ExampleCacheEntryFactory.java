package org.commmstudy.ehcache.selfpopulatingcache;

import net.sf.ehcache.constructs.blocking.CacheEntryFactory;

public class ExampleCacheEntryFactory implements CacheEntryFactory {

    public Object createEntry(Object key) throws Exception {
        System.out.println("++++++creating entry for key = " + key);
        return new StringBuffer(Long.toString(Math.round(100 * Math.random())) + key + "0");
    }
}
