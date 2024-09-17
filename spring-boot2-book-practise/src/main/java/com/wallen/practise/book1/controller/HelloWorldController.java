package com.wallen.practise.book1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wallen
 * @date 2024/9/17 14:16
 */
@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String helloWorld() {
        return "hello world";
    }
}
