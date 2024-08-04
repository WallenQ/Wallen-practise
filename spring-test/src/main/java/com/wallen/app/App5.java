package com.wallen.app;

import com.wallen.bean.Cat;
import com.wallen.bean.Dog;
import com.wallen.config.SpringConfig4;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wallen
 * @date 2023/8/19 15:49
 */
public class App5 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig4.class);
        context.register(Cat.class);
        context.registerBean("tom",Cat.class,1);
        context.registerBean("tom",Cat.class,2);
        context.registerBean("tom",Cat.class,3);

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
        System.out.println(context.getBean(Cat.class));
    }
}
