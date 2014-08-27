package org.springexample.aop.tagert;

public class BeforeBean implements TargetBean {

    public Object testAop() {
        System.out.println("run method:test advice before");
        return "BeforeBean-testAop()";
    }

    public Object testAop(int throwexception) {
        return "BeforeBean-testAop(throwexception)";
    }
}
