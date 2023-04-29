package com.bs.spring.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.jpa.entity.JpaTest;
import com.bs.spring.jpa.service.JpaService;

@Controller
@RequestMapping("/jpa")
public class JpaController {

	private JpaService service;
	
	public JpaController(JpaService service) {
		this.service=service;
	}
	
	
	@RequestMapping("/basicjpa")
	public @ResponseBody JpaTest testBasicjpa() {
		
		return service.basicTest();
	}
	
	
	
}
