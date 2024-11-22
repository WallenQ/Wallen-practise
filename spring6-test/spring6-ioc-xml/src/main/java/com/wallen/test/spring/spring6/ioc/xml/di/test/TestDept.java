package com.wallen.test.spring.spring6.ioc.xml.di.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/23 13:56
 */
public class TestDept {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di-list.xml");
        Dept               dept5   = context.getBean("dept5", Dept.class);
        dept5.info();
    }
}
