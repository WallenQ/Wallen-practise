package com.wallen.practise.spring.test.webmvc.controller;

import com.wallen.practise.spring.test.webmvc.service.QuickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Wallen
 * @date 2025/4/25 17:27
 */
@Controller
public class QuickController {
    @Autowired
    private QuickService quickService;

    @RequestMapping("/show")
    public String show() {
        System.out.println("show running... : " + quickService);
        return "/index.jsp";
    }
}
