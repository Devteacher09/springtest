package com.bs.spring.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@SequenceGenerator(name = "seq_lockerNo", sequenceName = "seq_lockerNo", 
	allocationSize = 1,initialValue = 1)
//Json에러를 처리하기 위해서
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Locker {
	@Id
	@GeneratedValue(generator = "seq_lockerNo", strategy = GenerationType.SEQUENCE)
	private Long lockerNo;
	
	private int floor;
	
	private String color;
	
	@OneToOne(mappedBy = "locker")
	private Student s;
	
	
}
