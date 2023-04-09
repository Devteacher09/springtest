package com.bs.spring.memo.model.service;

import java.util.List;

import com.bs.spring.memo.model.vo.Memo;

public interface MemoService {
	
	List<Memo> selectMemoAll();
	
	int insertMemo(Memo m);
	
}
