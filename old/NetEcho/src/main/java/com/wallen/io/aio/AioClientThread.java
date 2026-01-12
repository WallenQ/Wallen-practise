package com.wallen.io.aio;

import com.wallen.common.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @Author qianwenlong
 * @Date 2026/1/12 14:24
 */
public class AioClientThread implements Runnable {
    private final AsynchronousSocketChannel clientChannel;
    private final CountDownLatch latch;

    public AioClientThread() throws IOException {
        //打开客户端的channel
        this.clientChannel = AsynchronousSocketChannel.open();
        this.clientChannel.connect(new InetSocketAddress(Constant.HOST, Constant.PORT));
        this.latch = new CountDownLatch(1);
    }

    @Override
    public void run() {
        try {
            this.latch.await();
            this.clientChannel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean sendMessage(String msg) {
        ByteBuffer buffer = ByteBuffer.allocate(50);
        buffer.put(msg.getBytes());
        buffer.flip();
        this.clientChannel.write(buffer, buffer, new ClientWriteHandler(this.clientChannel, this.latch));
        return !"exit".equalsIgnoreCase(msg);
    }
}
