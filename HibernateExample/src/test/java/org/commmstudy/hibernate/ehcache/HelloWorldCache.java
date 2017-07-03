package org.commmstudy.hibernate.ehcache;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:ehcache_jpa_hibernate.xml")
public class HelloWorldCache {

    private static final Logger logger = Logger.getLogger(HelloWorldCache.class);

    @Test
    public void helloworld() {

        logger.info(entityManagerFactory);
        EntityManager em = entityManagerFactory.createEntityManager();
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);
        Message m1 = new Message();
        m1.setText("hibernateTest");
        Message id = repository.save(m1);
        printStats(1);
        List<Message> m2 = repository.findByText("hibernateTest");
        printStats(2);
        Message m3 = repository.findOne(id.getId());
        printStats(3);

        Message m4 = repository.findOne(id.getId());
        logger.info("***** " + m4.getText() + " *****");
        printStats(4);

    }

    @Test
    public void save() {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Message m1 = new Message();
        m1.setText("hibernateTest");
        em.persist(m1);
        em.getTransaction().commit();
        printStats(1);

    }

    @Test
    public void testSearch() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from " + Message.class.getName());
        query.setHint("org.hibernate.cacheable", true);
        query.getResultList();
        em.getTransaction().commit();
        printStats(1);

        em.getTransaction().begin();
        query = em.createQuery("from " + Message.class.getName());
        query.setHint("org.hibernate.cacheable", true);
        query.getResultList();
        em.getTransaction().commit();
        printStats(2);

        String[] cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames) {
            logger.info("cache :" + cacheName);
        }
    }

    @Test
    public void detectiveCache() {
        printCachePool("before search");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("from " + Message.class.getName());
        query.setHint("org.hibernate.cacheable", true);
        query.getResultList();
        em.getTransaction().commit();
        printStats(1);
        printCachePool("after search");
    }

    public void printCachePool(String tag) {
        logger.info("***** " + tag + " *****");
        String[] cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames) {
            logger.info("cache :" + cacheName);

            Cache cache = cacheManager.getCache(cacheName);
            logger.info("cache :" + cache.getSize());
            logger.info("print keys :");

            List<Object> keys = cache.getKeys();
            for (Object key : keys) {
                logger.info("keyclass:" + key.getClass() + ",key value" + key);
            }
        }
        logger.info("***********************");
    }

    private void printStats(int i) {
        logger.info("***** " + i + " *****");

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

    @Before
    public void setup() {
        sessionFactory = ((HibernateEntityManagerFactory) entityManagerFactory).getSessionFactory();

        EntityManagerFactoryInfo entityManagerFactoryInfo = (EntityManagerFactoryInfo) entityManagerFactory;
        EntityManagerFactory emf = entityManagerFactoryInfo.getNativeEntityManagerFactory();
        EntityManagerFactoryImpl emfImp = (EntityManagerFactoryImpl) emf;
        stats = emfImp.getSessionFactory().getStatistics();
        stats.setStatisticsEnabled(true);

        System.out.println(cacheManager);
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
