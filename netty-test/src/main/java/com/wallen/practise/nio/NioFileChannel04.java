package com.wallen.practise.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 文件拷贝
 */
public class NioFileChannel04 {
    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/tangqiu/Downloads/1.jpeg");

        FileOutputStream fileOutputStream = new FileOutputStream("/Users/tangqiu/Downloads/2.jpeg");

        FileChannel inputStreamChannel = fileInputStream.getChannel();

        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        outputStreamChannel.transferFrom(inputStreamChannel, 0, inputStreamChannel.size());

        fileInputStream.close();
        fileOutputStream.close();
        inputStreamChannel.close();
        outputStreamChannel.close();
    }
}
