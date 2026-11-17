package com.wallen.practise.nettypro.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.StandardCharsets;

/**
 * @Author qianwenlong
 * @Date 2026/11/17 8:40
 */
public class NettyByteBuf02 {
    public static void main(String[] args) {
        //创建ByteBuf
        ByteBuf byteBuf = Unpooled.copiedBuffer("Hello World!", StandardCharsets.UTF_8);

        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();

            System.out.println(new String(array,0, byteBuf.readableBytes(), StandardCharsets.UTF_8));

            System.out.println("byteBuf=" + byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());
            System.out.println(byteBuf.readableBytes());

            System.out.println(byteBuf.getCharSequence(0,5,StandardCharsets.UTF_8));
            System.out.println(byteBuf.getCharSequence(6,6,StandardCharsets.UTF_8));
        }
    }
}
