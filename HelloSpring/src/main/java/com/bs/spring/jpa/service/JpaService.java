package com.bs.spring.jpa.service;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.jpa.dao.JpaDao;
import com.bs.spring.jpa.entity.Club;
import com.bs.spring.jpa.entity.Dev;
import com.bs.spring.jpa.entity.JpaMember;
import com.bs.spring.jpa.entity.JpaTest;
import com.bs.spring.jpa.entity.Locker;
import com.bs.spring.jpa.entity.Student;
import com.bs.spring.jpa.entity.Subject;

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
	public JpaMember insertjpa() {
		return dao.insertjpa(manager);
	}
	
	public JpaMember searchMemberByNo(int no) {
		return dao.seachMemberByNo(manager,no);
	}
	
	public List<JpaMember> searchMemberAll(){
		return dao.searchMemberAll(manager);	
	}
	
	public Dev searchDev(int no) {
		return dao.searchDev(manager,no);
	}
	
	public List<JpaMember> searchMemberByAge(int age){
		return dao.searchMemberBy(manager, age);
	}
	
	public void insertMember(JpaMember m) {
		//jpa에서 DML(INSERT, UPDATE, DELETE)구문을 실행할때
		//트렌젝션으로 관리해줘야함. COMMIT, ROLLBACK -> 영속성컨텍스트에 저장된 내용을
		// DB에 반영(flush() -> commit()메소드를 호출하면 실행됨)
		EntityTransaction transaction=manager.getTransaction();
		//트렌젝션시작하기. -> begin()메소드를 이용
		transaction.begin();
		
		dao.insertMember(manager,m);
		
		transaction.commit();//flush()메소드 호출
	}
	
	
	
	public void updateMember(Map param) {
		EntityTransaction et=manager.getTransaction();
		et.begin();
		JpaMember m=dao.seachMemberByNo(manager, Integer.parseInt((String)param.get("memberNo")));
		dao.updateMember(manager,m,param);
		et.commit();	
	}
	
	public void deleteMember(int memberNo) {
		EntityTransaction et=manager.getTransaction();
		et.begin();
		JpaMember deleteMember=dao.seachMemberByNo(manager, memberNo);
		dao.deleteMember(manager,deleteMember);
		et.commit();
	}
	
	public void insertOneToOneData() {
		EntityTransaction et=manager.getTransaction();
		et.begin();
			dao.insertOneToOneData(manager);
		et.commit();
	}
	
	public Student selectStudent(Long no) {
		return dao.selectStudent(manager,no);
	}
	public Locker selectLocker(Long no) {
		return dao.selectLocker(manager,no);
	}
	public Club selectClub(Long no) {
		return dao.selectClub(manager,no);
	}

	public void insertSubject() {
		EntityTransaction et=manager.getTransaction();
		et.begin();
			dao.insertSubject(manager);
		et.commit();
	}
	public Subject selectSubJect(Long no) {
		return dao.selectSubJect(manager, no);
	}
	
	
}
