package com.wallen.practise.spring6.resources.loader.aware;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * @author Wallen
 * @date 2025/1/2 10:15
 */
public class ResourceLoaderAwareDemo {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        TestBean           testBean    = context.getBean(TestBean.class);

        ResourceLoader resourceLoader = testBean.getResourceLoader();

        System.out.println(context == resourceLoader);
    }
}
