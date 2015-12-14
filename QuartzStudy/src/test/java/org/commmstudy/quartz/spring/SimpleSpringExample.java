package org.commmstudy.quartz.spring;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springBasic.xml")
public class SimpleSpringExample extends AbstractJUnit4SpringContextTests {

    @Test
    public void testPOJOJob() throws SchedulerException {
        sched.scheduleJob(job1, trigger);

        try {
            // sleep for ten minutes for triggers to file....
            Thread.sleep(600L * 1000L);
        } catch (Exception e) {
            //
        }

    }

    @Test
    public void testSpringJob() throws SchedulerException {
        Date startTime = DateBuilder.nextGivenSecondDate(null, 0);
        SimpleTrigger trigger1 = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(startTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(5))
                .build();
        sched.scheduleJob(job2, trigger1);
        try {
            // sleep for ten minutes for triggers to file....
            Thread.sleep(600L * 1000L);
        } catch (Exception e) {
            //
        }

    }

    @Resource(name = "springSechedulerFactory")
    private Scheduler sched;

    @Resource(name = "MethodInvokingJobDetail")
    private JobDetail job1;

    @Resource(name = "SimpleJobTrigger")
    private SimpleTrigger trigger;

    @Resource(name = "SpringJobDetail")
    private JobDetail job2;
}
