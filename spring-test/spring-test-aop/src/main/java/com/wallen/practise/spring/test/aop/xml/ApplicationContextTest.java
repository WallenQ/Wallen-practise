package com.wallen.practise.spring.test.aop.xml;

import com.wallen.practise.spring.test.aop.xml.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2025/4/2 11:33
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        /*ApplicationContext context     = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService        userService = context.getBean(UserService.class);
        userService.show1();
        userService.show2();*/

        ApplicationContext context2     = new ClassPathXmlApplicationContext("applicationContext2.xml");
        UserService        userService2 = context2.getBean(UserService.class);
        userService2.show1();
        userService2.show2();
    }
}
