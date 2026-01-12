package com.wallen.io.aio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Author qianwenlong
 * @Date 2026/1/12 14:24
 */
public class ClientWriteHandler  implements CompletionHandler<Integer, ByteBuffer> {
    private final AsynchronousSocketChannel clientChannel;
    private final CountDownLatch latch;

    public ClientWriteHandler(AsynchronousSocketChannel clientChannel, CountDownLatch latch) {
        this.clientChannel = clientChannel;
        this.latch = latch;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        if (buffer.hasRemaining()) {
            this.clientChannel.write(buffer, buffer, null);
        } else {
            ByteBuffer readBuffer = ByteBuffer.allocate(50);
            this.clientChannel.read(readBuffer, readBuffer, new ClientReadHandler(this.clientChannel, this.latch));
        }
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
