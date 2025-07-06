package com.wallen.practise.book1.webFlux.service;

import com.wallen.practise.book1.webFlux.entity.Order;
import com.wallen.practise.book1.webFlux.util.OrderGenerator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {
    private final Map<String, Order> orders = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        OrderGenerator generator = new OrderGenerator();
        for (int i = 0; i < 25; i++) {
            Order order = generator.generate();
            orders.put(order.getId(), order);
        }
    }

    public Mono<Order> findById(String id) {
        return Mono.justOrEmpty(orders.get(id));
    }

    public Mono<Order> save(Mono<Order> order) {
        return order.map(this::save);
    }

    public Order save(Order order) {
        return orders.put(order.getId(), order);
    }

    public Flux<Order> orders() {
        return Flux.fromIterable(orders.values()).delayElements(Duration.ofMillis(128));
    }

    public List<Order> getAll() {
        return orders.values().stream().toList();
    }
}
