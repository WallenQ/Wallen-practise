package com.wallen.practise.spring.test.factory;

import com.wallen.practise.spring.test.service.UserService;
import com.wallen.practise.spring.test.service.impl.UserServiceImpl;

/**
 * @author Wallen
 * @date 2025/1/24 13:39
 */
public class BeanFactory1 {

    public static UserService getUserService(){
        return new UserServiceImpl();
    }
}
