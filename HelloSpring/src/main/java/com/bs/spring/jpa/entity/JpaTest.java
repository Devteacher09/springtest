package com.bs.spring.jpa.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class JpaTest {
	@Id
	private long testNo;
	private String testName;
	private int testNumber;
	private Date testDate;
}




