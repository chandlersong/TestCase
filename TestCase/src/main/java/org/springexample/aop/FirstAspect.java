package org.springexample.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springexample.BasicBean;

public class FirstAspect extends BasicBean {

    public void testbefore(JoinPoint jp) {
        System.out.println("aop before execute");
        System.out.println("get--signature--Declaring Type Name:" + jp.getSignature().getDeclaringTypeName());
        System.out.println("get--signature--Declaring Type:" + jp.getSignature().getDeclaringType());
        System.out.println("get--signature--Class:" + jp.getSignature().getClass());
        System.out.println("get this:" + jp.getTarget());
        System.out.println("get target:" + jp.getThis().getClass());

        System.out.println("get--signature class:" + jp.getSignature().getClass());

        if (jp.getSignature() instanceof MethodSignature) {
            MethodSignature signature = (MethodSignature) jp.getSignature();
            Method method = signature.getMethod();
            System.out.println("method:" + method.toGenericString());

        }
        System.out.println("before advice");
        this.Success();
    }

    public void testafterreturning(Object returnVal) {
        System.out.println("after returning advice");
        System.out.println("return value:" + returnVal);
        this.Success();
    }

    public void testthrow() {
        System.out.println("throw advice");
        this.Success();
    }

    public void testthrowcatch(RuntimeException runtimeexception) {
        System.out.println("throw advice, will catch the exception");

        System.out.println("catch the exception:" + runtimeexception.getMessage());

        this.Success();
    }

    public void testafter(JoinPoint jp, Object returnVal) {
        System.out.println("aop before execute");
        System.out.println("get--signature--Declaring Type Name:" + jp.getSignature().getDeclaringTypeName());
        System.out.println("get--signature--Declaring Type:" + jp.getSignature().getDeclaringType());
        System.out.println("get--signature--Class:" + jp.getSignature().getClass());
        System.out.println("get this:" + jp.getTarget());
        System.out.println("get target:" + jp.getThis().getClass());

        System.out.println("get--signature class:" + jp.getSignature().getClass());
        System.out.println("after advice");
        System.out.println("return value:" + returnVal);
        this.Success();
    }

    public Object testAround(ProceedingJoinPoint pjp) {

        try {
            System.out.println("test around advice");
            Object result = pjp.proceed();
            System.out.println("result:" + result);
            System.out.println("finish around advice");
            return result;
        } catch (Throwable e) {
            System.out.println("catch exception");
            return null;
        }
    }

    public void testParemter(ProceedingJoinPoint pjp, int throwexception) {

        System.out.println(throwexception);
        try {
            System.out.println("test parameter advice");
            pjp.proceed();
            System.out.println("parameter around advice");
        } catch (Throwable e) {
            System.out.println("catch exception");
        }
    }
}
