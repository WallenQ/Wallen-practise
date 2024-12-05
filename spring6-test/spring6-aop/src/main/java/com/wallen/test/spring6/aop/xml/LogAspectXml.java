package com.wallen.test.spring6.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面
 *
 * @author Wallen
 * @date 2024/11/29 09:30
 */
@Component
public class LogAspectXml {
    //设置切入点和通知类型

    //通知类型：
    // 前置
    public void before(JoinPoint joinPoint) {
        String   name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("Logger-->前置通知，方法名称：" + name + "，参数：" + Arrays.toString(args));
    }

    // 后置
    public void after(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger-->后置通知，方法名称：" + name);
    }

    // 返回
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger-->返回通知，方法名称：" + name + "，返回结果" + result);
    }

    // 异常
    public void afterThrowing(JoinPoint joinPoint, Throwable ex) {
        String name = joinPoint.getSignature().getName();
        System.out.println("Logger-->异常通知，方法名称：" + name + "，异常信息" + ex);
    }

    // 环绕
    public Object around(ProceedingJoinPoint joinPoint) {
        String   name   = joinPoint.getSignature().getName();
        Object[] args   = joinPoint.getArgs();
        Object   result = null;
        try {
            System.out.println("环绕通知==目标方法之前执行");

            //调用目标方法
            result = joinPoint.proceed();

            System.out.println("环绕通知==目标方法之后执行");
        } catch (Throwable e) {
            System.out.println("环绕通知==目标方法出现异常执行");
            throw new RuntimeException(e);
        } finally {
            System.out.println("环绕通知==目标方法执行完毕执行");
        }
        return result;
    }
}
