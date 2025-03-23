package com.wallen.practise.book1;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Wallen
 * @date 2023/1/8 22:05
 */
@Component
public class HelloWorld {

    @PostConstruct
    public void sayHello() {
        System.out.println("Hello World, from Spring Boot 2!");
    }
}
