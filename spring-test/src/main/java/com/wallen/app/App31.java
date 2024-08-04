package com.wallen.app;

import com.wallen.config.SpringConfig2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wallen
 * @date 2023/8/19 15:49
 */
public class App31 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig2.class);
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }

        SpringConfig2 config = context.getBean("springConfig2", SpringConfig2.class);
        System.out.println(config.book());
        System.out.println(config.book());
        System.out.println(config.book());
    }
}
