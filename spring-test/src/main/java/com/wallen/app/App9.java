package com.wallen.app;

import com.wallen.config.SpringConfig9;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wallen
 * @date 2024/8/4 11:16
 */
public class App9 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig9.class);

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
