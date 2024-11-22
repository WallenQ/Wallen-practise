package com.wallen.test.spring.spring6.ioc.xml.di.test;

import lombok.Data;

import java.util.List;

/**
 * 部门
 *
 * @author Wallen
 * @date 2024/10/21 14:13
 */
@Data
public class Dept {
    private List<Emp> empList;
    private String    name;

    public void info() {
        System.out.println("部门名称：" + name);
        for (Emp emp : empList) {
            System.out.println(emp.getName());
        }
    }
}
