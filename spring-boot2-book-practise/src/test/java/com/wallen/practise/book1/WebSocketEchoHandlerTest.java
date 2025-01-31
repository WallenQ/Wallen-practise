package com.wallen.practise.book1;

import com.wallen.practise.book1.handler.WebSocketEchoHandler;
import org.junit.Test;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;

public class WebSocketEchoHandlerTest {
    private final WebSocketEchoHandler handler = new WebSocketEchoHandler();

    @Test
    public void testEchoMessage() throws Exception {
        String message = "Hello World!";
        assertThat(handler.echo(message)).isEqualTo("Received: " + message);
    }

    public static class TestStompFrameHandler extends StompSessionHandlerAdapter {
        private final CompletableFuture<String> answer;

        public TestStompFrameHandler(CompletableFuture<String> answer) {
            this.answer = answer;
        }

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return byte[].class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            answer.complete(new String((byte[]) payload));
        }
    }
}
