package org.springexample.aop.tagert.execution;

public class ReturnValueBean implements ExecutionBean {

    public Object testAop() {
        System.out.println("testAOP");
        return "ReturnValueBean-testAop()";

    }

    public Object testAop(int throwexception) {
        System.out.println("testAOP");
        return "ReturnValueBean-testAop(throwexception)";
    }

    public String getValue() {

        return "test return value";
    }

    public String throwsException() {
        return null;
    }

    public void testAop(int throwexception, String arg) {

    }

}
