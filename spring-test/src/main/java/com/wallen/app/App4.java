package com.wallen.app;

import com.wallen.bean.Dog;
import com.wallen.config.SpringConfig4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wallen
 * @date 2023/8/19 15:49
 */
public class App4 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig4.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println(context.getBean(Dog.class));
        System.out.println(context.getBean(Dog.class));
        System.out.println(context.getBean(Dog.class));
    }
}
