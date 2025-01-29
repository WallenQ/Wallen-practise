package com.wallen.practise;

import com.wallen.practise.spring.test.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2025/1/16 17:38
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext     = new ClassPathXmlApplicationContext("applicationContext.xml");
        //UserService        userService = applicationContext.getBean("userService", UserService.class);
        //System.out.println(userService);
        //applicationContext.close();
        UserService        userService = applicationContext.getBean("userService3", UserService.class);
        System.out.println(userService);
    }
}
