package com.wallen.practise;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

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
    @Import((HelloWorldApplication.class))
    public static class EchoHandlerIntegrationTestConfiguration{
        @Bean
        public NettyReactiveWebServerFactory webServerFactory(){
            return new NettyReactiveWebServerFactory();
        }
    }
}
