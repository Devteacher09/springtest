package com.bs.spring.jpa.dao;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.bs.spring.jpa.common.MemberLevel;
import com.bs.spring.jpa.entity.Club;
import com.bs.spring.jpa.entity.Dev;
import com.bs.spring.jpa.entity.JpaMember;
import com.bs.spring.jpa.entity.JpaTest;
import com.bs.spring.jpa.entity.Locker;
import com.bs.spring.jpa.entity.Student;
import com.bs.spring.jpa.entity.Subject;
import com.bs.spring.jpa.entity.SubmitSubject;

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
		
		Club c=Club.builder()
				.name("코딩")
				.position("컴퓨터실")
				.build();
		
		Student s1=Student.builder()
				.studentName("박세현")
				.classNumber(2)
				.grade(2)
				.gender("남")
				.build();
		
		s1.setClub(c);
		
		s.setClub(c);
		s.setLocker(l);
		
		em.persist(c);
		em.persist(l);
		em.persist(s);
		em.persist(s1);
	}
	
	public Student selectStudent(EntityManager em, Long no) {
		return em.find(Student.class,no);
	}
	public Locker selectLocker(EntityManager em, Long no) {
		return em.find(Locker.class, no);
	}
	
	public Club selectClub(EntityManager em, Long no) {
		return em.find(Club.class, no);
	}
	
	
	public void insertSubject(EntityManager em) {
		Student s=Student.builder()
				.studentName("한호현")
				.classNumber(4)
				.grade(3)
				.gender("남")
				.build();
		Student s1=Student.builder()
				.studentName("이동명")
				.classNumber(2)
				.grade(2)
				.gender("남")
				.build();
		Subject sub=Subject.builder().subjectName("자바").teacherName("유병승").build();
		Subject sub1=Subject.builder().subjectName("c언어").teacherName("유병승").build();
		Subject sub2=Subject.builder().subjectName("파이썬").teacherName("유병승").build();
		Subject sub3=Subject.builder().subjectName("spring").teacherName("유병승").build();
		
		
//		s.setSubjects(List.of(sub,sub3,sub2));
//		s1.setSubjects(List.of(sub1,sub2,sub3));
		
		
		
		SubmitSubject ss=SubmitSubject.builder().subject(sub3).student(s)
				.submitDate(new Date(System.currentTimeMillis())).build();
		SubmitSubject ss1=SubmitSubject.builder().subject(sub3).student(s1)
				.submitDate(new Date(System.currentTimeMillis())).build();
		
		em.persist(ss1);
		em.persist(ss);
		
		Student s3=em.find(Student.class, 101L);
		Subject sub4=em.find(Subject.class, 21L);
		
		SubmitSubject ss2=SubmitSubject.builder().subject(sub4).student(s3)
				.submitDate(new Date(System.currentTimeMillis())).build();
		
		em.persist(ss2);
		
		
		
		
		
		em.persist(s);
		em.persist(s1);
		em.persist(sub);
		em.persist(sub1);
		em.persist(sub2);
		em.persist(sub3);
		
	}
	
	public Subject selectSubJect(EntityManager em, Long no) {
		return em.find(Subject.class,no);
	}
	
}
