package com.bs.spring.mvctest;

import org.springframework.stereotype.Service;

@Service
public class MvcTestService {
	
	private MvcTestDao dao;
	
	public MvcTestService(MvcTestDao dao) {
		this.dao=dao;
	}
	
	public void mvcTest() {
		System.out.println("service실행");
		dao.mvcTest();
	}
	
	
}
