package com.wallen.rabbitmq.utils;

/**
 * 睡眠工具类
 *
 * @author: Wallen
 * @date: 2022/5/11 20:28
 */
public class SleepUtils {
    public static void sleep(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
