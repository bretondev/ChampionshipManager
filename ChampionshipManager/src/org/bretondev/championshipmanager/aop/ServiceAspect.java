package org.bretondev.championshipmanager.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

	@Before(value="execution(* org.bretondev.championshipmanager.service.*Service.*(..))")
	public void log(JoinPoint jp) {
		System.out.println("Entering AOP advice");
		System.out.println(jp.getTarget().getClass().getSimpleName());
		System.out.println(jp.getSignature().getName());
		
		Object[] args = jp.getArgs();
		for (int i = 0; i < args.length; i++) {
			System.out.println("args[" + i + "]-->" + args[i]);
		}
	}
	
	
	
	
}
