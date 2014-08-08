package sourcecode.springexample.container;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import sourcecode.springexample.test.TestObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:TestExample.xml")
public class TestExampleTest extends AbstractJUnit4SpringContextTests {

    @Resource(name = "TestExample")
    TestObject object;

    @Test
    public void test1() {
        object.run();
    }

    @Test
    public void test2() {
        object.run();
    }

}
