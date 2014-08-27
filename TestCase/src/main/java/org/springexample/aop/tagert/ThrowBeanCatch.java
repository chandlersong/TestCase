package org.springexample.aop.tagert;

public class ThrowBeanCatch implements TargetBean {

    public Object testAop() {
        System.out.println("run method:test throw after without throw exception");
        return "ThrowBeanCatch-testAop()";
    }

    public Object testAop(int throwexception) {

        if (throwexception == THROWEXCPTION) {
            System.out.println("throw exception in mehtod testAOP");
            throw new RuntimeException();
        }
        else if (throwexception == NOTTHROWEXCPTION) {
            this.testAop();
        }
        return "ThrowBeanCatch-testAop()";

    }
}
