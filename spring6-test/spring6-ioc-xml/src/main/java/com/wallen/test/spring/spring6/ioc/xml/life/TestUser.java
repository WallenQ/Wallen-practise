package com.wallen.test.spring.spring6.ioc.xml.life;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 测试bean生命周期
 *
 * @author Wallen
 * @date 2024/10/25 14:10
 */
public class TestUser {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean-life.xml");
        User                           user    = context.getBean("user", User.class);
        System.out.println("6.bean对象创建完成了，可以使用了");
        System.out.println(user);
        //bean销毁
        context.close();
    }
}
