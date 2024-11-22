package com.wallen.test.spring6.ioc.bean;

/**
 * @author Wallen
 * @date 2024/11/22 14:30
 */
public interface ApplicationContext {
    Object getBean(Class clazz);
}
