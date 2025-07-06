package com.wallen.practise.book1.webFlux.handler;


import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

/**
 * @Author qianwenlong
 * @Date 2025/7/6 15:50
 */
public class EchoHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(session.receive().map(msg -> "RECEIVED:" + msg.getPayloadAsText()).map(session::textMessage));
    }
}
