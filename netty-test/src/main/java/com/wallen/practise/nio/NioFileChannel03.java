package com.wallen.practise.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 一个buffer完成文件读取写入
 *
 * @Author qianwenlong
 * @Date 01/04/2026 09:15
 */
public class NioFileChannel03 {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("D:\\codes\\github\\Wallen-practise\\netty-test\\1.txt");
        FileChannel channel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\codes\\github\\Wallen-practise\\netty-test\\2.txt");
        FileChannel channel2 = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(512);
        while (true) {
            buffer.clear();
            int read = channel.read(buffer);
            if (read == -1) {
                break;
            }
            buffer.flip();
            channel2.write(buffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
