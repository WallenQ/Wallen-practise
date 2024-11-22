package com.wallen.test.spring.spring6.ioc.xml.di.map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/23 15:49
 */
public class TestStudent {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di-map.xml");
        Student            student = context.getBean("student", Student.class);
        student.run();
    }
}
