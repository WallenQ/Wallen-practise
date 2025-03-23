package com.wallen.practise.book1.controller;

import com.wallen.practise.book1.entity.Order;
import com.wallen.practise.book1.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

/**
 * @author Wallen
 * @date 2024/10/13 16:40
 */
@RestController
@RequestMapping
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public ResponseBodyEmitter order() {
        ResponseBodyEmitter emitter  = new ResponseBodyEmitter();
        ExecutorService     executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Iterable<Order> orders = orderService.findAll();
            try {
                for (Order order : orders) {
                    randomDelay();
                    emitter.send(order);
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        executor.shutdown();
        return emitter;
    }

    private void randomDelay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(150));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @GetMapping("/orders")
    public SseEmitter orders() {
        SseEmitter      emitter  = new SseEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Iterable<Order> orders = orderService.findAll();
            try {
                for (Order order : orders) {
                    randomDelay();
                    emitter.send(order);
                }
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        executor.shutdown();
        return emitter;
    }

    @GetMapping("/orders1")
    public SseEmitter orders1() {
        SseEmitter      emitter  = new SseEmitter();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            Iterable<Order> orders = orderService.findAll();
            try {
                for (Order order : orders) {
                    randomDelay();
                    SseEmitter.SseEventBuilder eventBuilder = event();
                    emitter.send(eventBuilder.data(order).name("order-created").id(String.valueOf(order.hashCode())));
                }
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        });
        executor.shutdown();
        return emitter;
    }
}
