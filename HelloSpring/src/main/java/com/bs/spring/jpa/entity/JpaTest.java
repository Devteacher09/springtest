package com.bs.spring.jpa.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name="jpa_test", 
	uniqueConstraints = {@UniqueConstraint(name="uq_namenumber",
							columnNames = {"testName","testNumber"})})
@SequenceGenerator(name="seq_jpatest", sequenceName = "seq_jpatest", 
initialValue = 1, allocationSize = 1 )
/*
	name : java에서 사용하는 명칭
	squenceName : 오라클에서 사용할 명칭
	initialValue : 시작값 * 기본 : 1
	allocationSize : cache * 기본 : 50, 1
*/
public class JpaTest {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator ="seq_jpatest")
	private long testNo;
	private String testName;
	private int testNumber;
	private Date testDate;
}




