package com.wallen.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author wallen
 * @date 2023/8/27 18:34
 */
public class DogFactoryBean implements FactoryBean<Dog> {
    @Override
    public Dog getObject() throws Exception {
        return new Dog();
    }

    @Override
    public Class<?> getObjectType() {
        return Dog.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }
}
