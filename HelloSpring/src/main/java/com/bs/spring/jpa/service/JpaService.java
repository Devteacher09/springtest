package com.bs.spring.jpa.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.jpa.dao.JpaDao;
import com.bs.spring.jpa.entity.JpaTest;

@Service
public class JpaService {

	private JpaDao dao;
	private EntityManager manager;
	
	@Autowired
	public JpaService(JpaDao dao,EntityManager manager) {
		this.dao=dao;
		this.manager=manager;
	}
	
	public JpaTest basicTest() {
		return dao.basicTest(manager);
	}
	
}
