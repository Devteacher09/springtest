package com.bs.spring.chatting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.access.method.MapBasedMethodSecurityMetadataSource;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChattingServer extends TextWebSocketHandler{

	//private Set<WebSocketSession> clients=new HashSet();
	private Map<String,WebSocketSession> clients=new HashMap();
	
	private ObjectMapper mapper=new ObjectMapper();
	@Override
	public void afterConnectionEstablished(WebSocketSession session) 
			throws Exception {
		//클라이언트가 접속하면 실행되는 메소드
		//log.debug("클라이언트 접속");
		
		//접속한 사용자에게 환영메세지 전송
//		Message welcome=new Message("system","admin","","입장을 환영합니다!","");
//		session.sendMessage(new TextMessage(mapper.writeValueAsString(welcome)));
//		clients.add(session);
		
		
		//log.debug("접속한 클라이언트 수 : {}",clients.size());
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//js에서 send(data)함수를 실행하면 실행되는 되는 함수
		log.debug("{}",message.getPayload());
		
		Message msg=mapper.readValue(message.getPayload(),Message.class);
		
		//room에 대한 정보는 msg에 있음
		//원하는 데이터를 session에 저장하는 기능이 있음
		session.getAttributes().put("room",msg.getRoom());
		
		
		
		switch(msg.getType()) {
			case "connect": addClients(session,msg);break;
			case "msg" : sendMessage(msg);break;
		}
		
		
		//매개변수 WebsocketSession은 메세지를 작성한 클라이언트
		//session.sendMessage(message);
//		for(WebSocketSession client:clients) {
//			if(client.isOpen()) client.sendMessage(message);
//		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//클라이언트 접속이 종료되면 실행되는 함수
		log.debug("session종료 : "+status.getReason());
		String userId="";
		for(Map.Entry<String, WebSocketSession> client:clients.entrySet()) {
			if(client.getValue().equals(session)) {
				userId=client.getKey();
			}
		}
		sendMessage(Message.builder().type("system").sender(userId).msg(userId+"님이 퇴장셨습니다.").build());
		deleteClients();
	}

	private void sendMessage(Message msg){
		//클라이언트가 보낸 메세지 전송처리하는 기능
		try {
			if(msg.getReceiver()!=null&&!msg.getReceiver().equals("")) {
				for(Map.Entry<String,WebSocketSession> client : clients.entrySet()) {
					if(client.getValue().isOpen() && 
							client.getKey().equals(msg.getReceiver())
							||client.getKey().equals(msg.getSender())) {
							client.getValue().sendMessage(new TextMessage(mapper.writeValueAsString(msg)));
//							break;
						}
				}	
			}else {
				for(Map.Entry<String,WebSocketSession> client : clients.entrySet()) {
					String room=(String)client.getValue().getAttributes().get("room");
					if(client.getValue().isOpen()) 
						client.getValue().sendMessage(new TextMessage(mapper.writeValueAsString(msg)));
				}	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void addClients(WebSocketSession session,Message msg) {
		clients.put(msg.getSender(), session);
		deleteClients();//접속중단된 클라이언트 삭제하기
		
		Message info=Message.builder().type("system").sender(msg.getSender())
				.msg(msg.getSender()+"님이 입장하셨습니다.").build();
		sendMessage(info);
		try {
			sendMessage(Message.builder().type("attendClient")
					.msg(mapper.writeValueAsString(clients.keySet())).build());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
	private void deleteClients() {
		Iterator<Map.Entry<String,WebSocketSession>> it=clients.entrySet().iterator();
		//for(Map.Entry<String,WebSocketSession> client : clients.entrySet()) {
		while(it.hasNext()) {
			Map.Entry<String,WebSocketSession> client=it.next();
			if(!client.getValue().isOpen()) 
				it.remove();
		}
	}
}
