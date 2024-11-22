package com.wallen.test.spring.spring6.ioc.xml.auto.controller;

import com.wallen.test.spring.spring6.ioc.xml.auto.service.UserService;

/**
 * @author Wallen
 * @date 2024/10/25 17:25
 */
public class UserController {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void addUser() {
        System.out.println("controller方法执行了。。。");
        userService.addUser();
    }
}
