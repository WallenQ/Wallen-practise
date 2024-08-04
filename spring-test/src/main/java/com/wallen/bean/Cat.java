package com.wallen.bean;

import org.springframework.stereotype.Component;

/**
 * @author wallen
 * @date 2023/8/19 15:42
 */
@Component("tom")
public class Cat {
    int age;
    public Cat (){

    }

    public Cat (int age){
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                '}';
    }
}
