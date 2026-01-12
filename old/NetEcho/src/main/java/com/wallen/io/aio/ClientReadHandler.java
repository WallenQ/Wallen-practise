package com.wallen.io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Author qianwenlong
 * @Date 2026/1/4 16:15
 */
class ClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private final AsynchronousSocketChannel clientChannel;
    private final CountDownLatch latch;

    public ClientReadHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        String receiveMessage = new String(buffer.array(), 0, buffer.remaining());
        System.out.println(receiveMessage);
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        try {
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.latch.countDown();
    }
}
