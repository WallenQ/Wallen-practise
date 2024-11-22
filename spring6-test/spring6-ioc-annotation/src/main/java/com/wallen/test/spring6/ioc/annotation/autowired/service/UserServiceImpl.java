package com.wallen.test.spring6.ioc.annotation.autowired.service;

import com.wallen.test.spring6.ioc.annotation.autowired.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Wallen
 * @date 2024/10/29 11:40
 */
@Service
public class UserServiceImpl implements UserService {
    //@Autowired
    //private UserDao userDao;

    //两个注解，根据名称注入
    @Autowired
    @Qualifier(value = "userRedisDaoImpl")
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("service...");
        userDao.add();
    }
}
