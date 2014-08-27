package org.springexample.aop.tagert.execution;

public class NoPackageName implements ExecutionBean {

    public Object testAop() {
        return "NoPackageName-testAop()";
    }

    public Object testAop(int throwexception) {
        return "NoPackageName-testAop(throwexception)";
    }

    public String getValue() {

        return null;
    }

    public String throwsException() {
        return null;
    }

    public void testAop(int throwexception, String arg) {

    }

}
