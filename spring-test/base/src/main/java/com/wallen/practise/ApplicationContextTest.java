package com.wallen.practise;

import com.wallen.practise.spring.test.service.impl.UserServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Wallen
 * @date 2025/1/16 17:38
 */
public class ApplicationContextTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext     = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl        userService = applicationContext.getBean("userService", UserServiceImpl.class);
        userService.findAll();
        //System.out.println(userService);
        //applicationContext.close();
        //UserService        userService = applicationContext.getBean("userService3", UserService.class);
        //System.out.println(userService);
        /*OtherBean bean = applicationContext.getBean(OtherBean.class);
        System.out.println(bean);*/
    }
}
