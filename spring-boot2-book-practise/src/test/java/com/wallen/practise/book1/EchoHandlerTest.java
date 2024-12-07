package com.wallen.practise.book1;

import com.wallen.practise.book1.handler.EchoHandler;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author Wallen
 * @date 2024/12/7 18:11
 */
public class EchoHandlerTest {
    private final EchoHandler handler = new EchoHandler();

    @Test
    public void shouldEchoMessage() throws Exception {
        WebSocketSession mockSession = Mockito.mock(WebSocketSession.class);
        TextMessage      msg         = new TextMessage("Hello World!");
        handler.handleTextMessage(mockSession, msg);

        Mockito.verify(mockSession, Mockito.times(1))
                .sendMessage(Mockito.eq(new TextMessage("RECEIVE: Hello World!")));
    }
}
