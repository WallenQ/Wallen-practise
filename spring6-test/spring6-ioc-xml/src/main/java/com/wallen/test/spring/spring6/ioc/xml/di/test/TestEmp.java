package com.wallen.test.spring.spring6.ioc.xml.di.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/21 19:19
 */
public class TestEmp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di-test.xml");
        //外部bean方式
        Emp emp = context.getBean("emp", Emp.class);
        emp.work();
        //内部bean方式
        Emp emp2 = context.getBean("emp2", Emp.class);
        emp2.work();
        //级联赋值
        Emp emp3 = context.getBean("emp3", Emp.class);
        emp3.work();

        ApplicationContext context4 = new ClassPathXmlApplicationContext("bean-di-array.xml");
        //外部bean方式
        Emp emp4 = context4.getBean("emp4", Emp.class);
        emp4.work();
    }
}
