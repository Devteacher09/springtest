package com.bs.spring.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
	private String name;
	private int age;
	private double height;
	private Phone phone;
	
	{
		System.out.println("초기화블록실행");		
	}
	
	public Person() {
		System.out.println("Person기본생성자호출");
	}
	
	public void inforPerson() {
		System.out.println(this.name+" "
				+this.age+" "+this.height
				+this.phone);
	}
	
	
	
	
}
