package com.bs.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.member.model.service.MemberService;
import com.bs.spring.member.model.vo.Member;

@Controller
@SessionAttributes({"loginMember"})
@RequestMapping("/member")
public class MemberController {
	
	private MemberService service;
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	public void setPasswordEncoder(BCryptPasswordEncoder encoder) {
		this.passwordEncoder=encoder;
	}
	
	@Autowired
	public void setService(MemberService service) {
		this.service=service;
	}
	
	@RequestMapping("/login")
	public String login(String userId, String password//,HttpSession session
			, Model model ) {
		
		Member m=service.selectMemberById(userId);
		System.out.println(m);
		
		//if(m!=null&&m.getPassword().equals(password)) {
		if(m!=null&&passwordEncoder.matches(password, m.getPassword())) {
			//로그인 성공
			//session.setAttribute("loginMember", m);
			model.addAttribute("loginMember",m);
		}else {
			//로그인 실패
			model.addAttribute("msg","로그인실패 다시 시도하세요 :(");
			model.addAttribute("loc","/");
			return "common/msg";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout.do")
	public String logout(//HttpSession session) {
			SessionStatus session) {
		//if(session!=null) session.invalidate();
		//로그아웃, @SessionAttributes로 선언한 내용에 대해 해제할때 사용
		if(!session.isComplete()) session.setComplete();
		
		return "redirect:/";
	}
	
	@RequestMapping("/enrollMember.do")
	public String enrollMemberView() {
		return "member/enrollMember";
	}
	
	@RequestMapping("/enrollMemberEnd.do")
	public String enrollMemberEnd(Member member,Model m) {
		
		//매개변수로 넘어온 password값을 암호화처리하자
		String password=member.getPassword();
		String encPass=passwordEncoder.encode(password);
		System.out.println(encPass);
		member.setPassword(encPass);
		
		int result=service.insertMember(member);
		
		if(result>0) {
			m.addAttribute("msg","회원가입성공! 축하드립니다!");
			m.addAttribute("loc","/");
		}
		else {
			m.addAttribute("msg","회원가입실패 :(");
			m.addAttribute("loc","/member/memberEnroll.do");
		}
		
		return "common/msg";
	}
	
	
	
}






