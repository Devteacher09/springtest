package com.bs.spring;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.common.AdminCheckException;

@Controller
public class BasicController {
	//log4j로 출력하기 위해서는 
	//log4j가 제공하는 객체를 이용
	//Logger클래스가 제공하는 메소드를 이용
	// debug(), info(), warn(), error()
	private Logger logger=LoggerFactory.getLogger(BasicController.class);
	
	
	@RequestMapping("/")
	public String mainview(HttpServletResponse response,HttpSession session) {
		
		//쿠키값저장
		Cookie c=new Cookie("testCookie", "cookiedata");
		c.setMaxAge(60*60*24);
		response.addCookie(c);
		
		//session객체에 데이터저장
		session.setAttribute("sessionId", "admin");
		
		
		//Logger클래스를 이용해서 출력하기
		logger.debug("debug레벨 출력하기");
		logger.info("infor레벨 출력하기");
		logger.warn("warn레벨 출력하기");
		logger.error("error레벨 출력하기");
		//Logger를 이용해서 메세지출력시 String을 인수로 받는다
		//객체를 출력할때는 매개변수가 두개인 메소드를 이용해야한다. 
		//첫번째 매개변수에는 문자열로 {}
		//두번째는 대입될 값을 선언
		logger.info("세션값음 {}",session);
		

//		if(session!=null) {
//			throw new IllegalArgumentException();
//		}
		
		return "index";
	}
	
	@RequestMapping("/error.do")
	public void error() {
		throw new AdminCheckException("로그인 실패");
	}
	
	@RequestMapping("/successLogin.do")
	public String successLogin() {
		logger.debug("로그인 성공");
		return "redirect:/";
	}
	
	
	
	
	
}
