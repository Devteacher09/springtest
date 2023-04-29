<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="MainPage" name="title"/>
</jsp:include>
<section id="content">
	<h2>Hello Spring</h2>
	<img src="${path }/resources/images/logo-spring.png" id="center-image"
	alt="스프링로고"/>
	
	
	<button onclick="memberAll();" class="btn btn-outline-primary">전제회원조회</button>
	<div id="memberList"></div>	
	
	<button onclick="insertMember();" class="btn btn-outline-primary">
		회원가입
	</button>
	<script>
		const insertMember=()=>{
			//비동식통신을할때 js가 기본으로 제공하는 함수를 만들었음
			//fetch()함수임. 요청을 순차적으로 처리하기 위해 promise객체를 반환해서 처리
			//fetch("url주소",{요청옵션(방식, body내용, 해더내용)})
			//.then(response=>response.json())
			//.then(data=>{처리로직})
			fetch("${path}/member/ajax/insert",{
				method:"post",
				headers:{
					"Content-Type":"application/json"
				},
				body:JSON.stringify({userId:"test22",password:"1234",
					userName:"유병승",gender:"M",age:19})
			})
			.then(response=>{console.log(response);return response.json()})
			.then(data=>{
				console.log(data);
			});
			
		}	
	
	
		const memberAll=()=>{
			$.get("${path}/member/memberList",data=>{
				console.log(data);
				const table=$("<table>");
				const header=$("<tr>");
				header.append("<th>아이디</th>")
					.append("<th>이름</th>")
					.append("<th>나이</th>")
					.append("<th>성별</th>")
					.append("<th>전화번호</th>")
					.append("<th>이메일</th>")
					.append("<th>주소</th>")
					.append("<th>취미</th>")
					.append("<th>가입일</th>");
				table.append(header);
				data.forEach(e=>{
					let tr=$("<tr>");
					let userId=$("<td>").text(e.userId);
					let userName=$("<td>").text(e.userName);
					let age=$("<td>").text(e.age);
					let gender=$("<td>").text(e.gender);
					let phone=$("<td>").text(e.phone);
					let email=$("<td>").text(e.email);
					let address=$("<td>").text(e.address);
					let hobby=$("<td>").text(e.hobby.toString());
					let enrollDate=$("<td>").text(new Date(e.enrollDate));
					tr.append(userId).append(userName).append(age)
					.append(gender).append(phone).append(email).append(address)
					.append(hobby).append(enrollDate);
					table.append(tr);
				});
				
				$("#memberList").html(table);
			});
		}
	
	</script>
	
	<div id="jpacontainer">
		<h3><a href="${path }/jpa/basicjpa">기본 jpa구문활용하기</a></h3>
	</div>
	
	
	
	
	
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
