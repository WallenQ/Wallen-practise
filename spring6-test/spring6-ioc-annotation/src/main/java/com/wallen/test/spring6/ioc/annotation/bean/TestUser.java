package com.wallen.test.spring6.ioc.annotation.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/29 09:50
 */
public class TestUser {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User               user    = context.getBean("user", User.class);
        System.out.println(user);
    }
}
