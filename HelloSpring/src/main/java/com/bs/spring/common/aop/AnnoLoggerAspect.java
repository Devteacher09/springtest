package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

//어노테이션방식으로 aop적용하기
@Component
@Aspect
@Slf4j
public class AnnoLoggerAspect {

	@Pointcut("execution(* com.bs.spring.member..*(..))")
	public void memberLogger() {};
	
	@Before("memberLogger()")
	public void loggerBefore(JoinPoint jp) {
		Signature sig=jp.getSignature();
		log.debug(sig.getName()+"메소드 실행전 ");
		log.debug(sig.getDeclaringTypeName()+"클래스");
		//메소드 실행시 전달되는 파라미터값 확인하기
		Object[] params=jp.getArgs();//전달되는 파라미터를 반환해주는 메소드
		if(params!=null) {
			for(Object o : params) {
				log.debug("파라미터 : {}",o);
			}
		}
		log.debug("========================");
	}
	
	@After("memberLogger()")
	public void loggerAfter(JoinPoint jp) {
		Signature sig=jp.getSignature();
		log.debug(sig.getName()+"() 메소드 실행 후");
		log.debug(sig.getDeclaringTypeName()+" 클래스");
		log.debug("=================================");
	}
	
	
	//실행전후에 로직 실행시키기
	//@Around
	@Pointcut("execution(* com.bs.spring.demo..*(..))")
	public void demoLogger() {}
	
	@Around("demoLogger()")//전, 후를 동시 선언!
	public Object demoLoggerAround(ProceedingJoinPoint pjp) throws Throwable{
		//전, 후 구분하기 -> ProceedingJoinPoint.proceed()메소드를 기준으로 전,후를 나눔
		//proceed()호출되기 전에 : before로직, proceed()호출된 후 : after로직
		//proceed()메소드는 Object를 반환.
		log.debug("==== around 실행전 =====");
		Signature sig=pjp.getSignature();
		log.debug(sig.getName()+"메소드 실행");
		log.debug("=======================");
		
		Object obj=pjp.proceed();//
		
		log.debug("===== around 실행후 ====");
		log.debug(sig.getName()+"메소드 실행");
		log.debug("=======================");
		return obj;
	}
	
	@Around("execution(* com.bs.spring..dao..insert*(..))")
	public Object insertTest(ProceedingJoinPoint pjp) throws Throwable{
		StopWatch stopwatch=new StopWatch();
		stopwatch.start();		
		Object obj=pjp.proceed();
		stopwatch.stop();
		log.debug("실행시간 : "+stopwatch.getTotalTimeMillis()+"ms");		
		return obj;
	}
	
	
}







