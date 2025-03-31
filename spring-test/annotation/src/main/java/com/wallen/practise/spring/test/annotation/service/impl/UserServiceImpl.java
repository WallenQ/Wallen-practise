package com.wallen.practise.spring.test.annotation.service.impl;

import com.wallen.practise.spring.test.annotation.bean.User;
import com.wallen.practise.spring.test.annotation.mapper.UserMapper;
import com.wallen.practise.spring.test.annotation.service.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author Wallen
 * @date 2025/3/26 17:58
 */
//@Component
//@Scope("singleton")
//@Lazy
@Service
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

    @Autowired
    private UserMapper userMapper;

    @Override
    public void show() {
        List<User> all = userMapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }
    }
}
