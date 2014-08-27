package org.springexample.aop.tagert;

public class AfterBean implements TargetBean {

    public Object testAop() {
        System.out.println("run method:test advice after");
        return "AfterBean-testAop()";
    }

    public Object testAop(int throwexception) {

        if (throwexception == THROWEXCPTION) {
            throw new RuntimeException();
        }
        else if (throwexception == NOTTHROWEXCPTION) {
            this.testAop();
        }

        return "AfterBean-testAop(throwexception)";

    }

}
