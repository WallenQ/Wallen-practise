package com.wallen.test.spring.spring6.ioc.xml.factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author Wallen
 * @date 2024/10/25 17:19
 */
public class MyFactoryBean implements FactoryBean<User>{
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
