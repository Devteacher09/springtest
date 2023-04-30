package com.bs.spring.jpa.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.common.MemberLevel;
import com.bs.spring.jpa.entity.Dev;
import com.bs.spring.jpa.entity.JpaMember;
import com.bs.spring.jpa.entity.JpaTest;
import com.bs.spring.jpa.entity.Locker;
import com.bs.spring.jpa.entity.Student;

@Repository
public class JpaDao {

	public JpaTest basicTest(EntityManager em) {
		//jpa가 제공하는 EntityManager를 이용해서 DB에 있는 데이터 조회하기
		//pk값을 기준으로 한개의 row를 조회할 수 있다.
		//EntityManager는 조회하는 메소드를 제공한다.
		// find()
		return em.find(JpaTest.class, 1L);
	}
	
	public JpaMember insertjpa(EntityManager em) {
		EntityTransaction et=em.getTransaction();
		et.begin();
		JpaMember jm=null;
		for(int i=0;i<10;i++) {
			jm=JpaMember.builder()
					.memberId("test"+i)
					.memberPwd("test"+i)
					.age(10+i)
					.height(180+i)
					.memberLevel(MemberLevel.PRETINUM)
					.enrollDate(new Date(System.currentTimeMillis()))
					.build();
			em.persist(jm);
		}
		et.commit();
		return jm;
	}
	
	public JpaMember seachMemberByNo(EntityManager em, int no) {
		//entity DB에서 조회할때는 
		//EntityManager가 제공하는 메소드를 이용해서 조회
		//단 EntityManager가 기본으로 제공하는 메소드는
		//pk값을 기준으로 한개의 row만 조회하는 기능이다.
		JpaMember m=em.find(JpaMember.class, Long.valueOf(no));
		return m;
	}
	
	public List<JpaMember> searchMemberAll(EntityManager em){
		//다수의 row를 조회하기
		//EntityManager에서 다수row를 조회하는 구문을 제공하지않음
		//JPQL구문을 이용해서 직접 SQL문을 작성하고 그결과를 받아와야함
		//JPQL은 java방식으로 sql문을 작성하는 것
		// sql문과 비슷하지만 다름
		// EntityManager가 제공하는 createQuery("JPQL")함수를 이용해서 작성을 함
		 Query sql=em.createQuery("select m from JpaMember m",JpaMember.class);
		 //쿼리문을 실행하고 결과를 받아오기
		 //Query클래스에서 제공하는 getResultList()메소드를 이용
		 return sql.getResultList();
	}
	
	
	public Dev searchDev(EntityManager em,int no) {
		return em.find(Dev.class, no);
	}
	
	public List<JpaMember> searchMemberBy(EntityManager em, int age){
		Query sql=em.createQuery("select m from JpaMember m where m.age>=:param");
		sql.setParameter("param", age);
		return sql.getResultList();
	}
	
	public void insertMember(EntityManager em, JpaMember m) {
		em.persist(m);		
	}
	public void updateMember(EntityManager em, JpaMember m, Map param) {
		//jpa에서 수정할때는 영속성컨텍스트에 등록된 객체에 필드값을 수정하면 
		//update문이 생성되고 commit(), flush()를 실행하면 update문이 실행된다.
		m.setAge(Integer.parseInt((String)param.get("age")));
		m.setHeight(Double.parseDouble((String)param.get("height")));
		m.setInfo((String)param.get("info"));
		//수정끝!
	}
	
	
	public void deleteMember(EntityManager em, JpaMember deleteMember) {
		//영속성컨텐스트에 있는 객체 삭제하기
		em.remove(deleteMember);
	}
	
	public void insertOneToOneData(EntityManager em) {
		
		//연관관계에 있는 데이터 저장하기
		Student s=Student.builder()
				.studentName("유병승")
				.classNumber(3)
				.grade(1)
				.gender("남")
				.build();
		Locker l=Locker.builder()
				.color("빨강")
				.floor(1)
				.build();
		s.setLocker(l);
		em.persist(l);
		em.persist(s);
		
		
	}
	
	
	
	
}
