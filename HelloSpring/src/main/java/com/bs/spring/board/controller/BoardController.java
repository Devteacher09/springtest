package com.bs.spring.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Board;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private BoardService service;

	@Autowired
	public BoardController(BoardService service) {
		this.service = service;
	}

	@RequestMapping("/boardList.do")
	public String selectBoardList(
			@RequestParam(value="cPage",defaultValue ="1") int cPage, 
			@RequestParam(value="numPerpage",defaultValue = "5") int numPerpage,
			Model m) {
		
		
		int totalData=service.selectBoardCount();
		List<Board> list=service.selectBoardList(cPage,numPerpage);
		
		m.addAttribute("boards",list);
		m.addAttribute("totalContents",totalData);
		
		return "board/boardList";
	}
	
	
	
}
