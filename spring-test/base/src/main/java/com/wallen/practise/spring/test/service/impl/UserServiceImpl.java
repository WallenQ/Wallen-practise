package com.wallen.practise.spring.test.service.impl;

import com.wallen.practise.spring.test.dao.UserDao;
import com.wallen.practise.spring.test.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author Wallen
 * @date 2025/1/15 09:46
 */
//@Service
public class UserServiceImpl implements UserService , InitializingBean {

    public UserServiceImpl() {
        System.out.println("UserServiceImpl初始化");
    }

    private UserDao userDao;

    /**
     * BeanFactory去调用该方法，从容器中获取userDao设置到此处
     *
     * @param userDao
     */
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
        System.out.println("BeanFactory去调用该方法，从容器中获取userDao设置到此处:" + userDao);
    }

    public void init() {
        System.out.println("UserServiceImpl初始化方法...");
    }

    public void destroy() {
        System.out.println("UserServiceImpl销毁方法...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean...");
    }
}
