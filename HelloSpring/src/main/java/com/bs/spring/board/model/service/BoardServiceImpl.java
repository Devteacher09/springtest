package com.bs.spring.board.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring.board.model.dao.BoardDao;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	private BoardDao dao;
	private SqlSessionTemplate session;
	
	@Autowired	
	public BoardServiceImpl(BoardDao dao, SqlSessionTemplate session) {
		super();
		this.dao = dao;
		this.session = session;
	}

	@Override
	public List<Board> selectBoardList(int cPage, int numPerpage) {
		// TODO Auto-generated method stub
		return dao.selectBoardList(session,cPage,numPerpage);
	}

	@Override
	public int insertBoard(Board b) {
		int result=dao.insertBoard(session, b);
		log.debug("생성된 게시글번호 {}",b.getBoardNo());
		if(result>0&&b.getFiles().size()>0) {
			for(Attachment a : b.getFiles()) {
				a.setBoardNo(b.getBoardNo());
				result=dao.insertAttachment(session,a);
			}
		}
		return result;
	}

	@Override
	public int selectBoardCount() {
		// TODO Auto-generated method stub
		return dao.selectBoardCount(session);
	}

	@Override
	public Board selectBoard(int no) {
		//조회수 증가하는 로직 추가하기
		Board b=dao.selectBoard(session,no);	
		if(b!=null) {
			int result=dao.updateBoardReadCount(session,no);
			//if(result==0) new RuntimeException();
		}
//		else {
//			throw new 
//		}
		return b;
	}
	
	
	
	
	

}
