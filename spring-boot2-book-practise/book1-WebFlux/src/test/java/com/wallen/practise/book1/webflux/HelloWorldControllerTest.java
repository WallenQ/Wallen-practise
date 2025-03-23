package com.wallen.practise.book1.webflux;

import com.wallen.practise.book1.webFlux.controller.HelloWorldController;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class HelloWorldControllerTest {

    private final HelloWorldController controller = new HelloWorldController();

    @Test
    public void testHelloWorld() {
        Mono<String> result = controller.hello();

        StepVerifier.create(result).expectNext("Hello World, from Reactive Spring Boot!").verifyComplete();
    }
}
