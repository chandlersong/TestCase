package org.commmstudy.quartz.state;

import java.util.Date;

import org.apache.log4j.Logger;
import org.commmstudy.quartz.job.StatefulDumbJob;
import org.junit.Test;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MisFireExample {
    Logger log = Logger.getLogger(MisFireExample.class);

    @Test
    public void testSimple() throws SchedulerException {

        log.info("------- Initializing -------------------");

        // First we must get a reference to a scheduler
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        log.info("------- Initialization Complete -----------");

        log.info("------- Scheduling Jobs -----------");

        // jobs can be scheduled before start() has been called

        // get a "nice round" time a few seconds in the future...
        Date startTime = DateBuilder.nextGivenSecondDate(null, 15);

        // statefulJob1 will run every three seconds
        // (but it will delay for ten seconds)
        JobDetail job = JobBuilder.newJob(StatefulDumbJob.class).withIdentity("statefulJob1", "group1")
                .usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L).build();

        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()).build();

        Date ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000 + " seconds");

        // statefulJob2 will run every three seconds
        // (but it will delay for ten seconds - and therefore purposely misfire after a few iterations)
        job = JobBuilder.newJob(StatefulDumbJob.class).withIdentity("statefulJob2", "group1")
                .usingJobData(StatefulDumbJob.EXECUTION_DELAY, 10000L).build();

        trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever()
                        .withMisfireHandlingInstructionNowWithExistingCount()) // set misfire instructions
                .build();

        ft = sched.scheduleJob(job, trigger);
        log.info(job.getKey() + " will run at: " + ft + " and repeat: " + trigger.getRepeatCount() + " times, every "
                + trigger.getRepeatInterval() / 1000 + " seconds");

        log.info("------- Starting Scheduler ----------------");

        // jobs don't start firing until start() has been called...
        sched.start();

        log.info("------- Started Scheduler -----------------");

        try {
            // sleep for ten minutes for triggers to file....
            Thread.sleep(600L * 1000L);
        } catch (Exception e) {
            //
        }

        log.info("------- Shutting Down ---------------------");

        sched.shutdown(true);

        log.info("------- Shutdown Complete -----------------");

        SchedulerMetaData metaData = sched.getMetaData();
        log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");
    }
}
