package com.wallen.practise.spring.test.annotation.service.impl;

import com.wallen.practise.spring.test.annotation.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * @author Wallen
 * @date 2025/3/26 17:58
 */
@Component
@Scope("singleton")
@Lazy
public class UserServiceImpl implements UserService {
    public UserServiceImpl() {
        System.out.println("UserServiceImpl create...");
    }

    @PostConstruct
    public void init() {
        System.out.println("UserServiceImpl init...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("UserServiceImpl destroy...");
    }
}
