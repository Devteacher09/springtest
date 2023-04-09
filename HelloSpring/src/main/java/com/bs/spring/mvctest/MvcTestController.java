package com.bs.spring.mvctest;

import java.io.IOException;
import java.io.Writer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MvcTestController {
	
	private MvcTestService service;
	
	public MvcTestController(MvcTestService service) {
		this.service=service;
	}
	
	@RequestMapping("/mvctest")
	public String mvctest() {
		System.out.println(service);
		service.mvcTest();
		
		return "test";
	}
		
}
