package org.springexample.aop.tagert.execution;

public class ExecutionThrowBean implements ExecutionBean {

    public Object testAop() {
        return "ExecutionThrowBean-testAop()";
    }

    public Object testAop(int throwexception) {
        return "ExecutionThrowBean-testAop(throwexception)";
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
