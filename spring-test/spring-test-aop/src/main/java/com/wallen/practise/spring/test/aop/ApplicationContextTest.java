package com.wallen.practise.spring.test.aop;

import com.wallen.practise.spring.test.aop.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2025/4/2 11:33
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext context     = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService        userService = context.getBean(UserService.class);
        userService.show1();
        userService.show2();
    }
}
