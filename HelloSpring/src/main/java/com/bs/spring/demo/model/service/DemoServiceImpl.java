package com.bs.spring.demo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.demo.model.dao.DemoDao;
import com.bs.spring.demo.model.vo.Demo;

@Service
public class DemoServiceImpl implements DemoService {
	
	private DemoDao dao;
	private SqlSessionTemplate sqlSession;
	
	@Autowired
	public DemoServiceImpl(DemoDao dao, SqlSessionTemplate session) {
		// TODO Auto-generated constructor stub
		this.dao=dao;
		this.sqlSession=session;
	}
	
	@Override
	public int insertDemo(Demo demo) {
		return dao.insertDemo(sqlSession, demo);
	}

	@Override
	public List<Demo> selectDemoAll() {
		// TODO Auto-generated method stub
		return dao.selectDemoAll(sqlSession);
	}

}
