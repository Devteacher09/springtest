package com.bs.spring.jpa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.jpa.common.MemberLevel;
import com.bs.spring.jpa.entity.Dev;
import com.bs.spring.jpa.entity.JpaMember;
import com.bs.spring.jpa.entity.JpaTest;
import com.bs.spring.jpa.service.JpaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/jpa")
@Slf4j
public class JpaController {

	private JpaService service;
	
	public JpaController(JpaService service) {
		this.service=service;
	}
	
	
	@RequestMapping("/basicjpa")
	public @ResponseBody JpaTest testBasicjpa() {
		
		return service.basicTest();
	}
	
	@RequestMapping("/inesrtjpa")
	public @ResponseBody JpaMember insertjpa() {
		return service.insertjpa();
	}
	
	
	
	@RequestMapping("/member")
	public @ResponseBody JpaMember searchMember(int no) {
		return service.searchMemberByNo(no);
	}
	
	
	@RequestMapping("/members")
	public @ResponseBody List<JpaMember> searchMemberAll(){
		return service.searchMemberAll();
	}
	
	@RequestMapping("/dev")
	public @ResponseBody Dev searchDev(int no) {
		return service.searchDev(no);
	}
	
	@RequestMapping("/searchage")
	public @ResponseBody List<JpaMember> searchAge(int age){
		return service.searchMemberByAge(age);
	}
	
	@RequestMapping("/inesrtmember")
	public @ResponseBody String insertMember(JpaMember member) {
		log.debug("{}",member);
		member.setMemberLevel(MemberLevel.BRONZE);
		service.insertMember(member);
		
		return "";
	}
	
	@RequestMapping("/updatemember")
	public @ResponseBody String updateMember(@RequestParam Map param) {
		log.debug("{}",param);
		service.updateMember(param);
		return "";
	}
	
	
	@RequestMapping("/deletemember")
	public @ResponseBody String deleteMember(int memberNo) {
		service.deleteMember(memberNo);
		return "";
	}
	
	@RequestMapping("/onetoone")
	public String oneToOneTest() {
		
		service.insertOneToOneData();
		
		return "redirect:/";
	}
	
}




