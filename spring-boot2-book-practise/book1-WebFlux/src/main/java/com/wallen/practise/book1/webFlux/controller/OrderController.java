package com.wallen.practise.book1.webFlux.controller;

import com.wallen.practise.book1.webFlux.entity.Order;
import com.wallen.practise.book1.webFlux.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

//@RestController
@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Mono<Order> store(@RequestBody Mono<Order> order) {
        return orderService.save(order);
    }

    @GetMapping("/{id}")
    public Mono<Order> get(@PathVariable("id") String id) {
        return orderService.findById(id);
    }

    @GetMapping
    public Flux<Order> getAll() {
        return orderService.orders();
    }

    /**
     * json流
     * @return
     */
    @GetMapping(value = "/list2",produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Order> list2() {
        return orderService.orders();
    }

    /**
     * 服务器发送的事件
     * @return
     */
    @GetMapping(value = "/list1",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Order> list1() {
        return orderService.orders();
    }

    @GetMapping(value = "/list")
    public Mono<String> list(Model model) {
        List<Order> orders = orderService.getAll();
        model.addAttribute("orders",orders);
        return Mono.just("orders/list");
    }

    @GetMapping(value = "/list3")
    public Mono<String> list3(Model model) {
        Flux<Order> orders = orderService.orders();
        model.addAttribute("orders",new ReactiveDataDriverContextVariable(orders,5));
        return Mono.just("orders/list3");
    }
}
