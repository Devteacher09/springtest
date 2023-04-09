package com.bs.spring.memo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.memo.model.vo.Memo;

public class MemoDaoImpl implements MemoDao {

	@Override
	public List<Memo> selectMemoAll(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("memo.memoList");
	}

	@Override
	public int insertMemo(SqlSessionTemplate session, Memo m) {
		// TODO Auto-generated method stub
		return session.insert("memo.insertMemo",m);
	}

}
