package com.wallen.practise.spring.test.annotation;

import com.wallen.practise.spring.test.annotation.config.SpringConfig;
import com.wallen.practise.spring.test.annotation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2025/3/26 17:57
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        //xml方式创建容器
        //ClassPathXmlApplicationContext context     = new ClassPathXmlApplicationContext("applicationContext.xml");
        //UserService                    userService = context.getBean(UserService.class);
        //System.out.println(userService);
        //context.close();

        //注解方式创建容器
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService        userService1       = applicationContext.getBean(UserService.class);
        System.out.println(userService1);
    }
}
