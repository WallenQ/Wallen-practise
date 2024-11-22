package com.wallen.test.spring6.ioc.annotation.autowired;

import com.wallen.test.spring6.ioc.annotation.autowired.controller.UserController;
import com.wallen.test.spring6.ioc.annotation.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/29 11:44
 */
public class TestUserController {
    public static void main(String[] args) {
        //配置文件方式
        //ApplicationContext context        = new ClassPathXmlApplicationContext("bean.xml");
        //配置类方式
        ApplicationContext context        = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController     userController = context.getBean(UserController.class);
        userController.add();
    }
}
