package com.wallen.practise.nettypro.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @Author qianwenlong
 * @Date 2026/11/17 8:28
 */
public class NettyByteBuf01 {
    public static void main(String[] args) {

        //创建一个ByteBuf
        ByteBuf buffer = Unpooled.buffer(10);

        for (int i = 0; i < 10; i++) {
            //writerIndex++
            buffer.writeByte(i);
        }

        System.out.println("capacity: " + buffer.capacity());
        for (int i = 0; i < buffer.capacity(); i++) {
            System.out.println(buffer.getByte(i));
            //readerIndex++
            buffer.readByte();
        }
    }
}
