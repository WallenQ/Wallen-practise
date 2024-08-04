package com.wallen.app;

import com.wallen.bean.Cat;
import com.wallen.bean.Dog;
import com.wallen.config.SpringConfig4;
import com.wallen.config.SpringConfig6;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wallen
 * @date 2023/8/19 15:49
 */
public class App6 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig6.class);

        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
