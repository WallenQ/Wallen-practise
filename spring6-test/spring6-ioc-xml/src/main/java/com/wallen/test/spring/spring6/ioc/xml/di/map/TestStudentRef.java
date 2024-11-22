package com.wallen.test.spring.spring6.ioc.xml.di.map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2024/10/23 15:49
 */
public class TestStudentRef {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di-ref.xml");

        //引用类型注入
        Student student = context.getBean("student", Student.class);
        student.run();

        //p命名空间注入
        Student studentp = context.getBean("studentp", Student.class);
        studentp.run();
    }
}
