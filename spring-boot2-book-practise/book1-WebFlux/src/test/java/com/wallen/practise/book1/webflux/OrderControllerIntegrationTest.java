package com.wallen.practise.book1.webflux;

import com.wallen.practise.book1.webFlux.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrderControllerIntegrationTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    public void listOrders() {
        webClient.get().uri("/orders").exchange().expectStatus().isOk().expectBodyList(Order.class).hasSize(25);
    }

    @Test
    public void addAndGetOrder() {
        var order = new Order("test1", BigDecimal.valueOf(1234.56));
        webClient.post().uri("/orders").syncBody(order).exchange().expectStatus().isOk().expectBody(Order.class).isEqualTo(order);
        webClient.get().uri("/orders/{id}", order.getId()).exchange().expectStatus().isOk().expectBody(Order.class).isEqualTo(order);
    }
}
