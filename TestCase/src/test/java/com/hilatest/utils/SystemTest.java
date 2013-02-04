
package com.hilatest.utils;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import org.junit.Test;

public class SystemTest {

    @Test
    public void testSystemPropertiy() {
        System.out.println("Print env");
        Iterator<Entry<String, String>> ei = System.getenv().entrySet().iterator();

        while (ei.hasNext()) {
            Entry<String, String> e = ei.next();

            System.out.println("key:" + e.getKey() + ",value:" + e.getValue());
        }

        Properties ps = System.getProperties();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out
                .println("====================================Print Properties============================================");
        Iterator<Entry<Object, Object>> i = ps.entrySet().iterator();

        while (i.hasNext()) {
            Entry<Object, Object> e = i.next();

            System.out.println("key:" + e.getKey() + ",value:" + e.getValue());
        }
    }

    @Test
    public void testMonitorJVM() {
        RuntimeMXBean RuntimemxBean = ManagementFactory.getRuntimeMXBean();
        List<String> arguments = RuntimemxBean.getInputArguments();

        Iterator<String> iter = arguments.iterator();

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }
}
