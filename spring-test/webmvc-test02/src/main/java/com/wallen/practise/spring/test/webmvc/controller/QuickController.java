package com.wallen.practise.spring.test.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wallen
 * @date 2025/4/25 17:27
 */
@Controller
public class QuickController {
    @RequestMapping("/show")
    public String  show() {
        System.out.println("show running...");
        return "/index.jsp";
    }
}
