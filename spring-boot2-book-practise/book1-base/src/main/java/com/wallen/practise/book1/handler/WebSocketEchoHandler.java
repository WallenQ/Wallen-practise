package com.wallen.practise.book1.handler;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author Wallen
 * @date 2025-01-31
 */
@Controller
public class WebSocketEchoHandler {

    @MessageMapping("/echo")
    @SendTo("/topic/echo")
    public String echo(String msg) {
        return "Received: " + msg;
    }
}
