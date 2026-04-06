package com.wallen.practise.nio;

import java.nio.ByteBuffer;

/**
 * 写入类型和读出类型要保持一致，不然会报错
 */
public class NioByteBufferPutGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(100);
        buffer.putLong(9L);
        buffer.putChar('a');
        buffer.putShort((short) 4);

        buffer.flip();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getLong());
    }
}
