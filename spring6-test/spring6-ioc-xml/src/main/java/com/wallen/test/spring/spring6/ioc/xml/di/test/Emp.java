package com.wallen.test.spring.spring6.ioc.xml.di.test;

import lombok.Data;

import java.util.Arrays;

/**
 * 员工
 *
 * @author Wallen
 * @date 2024/10/21 14:14
 */
@Data
public class Emp {
    private Dept    dept;
    private String  name;
    private Integer age;
    private String[] loves;

    public void work() {
        System.out.println(name + " working..." + age);
        this.dept.info();
        if (loves != null) {
            System.out.println(Arrays.toString(loves));
        }
    }
}
