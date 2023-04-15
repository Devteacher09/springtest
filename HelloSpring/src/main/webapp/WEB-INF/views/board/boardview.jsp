<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="" name="title"/>
</jsp:include>
 <style>
div#board-container{width:400px; margin:0 auto; text-align:center;}
div#board-container input,div#board-container button{margin-bottom:15px;}
div#board-container label.custom-file-label{text-align:left;}
</style>
<section id="content">
<div id="board-container">
        <input type="text" class="form-control"
         placeholder="제목" name="boardTitle" id="boardTitle"  
         value="${board.boardTitle }" required>
        <input type="text" class="form-control" name="boardWriter" 
        value="${board.boardWriter.userId}" readonly required>
		<c:if test="${empty board.files }">
			
		</c:if>
		<c:if test="${not empty board.files }">
			<c:forEach var="f" items="${board.files }">
                <button type="button" 
                class="btn btn-outline-success btn-block"
                onclick="fileDownload('${f.originalFilename}','${f.renamedFilename}');">
                	${f.originalFilename}
        		</button>
			</c:forEach>        		
       	</c:if>
        
        
        <textarea class="form-control" name="boardContent" placeholder="내용" required>${board.boardContent}</textarea>
    </div>
</section>

<script>
	const fileDownload=(ori,re)=>{
		location.assign("${path}/board/filedownload.do?ori="+ori+"&re="+re);
	}

</script>





<jsp:include page="/WEB-INF/views/common/footer.jsp"/>