package com.wallen.practise.book1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.websocket.ContainerProvider;
import javax.websocket.WebSocketContainer;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Wallen
 * @date 2024/12/7 18:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EchoHandlerIntegrationTest {
    @LocalServerPort
    private int port;

    @Test
    public void sendAndReceiveMessage() throws Exception {
        WebSocketContainer       container  = ContainerProvider.getWebSocketContainer();
        URI                      uri        = URI.create("ws://localhost:" + port + "/echo");
        SimpleTestClientEndpoint testClient = new SimpleTestClientEndpoint();
        container.connectToServer(testClient, uri);
        testClient.sendTextAndWait("Hello World!", 200);
        testClient.closeAndWait(2);
        assertThat(testClient.getReceived()).containsExactly("CONNECTION ESTABLISHED", "RECEIVED: Hello World!");
    }
}
