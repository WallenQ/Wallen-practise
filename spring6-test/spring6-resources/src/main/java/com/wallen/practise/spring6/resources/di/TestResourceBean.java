package com.wallen.practise.spring6.resources.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2025/1/2 10:28
 */
public class TestResourceBean {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");
        ResourceBean       bean    = context.getBean(ResourceBean.class);
        bean.parse();
    }
}
