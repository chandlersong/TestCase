package org.commmstudy.quartz.state;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.commmstudy.quartz.job.ColorJob;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:stateJobExample.xml")
public class StateJobExample extends AbstractJUnit4SpringContextTests {

    private static final Logger log = Logger.getLogger(StateJobExample.class);

    @Test
    public void testSimple() throws SchedulerException {
        log.info("------- test Simple --------");
        // get a "nice round" time a few seconds in the future....
        Date startTime = DateBuilder.nextGivenSecondDate(null, 10);
        // job1 will only run 5 times (at start time, plus 4 repeats), every 10 seconds
        JobDetail job1 = JobBuilder.newJob(ColorJob.class).withIdentity("job1", "group1").build();

        SimpleTrigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(5))
                .build();

        // pass initialization parameters into the job
        job1.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Green");
        job1.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        Date scheduleTime1 = sched.scheduleJob(job1, trigger1);
        log.info(job1.getKey() + " will run at: " + scheduleTime1 + " and repeat: " + trigger1.getRepeatCount()
                + " times, every " + trigger1.getRepeatInterval() / 1000 + " seconds");

        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting 60 seconds... -------------");
        sched.start();
        try {
            // wait five minutes to show jobs
            Thread.sleep(60L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }
    }

    @Test
    public void testTwoJob() throws SchedulerException {
        log.info("------- test two job --------");

        log.info("------- Initialization Complete --------");

        log.info("------- Scheduling Jobs ----------------");

        // get a "nice round" time a few seconds in the future....
        Date startTime = DateBuilder.nextGivenSecondDate(null, 10);

        // job1 will only run 5 times (at start time, plus 4 repeats), every 10 seconds
        JobDetail job1 = JobBuilder.newJob(ColorJob.class).withIdentity("job1", "group1").build();

        SimpleTrigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4))
                .build();

        // pass initialization parameters into the job
        job1.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Green");
        job1.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        // schedule the job to run
        Date scheduleTime1 = sched.scheduleJob(job1, trigger1);
        log.info(job1.getKey() + " will run at: " + scheduleTime1 + " and repeat: " + trigger1.getRepeatCount()
                + " times, every " + trigger1.getRepeatInterval() / 1000 + " seconds");

        // job2 will also run 5 times, every 10 seconds
        JobDetail job2 = JobBuilder.newJob(ColorJob.class).withIdentity("job2", "group1").build();

        SimpleTrigger trigger2 = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1").startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(4))
                .build();

        // pass initialization parameters into the job
        // this job has a different favorite color!
        job2.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Red");
        job2.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        // schedule the job to run
        Date scheduleTime2 = sched.scheduleJob(job2, trigger2);
        log.info(job2.getKey().toString() + " will run at: " + scheduleTime2 + " and repeat: "
                + trigger2.getRepeatCount()
                + " times, every " + trigger2.getRepeatInterval() / 1000 + " seconds");

        log.info("------- Starting Scheduler ----------------");

        // All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started
        sched.start();

        log.info("------- Started Scheduler -----------------");

        log.info("------- Waiting 60 seconds... -------------");
        try {
            // wait five minutes to show jobs
            Thread.sleep(60L * 1000L);
            // executing...
        } catch (Exception e) {
            //
        }

        log.info("------- Shutting Down ---------------------");

        sched.shutdown(true);

        log.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }

    @Resource(name = "quartzScheduler")
    private Scheduler sched;

    @After
    public void after() throws SchedulerException {
        if (sched.isStarted()) {
            log.info("------- Shutting Down ---------------------");
            sched.shutdown(true);
            log.info("------- Shutdown Complete -----------------");
        }
    }
}
