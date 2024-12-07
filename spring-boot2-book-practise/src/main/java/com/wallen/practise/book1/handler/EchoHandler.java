package com.wallen.practise.book1.handler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * @author Wallen
 * @date 2024/12/7 17:41
 */
public class EchoHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        session.sendMessage(new TextMessage("CONNECTION ESTABLISHED"));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        session.sendMessage(new TextMessage("RECEIVED:" + msg));
    }
}
