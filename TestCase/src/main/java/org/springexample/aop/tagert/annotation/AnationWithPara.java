package org.springexample.aop.tagert.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnationWithPara {

    String name() default "";
}
