package com.wallen.test.spring.spring6.ioc.xml.bean;

/**
 * @author Wallen
 * @date 2024/10/16 16:02
 */
public class PersonDaoImpl implements UserDao {
    @Override
    public void run() {
        System.out.println("PersonDaoImpl run......");
    }
}
