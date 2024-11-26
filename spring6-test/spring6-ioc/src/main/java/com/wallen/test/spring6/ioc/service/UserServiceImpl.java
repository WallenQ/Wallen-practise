package com.wallen.test.spring6.ioc.service;

import com.wallen.test.spring6.ioc.annotation.Bean;
import com.wallen.test.spring6.ioc.annotation.Di;
import com.wallen.test.spring6.ioc.dao.UserDao;

/**
 * @author Wallen
 * @date 2024/11/22 14:27
 */
@Bean
public class UserServiceImpl implements UserService {
    @Di
    private UserDao userDao;

    @Override
    public void add() {
        System.out.println("service add......");
        userDao.add();
    }
}
