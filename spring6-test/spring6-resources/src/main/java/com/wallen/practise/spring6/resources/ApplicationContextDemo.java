package com.wallen.practise.spring6.resources;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * @author Wallen
 * @date 2024/12/31 18:07
 */
public class ApplicationContextDemo {
    @Test
    public void testClassPathXml() {
        ApplicationContext context  = new ClassPathXmlApplicationContext();
        Resource           resource = context.getResource("test.txt");
        System.out.println(resource.getFilename());
    }

    @Test
    public void testFileSystemXml() {
        ApplicationContext context  = new FileSystemXmlApplicationContext();
        Resource           resource = context.getResource("test.txt");
        System.out.println(resource.getFilename());
    }
}
