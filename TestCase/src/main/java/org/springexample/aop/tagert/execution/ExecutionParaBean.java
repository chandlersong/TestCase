package org.springexample.aop.tagert.execution;

import java.rmi.AlreadyBoundException;

public class ExecutionParaBean implements ExecutionBean {

    public Object testAop() {

        return "ExecutionParaBean-testAop()";
    }

    public Object testAop(int throwexception) {
        System.out.println("int");
        return "ExecutionParaBean-testAop(throwexception)";

    }

    public String getValue() {

        return null;
    }

    public String throwsException() throws AlreadyBoundException {

        return null;
    }

    public void testAop(int throwexception, String arg) {
        System.out.println("int,string");
    }

    public void testAop(int throwexception, double b) {

        System.out.println("int,double");
    }
}
