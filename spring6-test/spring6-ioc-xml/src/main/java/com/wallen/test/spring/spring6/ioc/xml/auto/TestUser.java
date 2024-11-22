package com.wallen.test.spring.spring6.ioc.xml.auto;

import com.wallen.test.spring.spring6.ioc.xml.auto.controller.UserController;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自动装配
 *
 * @author Wallen
 * @date 2024/10/25 17:33
 */
public class TestUser {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context        = new ClassPathXmlApplicationContext("bean-auto.xml");
        UserController                 userController = context.getBean("userController", UserController.class);
        userController.addUser();
    }
}
