package com.wallen.app;

import com.wallen.config.SpringConfig7;
import com.wallen.config.SpringConfig8;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wallen
 * @date 2023/8/19 15:49
 */
public class App8 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig8.class);

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
