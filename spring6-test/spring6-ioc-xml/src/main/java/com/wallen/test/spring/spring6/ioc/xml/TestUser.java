package com.wallen.test.spring.spring6.ioc.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/16 15:46
 */
public class TestUser {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        //1.根据id获取bean
        User user1 = (User) context.getBean("user");
        System.out.println("根据id获取bean：" + user1);

        //2.根据类型获取bean,要求IOC容器中指定类型的bean有且只能有一个
        User user2 = context.getBean(User.class);
        System.out.println("根据类型获取bean：" + user2);

        //2.根据id和类型获取bean,要求IOC容器中指定类型的bean有且只能有一个
        User user3 = context.getBean("user", User.class);
        System.out.println("根据id和类型获取bean：" + user3);
    }
}
