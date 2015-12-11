package org.commmstudy.quartz.spring;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleTrigger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springBasic.xml")
public class SimpleSpringExample extends AbstractJUnit4SpringContextTests {

    @Test
    public void testPublic() throws SchedulerException {
        sched.scheduleJob(job1, trigger);
    }

    @Resource(name = "springSechedulerFactory")
    private Scheduler sched;

    @Resource(name = "myJobDetail")
    private JobDetail job1;

    @Resource(name = "myJobTrigger")
    private SimpleTrigger trigger;
}
