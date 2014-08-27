package org.springexample.aop.tagert;

public class ThrowBean implements TargetBean {

    public Object testAop() {
        System.out.println("run method:test throw after without throw exception");
        return "ThrowBean-testAop()";
    }

    public Object testAop(int throwexception) {

        if (throwexception == THROWEXCPTION) {
            System.out.println("throw exception in mehtod testAOP");
            throw new RuntimeException();
        }
        else if (throwexception == NOTTHROWEXCPTION) {
            this.testAop();
        }
        return "ThrowBean-testAop(throwexception)";
    }
}
