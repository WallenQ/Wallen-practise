package com.wallen.test.spring6.reflect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Wallen
 * @date 2024/11/21 17:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    private String  name;
    private Integer age;
    private String  color;

    private Car(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.color = "blue ";
    }

    private void run() {
        System.out.println("私有方法run执行。。。");
    }
}
