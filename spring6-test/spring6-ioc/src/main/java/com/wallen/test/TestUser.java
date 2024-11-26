package com.wallen.test;

import com.wallen.test.spring6.ioc.bean.AnnotationApplicationContext;
import com.wallen.test.spring6.ioc.service.UserService;

/**
 * @author Wallen
 * @date 2024/11/22 11:58
 */
public class TestUser {
    public static void main(String[] args) throws Exception {
        AnnotationApplicationContext context     = new AnnotationApplicationContext("com.wallen.test");
        UserService                  userService = (UserService) context.getBean(UserService.class);
        System.out.println(userService);
        userService.add();
    }
}