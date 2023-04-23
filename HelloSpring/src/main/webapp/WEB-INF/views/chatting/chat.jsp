<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>mega채팅</title>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.6.3.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/js/chatting.js"></script>

</head>
<body>
	<div id="inputContainer">
		<input type="text" id="msg"><button id="sendMsg">전송</button>
	</div>
	<div id="msgContainer">
		
	</div>
	<script>
		//소켓연결을 위해서 js에서 제공하는 WebSocket객체를 생성한다.
		//매개변수있는 생성자를 이용 매개변수에는 연결할 서버 주소를 문자열로 대입
		//주소는 패턴을 정해놨음..
		//http방식, https방식
		// ws : http프로토콜이용시
		// 예) ws://ip주소,도메인이름:포트번호/프로젝트주소/mapping주소
		// wss : https프로토콜이용시
		// 예) wss://ip주소,도메인이름:포트번호/프로젝트주소/mapping주소
		const websocket=new WebSocket("ws://localhost:9090/${pageContext.request.contextPath}/chatting");
		const loginMemberId='${loginMember.userId}';
		//socket에 필요한 속성에 이벤트핸들러를 등록해준다.
		websocket.onopen=e=>{
			//서버와 접속된 후 자동으로 실행되는 함수
			console.log(e);
			$("#msgContainer").html("<h4>채팅서버접속완료</h4>");
			const access=new Message("connect",loginMemberId,'','','');
			websocket.send(JSON.stringify(access));
		}
		websocket.onmessage=e=>{
			//서버에서 sendMessage()메소드를 호출하면 실행되는 함수
			console.log(e);
			const data=JSON.parse(e.data);
			console.log(data);
			//$("#msgContainer").append("<p>"+e.data+"</p>");
			//const msg=e.data.split(",");
			//0 : 보낸사람, 1 : 받는사람 2 메세지
			/* const messagecontainer=$("<div>");
			messagecontainer.append($("<small>").text(msg[0]));
			messagecontainer.append($("<span>").text(msg[2]));
			$("#msgContainer").append(messagecontainer); */
			switch(data.type){
				case "system": addMsgSystem(data);break;
				case "msg" : break;
			}
		}
		websocket.onclose=e=>{
			//서버와 연결이 차단되었을때 실행되는 함수
			
		}
		
		
		$("#sendMsg").click(e=>{
			const msg=$("#msg").val();
			//csv방식으로 전송하기
			//sender,receiver,msg
			const sender='${loginMember.userId}';
			const receiver='';
			
			const sendMessage=new Message("msg",sender,receiver,msg,'');
			
			
			//메세지를 서버에 전송하려면 send()함수를 이용한다.
			websocket.send(JSON.stringify(sendMessage));//서버의 hadleTextMessage메소드가 실행.
		});
		
		class Message{
			constructor(type,sender,receiver,msg,room){
				this.type=type;
				this.sender=sender;
				this.receiver=receiver;
				this.msg=msg;
				this.room=room;
			}
		}
	</script>
	
	
	
	
</body>
</html>





