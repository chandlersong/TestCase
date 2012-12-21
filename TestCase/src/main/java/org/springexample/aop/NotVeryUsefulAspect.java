package org.springexample.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class NotVeryUsefulAspect {

	@Pointcut("execution(* transfer(..))")// the pointcut expressi
    public void anyOldTransfer() {}// the pointcut signature
	
	@Pointcut("execution(public * *(..))")
	public void anyPublicOperation() {}
	@Pointcut("within(com.xyz.someapp.trading..*)")
	public void inTrading() {}
	
	@Pointcut("anyPublicOperation() && inTrading()")
	public void tradingOperation() {}
}
