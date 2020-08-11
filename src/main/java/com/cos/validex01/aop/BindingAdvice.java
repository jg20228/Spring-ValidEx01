package com.cos.validex01.aop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.cos.validex01.RespDto;
import com.cos.validex01.StatusCode;

//공통 관심사 : advice 교재 446p
@Component //모든 어노테이션은 Component의 자식임
@Aspect //AOP 등록 완료
public class BindingAdvice {
	
	//핵심로직에는 접근 못함
	@Before("execution(* com.cos.validex01.test.BindControllerTest.*(..))") 
	public void test1() {
		System.out.println("BindController에 오신것을 환영 합니다.");
	}
	
	@After("execution(* com.cos.validex01.test.BindControllerTest.*(..))") 
	public void test2() {
		System.out.println("BindController를 이용해주셔서 감사합니다.");
	}
	
	
	//JoinPoint = 핵심로직, 즉 Proxy로 가져온다는 뜻
	//@Before, @After, @Around(핵심로직 앞뒤로)
	@Around("execution(* com.cos.validex01..*Controller.*(..))")//검색의 키워드
	public Object validationHandler(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		//Around에 접근하는 이유는 ProceedingJoinPoint 이용하기 위함
		//ProceedingJoinPoint = 실행되는 메소드
		//Throwable 쓰로우의 최고 부모
		String type = proceedingJoinPoint.getSignature().getDeclaringTypeName();
		String method = proceedingJoinPoint.getSignature().getName();
		
		System.out.println("type : "+type);
		System.out.println("method : "+method);
		
		Object[] args = proceedingJoinPoint.getArgs(); //getArgs는 joinPoint의 parameter를 찾는다.
		
		for (Object arg : args) {
			if(arg instanceof BindingResult) {
				BindingResult bindingResult = (BindingResult) arg;
				
				//bindingResult에 에러가 났을때만
				if(bindingResult.hasErrors()) {
					//오류들의 메시지만 담을려면 Map이 필요함
					Map<String, String> errorMap = new HashMap<>();
					
					for (FieldError error : bindingResult.getFieldErrors()) {
						errorMap.put(error.getField(), error.getDefaultMessage());
					}
					//이 밑에는 함수로 만들어두면 되는데 일단 적어둠
					RespDto<?> respDto = RespDto.builder()
							.statusCode(StatusCode.FAIL)
							.msg(method+"요청에 실패하였습니다.")
							.data(errorMap)
							.build();
					
					//여기서 return하면 해당 메소드로 가지도 않음
					return new ResponseEntity<RespDto>(respDto,HttpStatus.BAD_REQUEST);
				}//End If
			}
		}
		
		return proceedingJoinPoint.proceed();
		}
}
