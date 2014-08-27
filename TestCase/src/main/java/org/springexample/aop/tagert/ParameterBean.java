package org.springexample.aop.tagert;

public class ParameterBean implements TargetBean {

    public Object testAop() {
        System.out.println("run method:test advice after");
        return "ParameterBean-testAop()";
    }

    public Object testAop(int throwexception) {

        if (throwexception == THROWEXCPTION) {
            throw new RuntimeException();
        }
        else if (throwexception == NOTTHROWEXCPTION) {
            this.testAop();
        }
        return "ParameterBean-testAop(throwexception)";
    }

}
