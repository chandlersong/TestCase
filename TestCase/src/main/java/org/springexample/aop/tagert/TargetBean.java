package org.springexample.aop.tagert;

public interface TargetBean {

    public final int THROWEXCPTION = 1;
    public final int NOTTHROWEXCPTION = 2;

    public abstract Object testAop();

    public abstract Object testAop(int throwexception);
}
