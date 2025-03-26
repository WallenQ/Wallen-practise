package com.wallen.practise.spring.test.annotation;

import com.wallen.practise.spring.test.annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2025/3/26 17:57
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext context     = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService        userService = context.getBean(UserService.class);
        System.out.println(userService);
    }
}
