package com.bs.spring.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name="seq_studentNo", sequenceName = "seq_studentNo", 
	initialValue = 1,allocationSize = 1)
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator = "seq_studentNo")
	private Long studentNo;
	private String studentName;
	private Integer grade;
	private Integer classNumber;
	private String gender;
	//다른 클래스와의 연관관계를 표시하는 jpa어노테이션!
	//@OneToOne : 1:1로 클래스가 연결되는것 -> java has a 관계로 클래스를 필드로 선언
	//@OneToMany : 1:다로 클래스가 연결되는것 -> java has a관계로 List타입으로 필드선언
	//@ManyToOne : 다:1로 클래스가 연결되는것 -> java has a 클래스를 필드로 선언
	//@ManyToMany : 다:다 클래스가 연결되는것 -> 중간테이블 생성하는 구문을 작성
	@OneToOne
	@JoinColumn(name="locker_no")
	private Locker locker;
	
	
	
	
}
