package org.springexample.aop.tagert.execution;

import org.springexample.aop.tagert.TargetBean;

public class ExecutionScopeBean implements TargetBean {

    public Object testAop() {
        System.out.println("run method:test Execution Scope");
        return "ExecutionScopeBean-testAop(throwexception)";
    }

    public Object testAop(int throwexception) {
        testscope(throwexception);
        return "ExecutionScopeBean-testAop(throwexception)";
    }

    public void testscope(int throwexception) {

        if (throwexception == THROWEXCPTION) {
            throw new RuntimeException();
        }
        else if (throwexception == NOTTHROWEXCPTION) {
            testAop();
        }
    }
}
