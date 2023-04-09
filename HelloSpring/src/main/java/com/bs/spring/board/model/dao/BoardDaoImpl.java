package com.bs.spring.board.model.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring.board.model.vo.Board;
@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> selectBoardList(SqlSessionTemplate session,
			int cPage,int numPerpage) {
		// TODO Auto-generated method stub
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("board.boardList",null,rb);
	}

	@Override
	public int insertBoard(SqlSessionTemplate session, Board b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectBoardCount(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectOne("board.boardCount");
	}
	

}
