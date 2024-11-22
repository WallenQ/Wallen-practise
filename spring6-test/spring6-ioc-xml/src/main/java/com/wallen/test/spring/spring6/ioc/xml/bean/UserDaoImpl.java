package com.wallen.test.spring.spring6.ioc.xml.bean;

/**
 * @author Wallen
 * @date 2024/10/16 15:59
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void run() {
        System.out.println("UserDaoImpl run......");
    }
}
