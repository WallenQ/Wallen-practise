package com.wallen.aio;

import com.wallen.common.Constant;
import com.wallen.util.InputUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Author qianwenlong
 * @Date 2026/1/4 16:15
 */
class ClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

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

class ClientWriteHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

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

class AioClientThread implements Runnable {
    private AsynchronousSocketChannel clientChannel;
    private CountDownLatch latch;

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

public class AioEchoClient {
    public static void main(String[] args) throws IOException {
        AioClientThread client = new AioClientThread();
        new Thread(client).start();
        while (client.sendMessage(InputUtil.getString("请输入要发送的信息："))) {

        }
    }
}
