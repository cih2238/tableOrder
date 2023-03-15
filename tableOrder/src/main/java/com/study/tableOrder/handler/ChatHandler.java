package com.study.tableOrder.handler;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Component
public class ChatHandler implements WebSocketHandler {

	private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 웹소켓 연결이 열리면 호출되는 메소드
		System.out.println("WebSocket connection established: " + session.getId());
		sessions.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// 클라이언트로부터 메시지를 받으면 호출되는 메소드
		System.out.println("Received message: " + message.getPayload());
		for (WebSocketSession webSocketSession : sessions) {
            webSocketSession.sendMessage(message);
        }
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// 웹소켓 전송 중 오류가 발생하면 호출되는 메소드
		System.out.println("WebSocket error: " + exception.getMessage());
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		// 웹소켓 연결이 닫히면 호출되는 메소드
		System.out.println("WebSocket connection closed: " + session.getId());
		sessions.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
