package com.wallen.practise.spring.test.factory;

import com.wallen.practise.spring.test.service.UserService;
import com.wallen.practise.spring.test.service.impl.UserServiceImpl;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author Wallen
 * @date 2025/1/24 13:39
 */
public class BeanFactory3 implements FactoryBean<UserService> {

    public UserService getUserService(){
        return new UserServiceImpl();
    }

    @Override
    public UserService getObject() throws Exception {
        return new UserServiceImpl();
    }

    @Override
    public Class<?> getObjectType() {
        return UserService.class;
    }
}
