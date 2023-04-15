<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시글" name="title"/>
</jsp:include>
<section id="board-container" class="container">
        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
        <button class="btn btn-outline-success"
        onclick="location.assign('${path}/board/boardWrite.do');">글쓰기</button>
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${empty boards }">
            	<tr>
            		<td colspan="6">등록된 게시물이 없습니다.</td>
            	</tr>
            </c:if>
            <c:if test="${not empty boards }">
            	<c:forEach var="b" items="${boards }">
	            	<tr>
            			<td>${b.boardNo }</td>
            			<td>
            				<a href="${path}/board/boardView.do?no=${b.boardNo}">
            					${b.boardTitle }
            				</a>
            			</td>
            			<td>${b.boardWriter.userName }</td>
            			<td>${b.boardDate }</td>
            			<td>
            				<c:if test="${empty b.files}">
            					첨부파일없음
            				</c:if>
            				<c:if test="${not empty b.files }">
            					<img src="${path }/resources/images/file.png"
            					width="25"><span>(${b.files.size() })</span>
            				</c:if>
            			</td>
            			<td>${b.boardReadCount}</td>
	            	</tr>
            	</c:forEach>
            </c:if>
        </table> 
        <div id="pageBar">
        	${pageBar }
        </div>
</section>




<jsp:include page="/WEB-INF/views/common/footer.jsp"/>