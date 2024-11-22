package com.wallen.test.spring6;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Wallen
 * @date 2024/10/6 04:31
 */
public class TestUser {
    private final Logger logger = LoggerFactory.getLogger(TestUser.class);

    @Test
    public void testUser() {
        //加载spring配置文件，对象创建
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        //获取创建的对象
        User user = (User) context.getBean("user");
        System.out.println(user);

        //使用对象调用方法进行测试
        user.add();

        logger.info("执行调用成功了！");
    }

    @Test
    public void testUserObject1() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        //获取类对象
        Class<?> clazz = Class.forName("com.wallen.test.spring6.User");

        //调用方法创建对象
        User user = (User) clazz.getDeclaredConstructor().newInstance();
        System.out.println(user);
    }
}
