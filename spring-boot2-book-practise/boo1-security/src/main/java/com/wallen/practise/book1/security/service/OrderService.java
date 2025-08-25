package com.wallen.practise.book1.security.service;

import com.wallen.practise.book1.security.entity.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Wallen
 * @date 2024/10/13 16:15
 */
@Service
public class OrderService {
    private final List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void setup() {
        createOrders();
    }

    public Iterable<Order> findAll() {
        return List.copyOf(orders);
    }

    private Iterable<Order> createOrders() {
        for (int i = 0; i < 25; i++) {
            this.orders.add(createOrder());
        }
        return orders;
    }

    private Order createOrder() {
        String id     = UUID.randomUUID().toString();
        double amount = ThreadLocalRandom.current().nextDouble(1000.00D);
        return new Order(id, BigDecimal.valueOf(amount));
    }
}
