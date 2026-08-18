package com.wallen.practise.nio.zerocopy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author qianwenlong
 * @Date 2026/8/18 15:58
 */
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("127.0.0.1", 7001));

        String fileName = "D:\\codes\\github\\Wallen-practise\\netty-test\\111.pcapng";

        FileChannel fileChannel = new FileInputStream(fileName).getChannel();

        long startTime = System.currentTimeMillis();

        //transferTo 方法底层使用零拷贝
        //Linux系统下，只需调用一次，windows系统下，每次最多发送8M，需要分段发送
        long transferCount = fileChannel.transferTo(0, fileChannel.size(), socketChannel);

        System.out.println("发送的总字节数： " + transferCount + ", 耗时： " + (System.currentTimeMillis() - startTime));

        //关闭通道
        fileChannel.close();
        socketChannel.close();
    }
}
