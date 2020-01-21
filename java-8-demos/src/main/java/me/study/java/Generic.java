package me.study.java;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Generic<T> {


    public void getClassType(Class<T[]> clz) {
        Class<?>[] classes = clz.getClasses();

        for (Class<?> type : classes) {
            log.info("type is {}", type);
        }

        log.info("component type:{}", clz.getComponentType());
    }
}
