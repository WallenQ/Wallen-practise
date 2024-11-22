package com.wallen.test.spring.spring6.ioc.xml.factoryBean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/25 17:21
 */
public class TestUser {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-factorybean.xml");
        User                           user    = context.getBean("user", User.class);
        System.out.println(user);
    }

}
