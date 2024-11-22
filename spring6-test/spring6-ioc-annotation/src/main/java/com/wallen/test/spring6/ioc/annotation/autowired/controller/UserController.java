package com.wallen.test.spring6.ioc.annotation.autowired.controller;

import com.wallen.test.spring6.ioc.annotation.autowired.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Wallen
 * @date 2024/10/29 11:38
 */
@Controller
public class UserController {
    /**
     * 第一种方式 属性注入
     */
    //@Autowired
    //private UserService userService;
    /**
     * 第二种方式set方式注入
     */
    //private UserService userService;
    //@Autowired
    //public void setUserService(UserService userService) {
    //    this.userService = userService;
    //}

    /**
     * 第三种方式，构造方法注入
     */
    //private UserService userService;
    //@Autowired
    //public UserController(UserService userService) {
    //    this.userService = userService;
    //}

    /**
     * 第四种方式，形参注入
     */
    //private UserService userService;
    //public UserController(@Autowired UserService userService) {
    //    this.userService = userService;
    //}

    /**
     * 第五种方式： 只有一个有参数构造，可以不加注解
     */
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void add() {
        System.out.println("controller...");
        userService.add();
    }
}
