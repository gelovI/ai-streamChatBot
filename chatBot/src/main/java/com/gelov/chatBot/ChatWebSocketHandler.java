package com.gelov.chatBot;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    @Autowired
    private OllamaService ollamaService;

    private final List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String userMessage = message.getPayload();
        TextMessage userMessageFormatted = new TextMessage("You: " + userMessage);

        // Sende die Benutzer-Nachricht sofort an den Client
        session.sendMessage(userMessageFormatted);

        // Hole die Antwort der KI asynchron
        new Thread(() -> {
            try {
                String aiResponse = ollamaService.getAIResponse(userMessage);
                TextMessage responseMessage = new TextMessage(aiResponse);
                session.sendMessage(responseMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}