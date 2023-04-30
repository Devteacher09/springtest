package com.bs.spring.jpa.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.jpa.common.MemberLevel;
import com.bs.spring.jpa.entity.Club;
import com.bs.spring.jpa.entity.Dev;
import com.bs.spring.jpa.entity.JpaMember;
import com.bs.spring.jpa.entity.JpaTest;
import com.bs.spring.jpa.entity.Locker;
import com.bs.spring.jpa.entity.Student;
import com.bs.spring.jpa.entity.Subject;
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
	
	
	@RequestMapping("/student/{studentNo}")
	@ResponseBody
	public Student selectStudent(@PathVariable(name="studentNo") Long no) {
		return service.selectStudent(no);
	}
	
	@RequestMapping("/locker/{lockerNo}")
	@ResponseBody
	public Locker selectLocker(@PathVariable(name="lockerNo") Long no) {
		return service.selectLocker(no);
	}
	
	@RequestMapping("/club/{clubNo}")
	@ResponseBody
	public Club selectClub(@PathVariable(name="clubNo") Long no) {
		Club c=service.selectClub(no);
		c.setStudents(null);
		return c;
	}
	
	@RequestMapping("/subject")
	public String insertSubject() {
		service.insertSubject();
		return "redirect:/";
	}
	
	@RequestMapping("/subject/{subjectNo}")
	@ResponseBody
	public Subject selectSubJect(@PathVariable Long subjectNo) {
		return service.selectSubJect(subjectNo);
	}
	
}




