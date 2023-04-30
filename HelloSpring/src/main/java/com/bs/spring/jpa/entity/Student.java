package com.bs.spring.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
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
	@JoinColumn(name="locker_no")//1:1 단방향관계를 설정
	private Locker locker;
	
	
	//학생과 클럽과의 관계는 
	// 다 대 일
	@ManyToOne
	@JoinColumn(name="clubNo")
	private Club club;
	
	//학생과 과목과의 관계를 설정하기
	//다대다 관계
//	@ManyToMany
//	@JoinTable(name="student_subject",
//		joinColumns = @JoinColumn(name="studentNo"),
//		inverseJoinColumns = @JoinColumn(name="subjectNo")
//	)
//	List<Subject> subjects;
	@OneToMany(mappedBy = "student")
	private List<SubmitSubject> submitSubject;
	
}
