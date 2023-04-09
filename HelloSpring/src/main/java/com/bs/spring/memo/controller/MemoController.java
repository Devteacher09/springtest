package com.bs.spring.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.memo.model.service.MemoService;
import com.bs.spring.memo.model.vo.Memo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {
	
	private MemoService service;
	
	@Autowired
	public MemoController(MemoService service) {
		this.service=service;
	}
	
	
	@RequestMapping("/memoList.do")
	public String memoList(Model m) {
		List<Memo> memos=service.selectMemoAll();
		
		m.addAttribute("memos",memos);
		
		return "memo/memoList";
	}
	
	@RequestMapping("/insertMemo.do")
	public String insertMemo(Memo memo) {
		int result=service.insertMemo(memo);
		return "redirect:/memo/memoList.do";
	}
	
	
	
	
}
