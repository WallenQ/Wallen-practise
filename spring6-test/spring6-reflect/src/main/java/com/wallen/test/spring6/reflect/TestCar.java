package com.wallen.test.spring6.reflect;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Wallen
 * @date 2024/11/21 17:27
 */
public class TestCar {
    //1.获取Class对象多种方式
    @Test
    public void test01() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        //1.类名.class
        Class<Car> clazz1 = Car.class;
        //2.对象.getClass()
        Class<? extends Car> clazz2 = new Car().getClass();
        //3.Class.forName("全路径")
        Class<?> clazz3 = Class.forName("com.wallen.test.spring6.reflect.Car");

        //实例化
        Car car = (Car) clazz3.getDeclaredConstructor().newInstance();
        System.out.println(car);
    }

    //2.获取构造方法
    @Test
    public void test02() throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Class<Car> clazz = Car.class;
        //getConstructors获取所有public的构造方法
        //Constructor<?>[] constructors = clazz.getConstructors();
        //getDeclaredConstructors获取所有的构造方法
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("方法名称：" + constructor.getName() + "，参数个数：" + constructor.getParameterCount());
        }

        //指定有参构造创建对象
        //1.构造public
        Constructor<Car> constructor = clazz.getConstructor(String.class, Integer.class, String.class);
        Car              car1        = constructor.newInstance("aa", 10, "red");
        System.out.println(car1);
        //2.构造private
        Constructor<Car> declaredConstructor = clazz.getDeclaredConstructor(String.class, Integer.class);
        declaredConstructor.setAccessible(true);
        Car car2 = declaredConstructor.newInstance("bb", 20);
        System.out.println(car2);
    }

    //3.获取属性
    @Test
    public void test03() throws NoSuchMethodException, InvocationTargetException,
            InstantiationException, IllegalAccessException {
        Class<Car> clazz = Car.class;
        Car        car   = clazz.getDeclaredConstructor().newInstance();
        //获取所有public属性
        Field[] publicFields = clazz.getFields();
        //获取所有属性
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals("name")) {
                //设置运行访问
                field.setAccessible(true);
                field.set(car, "五菱宏光");
            }
            System.out.println(field.getName());
            System.out.println(car);
        }
    }

    //4.获取方法
    @Test
    public void test04() throws InvocationTargetException, IllegalAccessException {
        Car                  car   = new Car("奔驰", 10, "black");
        Class<? extends Car> clazz = car.getClass();
        //1.public方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            //System.out.println(method.getName());
            if (method.getName().equals("toString")) {
                String invoke = (String) method.invoke(car);
                System.out.println("toString执行了：" + invoke);
            }
        }
        //2.private方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (method.getName().equals("run")) {
                method.setAccessible(true);
                method.invoke(car);
            }
        }
    }
}
