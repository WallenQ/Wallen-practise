package com.wallen.practise;

import com.wallen.practise.book1.webflux.SimpleTestClientEndpoint;
import jakarta.websocket.ContainerProvider;
import jakarta.websocket.WebSocketContainer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @Author qianwenlong
 * @Date 2025/7/21 16:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloWorldApplicationTest {
    @LocalServerPort
    private int port;

    @Configuration
    @Import(HelloWorldApplication.class)
    public static class EchoHandlerIntegrationTestConfiguration{
        @Bean
        public NettyReactiveWebServerFactory webServerFactory(){
            return new NettyReactiveWebServerFactory();
        }
    }

    @Test
    public void sendAndReceiveMessage() throws Exception{
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        URI uri = URI.create("ws://localhost:" + port + "/echo");
        SimpleTestClientEndpoint testClientEndpoint = new SimpleTestClientEndpoint();
        container.connectToServer(testClientEndpoint,uri);

        testClientEndpoint.sendTextAndWait("hello world!",200);
        testClientEndpoint.closeAndWait(2);

        System.out.println(testClientEndpoint.getReceived());
        assertThat(testClientEndpoint.getReceived()).containsExactly("RECEIVED:hello world!");
    }
}
