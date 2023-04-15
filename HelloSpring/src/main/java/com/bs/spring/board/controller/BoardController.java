package com.bs.spring.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bs.spring.board.model.service.BoardService;
import com.bs.spring.board.model.vo.Attachment;
import com.bs.spring.board.model.vo.Board;
import com.bs.spring.common.PageBarFactory;
import com.bs.spring.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
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
		m.addAttribute("pageBar",PageBarFactory.getPageBar(cPage, numPerpage, totalData, "boardList.do"));
		
		return "board/boardList";
	}
	
	@RequestMapping("/boardView.do")
	public String selectBoard(int no, Model m) {
		
		m.addAttribute("board",service.selectBoard(no));
		
		return "board/boardview";
		
	}
	
	@RequestMapping("/boardWrite.do")
	public String boardWrite() {
		return "board/boardWrite";
	}
	
	
	@RequestMapping("/boardWriteEnd.do")
	public String insertBoard(MultipartFile[] upFile,
			String boardTitle, String boardContent, String boardWriter,
			HttpSession session, Model m) {
//		log.debug("{}",board);
//		log.debug("{}",upFile);
//		log.debug("{}",upFile.getName());
//		log.debug("{}",upFile.getSize());
//		log.debug("{}",upFile.getOriginalFilename());
		log.debug(boardTitle+" "+boardContent+" "+boardWriter);
		
		//파일 업로드처리하기
		//1. 저장할 위치선정 -> 절대경로 가져오기
		String path=session.getServletContext().getRealPath("/resources/upload/board/");
		log.debug(path);
		
		//2. 저장할 위치가 있는지 확인하고 없으면 생성하기
		File dir=new File(path);
		if(!dir.exists()) dir.mkdirs();
		
		//Attachment file=null;
		List<Attachment> files=new ArrayList();
		
		//3. MultipartFile클래스를 이용해서 업로드처리하기
		// 1) 파일명 리네임처리하기
		// 2) 리네임된 파일명으로 파일 서버에 저장하기 -> MultipartFile.transTo()메소드이용
		for(MultipartFile f : upFile) {
			if(f!=null&&!f.isEmpty()) {
				//파일리네임 만들기
				String originalFilename=f.getOriginalFilename();
				String ext=originalFilename.substring(originalFilename.lastIndexOf("."));
				//중복없게 파일명만들기
				SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rnd=(int)(Math.random()*10000)+1;
				String renamedFilename=sdf.format(System.currentTimeMillis())+"_"+rnd+ext;
				
				//파일저장처리하기 -> 지정된 위치(폴더)에 저장
				try {
					
					f.transferTo(new File(path,renamedFilename));
					
	//				file.setOriginalFilename(originalFilename);
	//				file.setRenamedFilename(renamedFilename);
					files.add(Attachment.builder()
							.originalFilename(originalFilename)
							.renamedFilename(renamedFilename)
							.build());
					
				}catch(IOException e) {
					e.printStackTrace();
				}	
			}
		}
		Board b=Board.builder()
				.boardTitle(boardTitle)
				.boardWriter(Member.builder().userId(boardWriter).build())
				.boardContent(boardContent)
				.files(files)
				.build();
		int result=0;
		try {
			result=service.insertBoard(b);
		}catch(RuntimeException e) {
			//업로드된 데이터 삭제
			for(Attachment a : files) {
				File f=new File(path+"/"+a.getRenamedFilename());
				if(f.exists()) f.delete();
			}
		}
		String msg="",loc="";
		
		if(result>0) {
			msg="게시글 등록 성공 :) ";
			loc="/board/boardList.do";
		}else{
			msg="게시글 등록 실패 :( ";
			loc="/board/boardWrite.do";
		}
		
		m.addAttribute("msg",msg);
		m.addAttribute("loc",loc);
		
		return "common/msg";
	}
	
	
	@RequestMapping("/filedownload.do")
	public void fileDownload(String ori, String re,
			HttpServletResponse res,HttpSession session,
			@RequestHeader(value="User-agent") String header) {
		//절대경로가져오기
		String path=session.getServletContext()
				.getRealPath("/resources/upload/board/");
		File downFile=new File(path+re);
		try(FileInputStream fis=new FileInputStream(downFile);
				BufferedInputStream bis=new BufferedInputStream(fis);){
			ServletOutputStream sos=res.getOutputStream();
			
			//파일명 인코딩하기
			boolean isMS=header.contains("Trident")||header.contains("MSIE");
			
			String encodeName="";
			if(isMS) {
				encodeName=URLEncoder.encode(ori,"UTF-8");
				encodeName=encodeName.replaceAll("\\+", "%20");
			}else {
				encodeName=new String(ori.getBytes("UTF-8"),"ISO-8859-1");
			}
			
			res.setContentType("application/octet-stream;charset=utf-8");
			res.setHeader("Content-Disposition","attachment;filename=\""+encodeName+"\"");
			
			int data=-1;
			while((data=bis.read())!=-1) {
				sos.write(data);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
}
