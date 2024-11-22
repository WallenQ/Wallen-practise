package com.wallen.test.spring6.ioc.annotation.autowired.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Wallen
 * @date 2024/10/29 11:40
 */
@Repository
public class UserDaoImpl implements UserDao {

    @Override
    public void add() {
        System.out.println("dao...");
    }
}
