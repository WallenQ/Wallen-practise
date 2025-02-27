package com.wallen.practise;

import com.wallen.practise.spring.test.dao.UserDao;
import com.wallen.practise.spring.test.service.UserService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author Wallen
 * @date 2025/1/15 09:43
 */
public class BeanFactoryTest {
    public static void main(String[] args) {
        //创建工厂对象
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //创建一个读取器（xml文件）
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //读取配置文件给工厂
        reader.loadBeanDefinitions("bean.xml");
        //根据id获取bean实例对象
        UserService userService = beanFactory.getBean("userService", UserService.class);
        //System.out.println(userService);

        UserDao userDao = beanFactory.getBean( UserDao.class);
        //System.out.println(userDao);
    }
}