package org.springexample.aop.tagert;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springexample.aop.tagert.annotation.AnationWithPara;

public class OtherAspect {

    public void withInClassAnnotation() {
        System.out.println("Aspect:withIn Class Annotation");
    }

    public void aroundAndWithPara(ProceedingJoinPoint pjp) throws Throwable {

        Object[] paras = pjp.getArgs();

        for (Object para : paras) {
            System.out.println(para);
        }

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        System.out.println("Aspect:aroundAndWithPara");
        Method method = signature.getMethod();
        System.out.println("method:" + method.getName());
        Annotation[] annotations = method.getAnnotations();
        for (Annotation a : annotations) {
            System.out.println(a.getClass());
        }
        AnationWithPara annotation = method.getAnnotation(AnationWithPara.class);
        System.out.println("name" + annotation.name());

        pjp.proceed();
    }
}
