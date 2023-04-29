package com.bs.spring.jpa.dao;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.entity.JpaTest;

@Repository
public class JpaDao {

	public JpaTest basicTest(EntityManager em) {
		//jpa가 제공하는 EntityManager를 이용해서 DB에 있는 데이터 조회하기
		//pk값을 기준으로 한개의 row를 조회할 수 있다.
		//EntityManager는 조회하는 메소드를 제공한다.
		// find()
		return em.find(JpaTest.class, 1L);
	}
	
	
}
