package org.springexample.aop.tagert;

public class AroundBean implements TargetBean {

    public Object testAop() {
        System.out.println("run method:test around after");
        return "AroundBean-testAop()";
    }

    public Object testAop(int throwexception) {

        if (throwexception == THROWEXCPTION) {
            throw new RuntimeException();
        }
        else if (throwexception == NOTTHROWEXCPTION) {
            this.testAop();
        }
        return "AroundBean-testAop(throwexception)";
    }

}
