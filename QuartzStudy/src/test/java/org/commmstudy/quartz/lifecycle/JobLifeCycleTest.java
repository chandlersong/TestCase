package org.commmstudy.quartz.lifecycle;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:springBasic.xml")
public class JobLifeCycleTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void testJobDetailsLifeCycle() throws SchedulerException {
        sched.scheduleJob(job1, createOneTimeTrigger());

        try {
            int i = 10;
            while (i > 0) {
                JobDetail jobDetail = sched.getJobDetail(job1.getKey());
                System.out.println(jobDetail);
                Thread.sleep(1 * 1000L);
                i--;
            }
            sched.deleteJob(job1.getKey());
            System.out.println("delete job");
            JobDetail jobDetail = sched.getJobDetail(job1.getKey());
            System.out.println(jobDetail);
        } catch (Exception e) {
            //
        }
    }

    @Resource(name = "springSechedulerFactory")
    private Scheduler sched;

    @Resource(name = "MethodInvokingJobDetail")
    private JobDetail job1;

    public Trigger createOneTimeTrigger() {
        return TriggerBuilder.newTrigger().withIdentity(UUID.randomUUID().toString(), "import").startNow().build();
    }
}
