package com.wallen.io.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 实现一个专门用于客户端请求处理的线程对象
 *
 * @Author qianwenlong
 * @Date 2025/12/26 8:54
 */
class SocketClientChannelThread implements Runnable {
    private final SocketChannel clientChannel;
    private boolean flag = true;

    public SocketClientChannelThread(SocketChannel clientChannel) {
        this.clientChannel = clientChannel;
        System.out.println("服务端连接成功，可以进行数据交互操作...");
    }

    @Override
    public void run() {
        //NIO是基于Buffer缓冲操作实现的功能，需要将输入的内容保存在缓存中
        //开辟缓存空间
        ByteBuffer buffer = ByteBuffer.allocate(50);
        try {
            while (flag) {
                //清空缓存空间
                buffer.clear();

                int readCount = this.clientChannel.read(buffer);
                //将缓冲区之中保存的内容转为字节数组进行存储
                String readMessage = new String(buffer.array(), 0, readCount).trim();
                System.out.println("【服务端接收消息】 " + readMessage);
                String writeMessage = "【ECHO】" + readMessage + "\n";
                if ("exit".equalsIgnoreCase(readMessage)) {
                    writeMessage = "【ECHO】 Bye Bye...";
                    this.flag = false;
                }
                buffer.clear();
                //保存响应信息
                buffer.put(writeMessage.getBytes());
                //重置缓冲区
                buffer.flip();
                this.clientChannel.write(buffer);
            }
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
