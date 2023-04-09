package com.bs.spring.common;

public class PageBarFactory {
	
	public static String getPageBar(int cPage,int numPerpage, 
			int totalData, String url) {
		
		String pageBar="";
		int totalPage=(int)Math.ceil((double)totalData/numPerpage);
		int pageBarSize=5;
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		pageBar="<ul class='pagination justify-content-center pagination-sm'>";
		
		if(pageNo==1) {
			pageBar+="<li class='page-item disabled'>";
			pageBar+="<a href='#' class='page-link'>이전</a>";
			pageBar+="</li>";		
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a href='javascript:fn_paging("+(pageNo-1)+")' class='page-link'>이전</a>";
			pageBar+="</li>";	
		}
		while(!(pageNo>pageEnd||pageNo>totalPage)) {
			if(cPage==pageNo) {
				pageBar+="<li class='page-item disabled'>";
				pageBar+="<a href='#' class='page-link'>"+pageNo+"</a>";
				pageBar+="</li>";	
			}else {
				pageBar+="<li class='page-item'>";
				pageBar+="<a href='javascript:fn_paging("+pageNo+")' class='page-link'>"
							+pageNo+"</a>";
				pageBar+="</li>";	
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar+="<li class='page-item disabled'>";
			pageBar+="<a href='#' class='page-link'>다음</a>";
			pageBar+="</li>";	
		}else {
			pageBar+="<li class='page-item'>";
			pageBar+="<a href='javascript:fn_paging("+pageNo+")' class='page-link'>다음</a>";
			pageBar+="</li>";	
		}
		
		pageBar+="</ul>";
		pageBar+="<script>";
		//pageBar+="const fn_paging=(pageNo)=>{";
		pageBar+="function fn_paging(pageNo){";
		pageBar+="location.assign('"+url+"?cPage='+pageNo+'&numPerpage="
				+numPerpage+"');";
		pageBar+="}";
		pageBar+="</script>";
		
				
		return pageBar;
	}
	
}
