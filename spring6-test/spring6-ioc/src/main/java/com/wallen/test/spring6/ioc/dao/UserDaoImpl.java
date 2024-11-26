package com.wallen.test.spring6.ioc.dao;

import com.wallen.test.spring6.ioc.annotation.Bean;

/**
 * @author Wallen
 * @date 2024/11/22 14:28
 */
@Bean
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao add......");
    }
}
