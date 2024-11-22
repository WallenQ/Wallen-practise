package com.wallen.test.spring.spring6.ioc.xml.auto.dao;

/**
 * @author Wallen
 * @date 2024/10/25 17:27
 */
public class UserDaoImpl implements UserDao {
    @Override
    public void addUserDao() {
        System.out.println("userDao方法执行了。。。");
    }
}
