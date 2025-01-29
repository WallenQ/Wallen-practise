package com.wallen.app;

import com.wallen.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wallen
 * @date 2023/8/19 15:49
 */
public class App3 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println(context.getBean("dog"));
        System.out.println(context.getBean("dog"));
        System.out.println(context.getBean("dog"));

    }
}
