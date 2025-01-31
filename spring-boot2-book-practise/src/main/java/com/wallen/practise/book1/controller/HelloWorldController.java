package com.wallen.practise.book1.controller;

import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Wallen
 * @date 2024/9/17 14:16
 */
//@RestController
public class HelloWorldController {
    @GetMapping("/helloWorld")
    public String helloWorld() {
        return "hello world";
    }

    @GetMapping("/hello1")
    public Callable<String> hello1() {
        return () -> {
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
            return "Hello World, from Spring Boot 2!";
        };
    }

    private final TaskExecutor taskExecutor;

    public HelloWorldController(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    @GetMapping("/hello2")
    public CompletableFuture<String> hello2() {
        return CompletableFuture.supplyAsync(() -> {
            randomDelay();
            return "Hello World, from Spring Boot 2!";
        }, taskExecutor);
    }

    private void randomDelay() {
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
