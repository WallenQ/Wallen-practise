package com.wallen.test.spring.spring6.ioc.xml.di.map;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author Wallen
 * @date 2024/10/23 14:19
 */
@Data
public class Student {
    private String               id;
    private String               name;
    private Map<String, Teacher> teacherMap;
    private List<Lesson>         lessonList;

    public void run() {
        System.out.println("学生编号：" + id + ",学生姓名" + name);
        System.out.println(teacherMap);
        System.out.println(lessonList);
    }
}
