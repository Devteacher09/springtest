package com.bs.spring.model.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
//어노테이션으로 springbean등록하기
@Component("f")
public class Food {
	private String name;
	private int price;
	private Person p;
}
