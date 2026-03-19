package com.wallen.practise.nio;

import java.nio.IntBuffer;

/**
 * @Author qianwenlong
 * @Date 19/03/2026 08:15
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //举例说明buffer的使用
        //创建一个buffer
        IntBuffer intBuffer = IntBuffer.allocate(5);
        intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);

        //如何从buffer读取数据
        intBuffer.flip();

        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
