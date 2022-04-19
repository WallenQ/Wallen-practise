package com.itcodai.course01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试开始
 *
 * @author Wallen
 * @date 2021年2月4日
 */
@RestController
@RequestMapping("/start")
public class StartController {
    @RequestMapping("/springboot")
    public String startSpringBoot (){
        return "Welcome to the world of Spring Boot!";
    }
}
