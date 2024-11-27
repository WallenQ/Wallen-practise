package com.wallen.test.spring6.aop.example;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @author Wallen
 * @date 2024/11/27 16:59
 */
public class ProxyFactory {
    /**
     * 目标对象
     */
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /**
     * 返回代理对象
     *
     * @return
     */
    public Object getProxy() {
        /*
         * ClassLoader loader       加载动态生成代理类的加载器
         * Class<?>[] interfaces    目标对象实现的所有接口的class类型数组
         * InvocationHandler invocationHandler      设置代理对象实现目标对象方法的过程
         */
        ClassLoader loader     = target.getClass().getClassLoader();
        Class<?>[]  interfaces = target.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             * @param proxy 代理对象
             * @param method 需要重写的目标对象的方法
             * @param args method中的参数
             *
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //方法调用之前输出
                System.out.println("[动态代理][日志] " + method.getName() + " 方法开始了， 参数是：" + Arrays.toString(args));
                //调用目标的方法
                Object invoke = method.invoke(target, args);
                //方法调用滞后输出
                System.out.println("[动态代理][日志] " + method.getName() + " 方法结束， 结果是：" + invoke);
                return invoke;
            }
        };
        return Proxy.newProxyInstance(loader, interfaces, invocationHandler);
    }
}
