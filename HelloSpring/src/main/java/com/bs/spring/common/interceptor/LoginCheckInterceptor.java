package com.bs.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.bs.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor{
	
	//private Logger logger=LoggerFactory.getLogger(LoginCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session=request.getSession();
		Member m=(Member)session.getAttribute("loginMember");
		if(m==null) {
			//로그인을 하지 않음
			request.setAttribute("msg","로그인 후 이용 가능한 서비스입니다. :(");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp")
			.forward(request, response);
			log.info(request.getRequestURI()+" : "+request.getRemoteAddr());
			return false;
		}
		return true;
		
	}

	
	
	
}
