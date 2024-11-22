package com.wallen.test.spring.spring6.ioc.xml.di.map;

import lombok.Data;

/**
 * @author Wallen
 * @date 2024/10/23 15:36
 */
@Data
public class Teacher {
    private String id;
    private String name;

    public void run() {
        System.out.println();
    }

    @Override
    public String toString() {
        return "Teacher{" + "id='" + id + '\'' + ", name='" + name + '\'' + '}';
    }
}
