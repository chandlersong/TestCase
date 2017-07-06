package org.commmstudy.hibernate.ehcache;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.commmstudy.hibernate.jpa.dao.MessageDao;
import org.commmstudy.hibernate.jpa.dao.impl.message.MessageRepository;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.hibernate.jpa.internal.EntityManagerFactoryImpl;
import org.hibernate.stat.Statistics;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hilatest.hibernate.inaction.chapter1.Message;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.statistics.StatisticsGateway;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ehcache_jpa_hibernate_wirte_behind.xml")
public class WriteBehindExample {

    private static final Logger logger = Logger.getLogger(WriteBehindExample.class);

    @Test
    public void helloworld() throws InterruptedException {

        LinkedBlockingDeque<Message> saveList = new LinkedBlockingDeque<Message>();
        CountDownLatch saveLatch = new CountDownLatch(1000);
        CountDownLatch updateLatch = new CountDownLatch(1000);
        ExecutorService saveExecutor = Executors.newFixedThreadPool(5);
        ExecutorService updateExecutor = Executors.newFixedThreadPool(5);
        Thread t = new Thread(new MonitorThread(saveLatch, cacheManager, stats));
        t.start();
        for (int i = 0; i < 5; i++) {
            saveExecutor.execute(new SaveThread(saveList, saveLatch, repository));
        }
        saveLatch.await();
        updateLatch.await();
        logger.info("finish  import");
    }

    private void printStats(String tag) {
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
    }

    public void printCachePool(String tag) {
        logger.info("***** " + tag + " *****");
        Cache cache = cacheManager.getCache("com.hilatest.hibernate.inaction.chapter1.Message");
        logger.info("cache :" + cache.getSize());
        StatisticsGateway statics = cache.getStatistics();
        logger.info("Local Heap  Size:" + statics.getLocalHeapSize());
        logger.info("Size:" + statics.getSize());
        logger.info("Writer Queue Length Size:" + statics.getWriterQueueLength());
        logger.info("print keys :");

        logger.info("***********************");
    }

    @Before
    public void setup() {
        sessionFactory = ((HibernateEntityManagerFactory) entityManagerFactory).getSessionFactory();

        EntityManagerFactoryInfo entityManagerFactoryInfo = (EntityManagerFactoryInfo) entityManagerFactory;
        EntityManagerFactory emf = entityManagerFactoryInfo.getNativeEntityManagerFactory();
        EntityManagerFactoryImpl emfImp = (EntityManagerFactoryImpl) emf;
        stats = emfImp.getSessionFactory().getStatistics();
        stats.setStatisticsEnabled(true);

    }

    @Resource(name = "entityManagerFactory")
    private EntityManagerFactory entityManagerFactory;

    private SessionFactory sessionFactory;

    @Resource(name = "messageDao")
    private MessageDao dao;

    @Resource(name = "messageRepository")
    private MessageRepository repository;

    @Resource(name = "ehcache")
    private CacheManager cacheManager;

    private Statistics stats;
}

class SaveThread implements Runnable {

    private static final Logger logger = Logger.getLogger(SaveThread.class);

    private LinkedBlockingDeque<Message> saveList;

    private CountDownLatch latch;

    private MessageRepository repository;

    private String tag;

    public SaveThread(LinkedBlockingDeque<Message> saveList, CountDownLatch latch, MessageRepository repository) {
        super();
        this.saveList = saveList;
        this.latch = latch;
        this.repository = repository;
        tag = RandomStringUtils.randomAlphabetic(10);
        logger.info("new tag:" + tag);
    }

    @Override
    public void run() {

        while (latch.getCount() != 0) {
            Message m1 = new Message();
            m1.setText(RandomStringUtils.randomAlphabetic(10));
            m1.setTag(tag);
            repository.save(m1);
            latch.countDown();
            saveList.push(m1);
        }
    }

}

class MonitorThread implements Runnable {

    private static final Logger logger = Logger.getLogger(MonitorThread.class);

    private CountDownLatch latch;

    private CacheManager cacheManager;

    private Statistics stats;

    public MonitorThread(CountDownLatch latch, CacheManager cacheManager, Statistics stats) {
        super();

        this.latch = latch;
        this.cacheManager = cacheManager;
        this.stats = stats;
    }

    @Override
    public void run() {
        int index = 0;
        while (latch.getCount() != 0) {
            printStats(String.valueOf(index));
            printCachePool(String.valueOf(index));
            index++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void printStats(String tag) {
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
    }

    public void printCachePool(String tag) {
        logger.info("***** " + tag + " *****");
        Cache cache = cacheManager.getCache("com.hilatest.hibernate.inaction.chapter1.Message");
        logger.info("cache :" + cache.getSize());
        StatisticsGateway statics = cache.getStatistics();
        logger.info("Local Heap  Size:" + statics.getLocalHeapSize());
        logger.info("Size:" + statics.getSize());
        logger.info("Writer Queue Length Size:" + statics.getWriterQueueLength());
        logger.info("print keys :");

        logger.info("***********************");
    }

}
