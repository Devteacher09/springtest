package com.bs.spring.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

public interface BoardDao {
	
	List<Board> selectBoardList(SqlSessionTemplate session,int cPage,int numPerpage);
	
	int insertBoard(SqlSessionTemplate session, Board b);
	
	int selectBoardCount(SqlSessionTemplate session);
	
	Board selectBoard(SqlSessionTemplate session, int no);
	
	int updateBoardReadCount(SqlSessionTemplate session, int no);
	
	int insertAttachment(SqlSessionTemplate session, Attachment a);
}



