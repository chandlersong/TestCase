package org.commmstudy.ehcache.selfpopulatingcache;

import net.sf.ehcache.constructs.blocking.UpdatingCacheEntryFactory;

public class ExampleUpdatingCacheEntryFactory
        extends ExampleCacheEntryFactory
        implements UpdatingCacheEntryFactory {
    public void updateEntryValue(Object key, Object value) throws Exception {
        System.out.println("~~~~~~UPDATING entry for key = " + key);
        final StringBuffer stringBuffer = (StringBuffer) value;
        stringBuffer.append(stringBuffer.length());
    }

}