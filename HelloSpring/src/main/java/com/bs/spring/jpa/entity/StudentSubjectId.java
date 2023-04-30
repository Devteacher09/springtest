package com.bs.spring.jpa.entity;

import java.io.Serializable;

import lombok.Data;

//조건
//복합키를 식별하게 해주는 클래스
//1. 기본생성자
//2. 클래스가 public로 선언되어야한다.
//3. Serilizable인터페이스를 구현
//4. equals, hashcode메소드가 오버라이딩 되어있어야한다.
@Data
public class StudentSubjectId implements Serializable{

	private static final long serialVersionUID = -169986460573618609L;

	//복합키로 사용하는 내용을 필드로 선언함.
	private Long student;
	private Long subject;
}






