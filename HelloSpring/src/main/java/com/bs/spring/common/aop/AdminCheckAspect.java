package com.bs.spring.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring.common.AdminCheckException;
import com.bs.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class AdminCheckAspect {

	//@Before("execution(* com.bs.spring.memo..select*(..))")
	public void admincheck(JoinPoint jp) {
		//1. 로그인정보를 가져오기
		//RequestContextHolder클래스를 이용해서 request정보를 가져올 수 있음
		//RequestContextHolder.currentRequestAttribute()메소드이용
		HttpSession session=(HttpSession)RequestContextHolder
				.currentRequestAttributes()
				.resolveReference(RequestAttributes.REFERENCE_SESSION);
		Member loginMember=(Member)session.getAttribute("loginMember");
		if(loginMember==null || !loginMember.getUserId().equals("admin")) {
			//서비스를 중단! 
			//exception이용하여 중단시키기
			throw new AdminCheckException("관리자만 접근할 수 있습니다! :(");
		}
	}
	
	
}







