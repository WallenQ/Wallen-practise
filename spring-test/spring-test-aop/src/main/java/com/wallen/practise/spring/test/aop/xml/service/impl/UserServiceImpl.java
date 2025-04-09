package com.wallen.practise.spring.test.aop.xml.service.impl;

import com.wallen.practise.spring.test.aop.xml.service.UserService;

/**
 * @author Wallen
 * @date 2025/4/2 11:34
 */
public class UserServiceImpl implements UserService {
    @Override
    public void show1() {
        System.out.println("show1...");
    }

    @Override
    public void show2() {
        System.out.println("show2...");
         //int i = 1/0;
    }
}
