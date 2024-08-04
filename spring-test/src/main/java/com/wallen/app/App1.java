package com.wallen.app;

import com.wallen.bean.Dog;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wallen
 * @date 2023/8/19 15:49
 */
public class App1 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext1.xml");
        Object cat = context.getBean("cat");
        System.out.println(cat);
        Dog dog = context.getBean(Dog.class);
        System.out.println(dog);

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

    }
}
