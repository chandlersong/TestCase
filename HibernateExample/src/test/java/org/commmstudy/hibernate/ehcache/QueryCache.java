package org.commmstudy.hibernate.ehcache;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.commmstudy.hibernate.jpa.dao.MessageDao;
import org.commmstudy.hibernate.jpa.dao.impl.message.MessageRepository;
import org.commmstudy.hibernate.utils.MonitorPrinter;
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

import net.sf.ehcache.CacheManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ehcache_jpa_hibernate.xml")
public class QueryCache {

    private static final Logger logger = Logger.getLogger(QueryCache.class);

    @Test
    public void helloworld() {
        String tag = RandomStringUtils.randomAlphabetic(10);
        initialData(tag, true);

        List<Message> result = repository.findByTag(tag);
        logger.info("result size:" + result.size());
        monitorPrinter.printCachePool("after query",
                                      "org.hibernate.cache.spi.UpdateTimestampsCache", true);
        monitorPrinter.printCachePool("after query",
                                      "org.hibernate.cache.internal.StandardQueryCache", true);
        monitorPrinter.printCachePool("after query",
                                      "my_cache");

        logger.info("result size:" + repository.findByTag(tag).size());
        monitorPrinter.printStats("after second cache");
    }

    public void initialData(String tag, boolean otherdata) {
        logger.info("start to initial data,tag:" + tag);
        saveBatchData(tag, 100);

        if (otherdata) {
            logger.info("need to add other data");
            for (int i = 0; i < 9; i++) {
                saveBatchData(RandomStringUtils.randomAlphabetic(10), 100);
            }
        }

    }

    public void saveBatchData(String tag, int size) {
        logger.info("save  batch data,tag:" + tag);
        for (int i = 0; i < size; i++) {
            Message m1 = new Message();
            m1.setText(RandomStringUtils.randomAlphabetic(10));
            m1.setTag(tag);
            repository.save(m1);
        }

    }

    @Before
    public void setup() {
        sessionFactory = ((HibernateEntityManagerFactory) entityManagerFactory).getSessionFactory();

        EntityManagerFactoryInfo entityManagerFactoryInfo = (EntityManagerFactoryInfo) entityManagerFactory;
        EntityManagerFactory emf = entityManagerFactoryInfo.getNativeEntityManagerFactory();
        EntityManagerFactoryImpl emfImp = (EntityManagerFactoryImpl) emf;
        stats = emfImp.getSessionFactory().getStatistics();
        stats.setStatisticsEnabled(true);

        monitorPrinter.setDefaultCacheManager(cacheManager);
        monitorPrinter.setDefaultStats(stats);
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

    private MonitorPrinter monitorPrinter = new MonitorPrinter();

}
