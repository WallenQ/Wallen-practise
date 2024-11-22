package com.wallen.test.spring6.ioc.annotation.autowired.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Wallen
 * @date 2024/11/1 16:30
 */
@Repository
public class UserRedisDaoImpl implements UserDao{
    @Override
    public void add() {
        System.out.println("dao redis...");
    }
}
