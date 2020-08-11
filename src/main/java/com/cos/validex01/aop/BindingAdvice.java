package com.cos.validex01.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//공통 관심사 : advice 교재 446p
@Component //모든 어노테이션은 Component의 자식임
@Aspect //AOP 등록 완료
public class BindingAdvice {
	
	//JoinPoint = 핵심로직, 즉 Proxy로 가져온다는 뜻
	//@Before, @After, @Around(핵심로직 앞뒤로)
	@Before("execution(* com.cos.validex01..*Controller.*(..)")//공부
	public Object validationHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		//Throwable 쓰로우의 최고 부모
		
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		
		System.out.println("type : "+type);
		System.out.println("method : "+method);
		
		return proceedingJoinPoint.proceed();
		}
}
