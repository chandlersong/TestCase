package me.study.java.spring.autoconfigure;

import me.study.java.spring.autoconfigure.beans.BeanA;
import me.study.java.spring.autoconfigure.beans.BeanB;
import me.study.spirng.tools.ServerRunner;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;

public class AutoConfigurationDemo {

    @Test
    public void enableCase() {
        ConfigurableApplicationContext configurableApplicationContext = ServerRunner.createAndRunServer(AutoConfigurationTestApplication.class, "enabled.yml").getApplication().get();
        Assert.assertNotNull(configurableApplicationContext.getBean(BeanA.class));
        Assert.assertNotNull(configurableApplicationContext.getBean(BeanB.class));

    }


    @Test()
    public void disableCase() {
        ConfigurableApplicationContext configurableApplicationContext = ServerRunner.createAndRunServer(AutoConfigurationTestApplication.class, "disabled.yml").getApplication().get();

        checkMissingBean(configurableApplicationContext, BeanA.class);
        checkMissingBean(configurableApplicationContext, BeanB.class);
    }

    private void checkMissingBean(ConfigurableApplicationContext configurableApplicationContext, Class<?> requiredType) {
        boolean missingBean = false;
        try {
            configurableApplicationContext.getBean(requiredType);
        } catch (BeansException e) {
            missingBean = true;
        }
        Assert.assertTrue(missingBean);
    }
}
