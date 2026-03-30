package com.wallen.practise.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * channel应用实例
 */
public class NioFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "hello";
        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.txt");

        //通过fileOutputStream获取对应FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put(str.getBytes());
        byteBuffer.flip();

        //将byteBuffer写入fileChannel
        fileChannel.write(byteBuffer);

        fileOutputStream.close();
    }
}
