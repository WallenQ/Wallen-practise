package com.wallen.test.spring.spring6.ioc.xml.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/25 13:57
 */
public class TestOrder {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-scope.xml");
        Order              order   = context.getBean("order", Order.class);
        System.out.println(order);
        Order              order1   = context.getBean("order", Order.class);
        System.out.println(order1);
    }
}
