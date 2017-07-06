package org.commmstudy.ehcache.selfpopulatingcache;

import net.sf.ehcache.Ehcache;

public class Reader {
    private ExampleCacheProvider exampleCacheProvider;

    public Reader(ExampleCacheProvider exampleCacheProvider) {
        this.exampleCacheProvider = exampleCacheProvider;
    }

    public void run() {
        Ehcache cache = exampleCacheProvider.getCache();
        final StringBuffer fooOriginalBuffer = (StringBuffer) cache.get("foo").getValue();
        for (int i = 0; i < 5; i++) {
            System.out.println("----------------------------------");
            System.out.println("Starting iteration " + i);
            StringBuffer fooBuffer = (StringBuffer) cache.get("foo").getValue();
            System.out.println("fooBuffer. = " + fooBuffer.toString());
            System.out.println("fooOriginalBuffer = " + fooOriginalBuffer.toString());
            System.out.println("cache.getSize() = " + cache.getSize());
            System.out.println("----------------------------------");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("===========================create===================");
        ExampleCacheProvider cacheProvider = new ExampleCacheProvider("create");
        Reader reader = new Reader(cacheProvider);
        reader.run();
        System.out.println("===========================create===================");

        System.out.println("===========================update===================");
        cacheProvider = new ExampleCacheProvider("update");
        reader = new Reader(cacheProvider);
        reader.run();
        System.out.println("===========================update===================");
    }
}
