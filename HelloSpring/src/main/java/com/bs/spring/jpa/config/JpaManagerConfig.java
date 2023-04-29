package com.bs.spring.jpa.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaManagerConfig {
	//jpa를 이용해서 DB를 이용하려면 
	//jpa가 제공하는 EntityManager클래스를 생성해야한다.
	//Persistence클래스의 createEntityManagerFactory() static메소드를 이용해서
	// EntityManagerFactory클래스를 먼저 생성함. 
	//	-> 매개변수로 persistence.xml에설정한 persistence-unit name값을 전달.
	//EntityManagerFactory클래스가 제공하는 createEntityManager()메소드를 이용해서
	// EntityManager클래스를 생성함.
	
	//EntityManagerFatory를 생성하는 과정은 비용이 크기때문에 애플리케션에서 한번만 
	//생성하는게 좋다!
	
	
	
	
}





