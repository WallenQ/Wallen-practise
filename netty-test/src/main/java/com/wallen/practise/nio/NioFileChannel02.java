package com.wallen.practise.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 使用nio读取文件
 *
 * @Author qianwenlong
 * @Date 01/04/2026 09:09
 */
public class NioFileChannel02 {
    public static void main(String[] args) throws IOException {
        //创建文件输入流
        File file = new File("d:\\file01.txt");

        FileInputStream fileInputStream = new FileInputStream(file);

        FileChannel channel = fileInputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate((int) file.length());

        //将channel的内容读取到buffer
        channel.read(buffer);

        System.out.println(new String(buffer.array()));

        fileInputStream.close();


    }
}
