package com.wallen.test.spring.spring6.ioc.xml.auto.service;

import com.wallen.test.spring.spring6.ioc.xml.auto.dao.UserDao;

/**
 * @author Wallen
 * @date 2024/10/25 17:25
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void addUser() {
        System.out.println("userService方法执行了。。。");
        userDao.addUserDao();
    }
}
