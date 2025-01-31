package com.wallen.practise.book1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketEchoHandlerIntegrationTest {
    @LocalServerPort
    private int port;

    private WebSocketStompClient stompClient;

    private List<StompSession> sessions = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        StandardWebSocketClient webSocketClient = new StandardWebSocketClient();
        stompClient = new WebSocketStompClient(webSocketClient);
    }

    @After
    public void clearUp() throws Exception {
        this.sessions.forEach(StompSession::disconnect);
        sessions.clear();
    }

    @Test
    public void shouldSendAndReceiveMessage() throws Exception {
        CompletableFuture<String> answer = new CompletableFuture<>();
        StompSession stompSession = conncetAndSubscribe(answer);

        stompSession.send("/app/echo", "Hello World!".getBytes());

        String result = answer.get(1, TimeUnit.SECONDS);
        assertThat(result).isEqualTo("Received: Hello World!");

    }


    private StompSession conncetAndSubscribe(CompletableFuture<String> answer) throws Exception {
        String uri = "ws://localhost:" + port + "/echo-endpoint";
        StompSession stompSession = stompClient.connect(uri, new StompSessionHandlerAdapter() {
        }).get(1, TimeUnit.SECONDS);
        stompSession.subscribe("/topic/echo", new WebSocketEchoHandlerTest.TestStompFrameHandler(answer));
        this.sessions.add(stompSession);
        return stompSession;
    }
}
