package com.bs.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bs.spring.model.vo.Food;
import com.bs.spring.model.vo.Person;
import com.bs.spring.model.vo.Phone;

@Configuration//xml파일과 유사한 기능을 하는 클래스
public class SpringBeanConfig {
	
	@Bean
	//@Autowired
	public Food food(@Qualifier("p2") Person person) {
		Food f=new Food();
		f.setName("햄버거");
		f.setPrice(10000);
		f.setP(person);
		System.out.println(f);
		return f;
	}
	
	@Bean
	@Qualifier("p")
	public Phone phone2() {
		return new Phone("아이폰","01044445555");
	}
	
}
