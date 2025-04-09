package com.wallen.practise.spring.test.aop.annotation.service.impl;

import com.wallen.practise.spring.test.aop.annotation.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Wallen
 * @date 2025/4/2 11:34
 */
@Service
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
