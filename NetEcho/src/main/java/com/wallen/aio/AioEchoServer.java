package com.wallen.aio;

import com.wallen.common.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * @Author qianwenlong
 * @Date 2025/12/31 15:51
 */

/**
 * 2.实现客户端回应处理操作
 */
class EchoHandler implements CompletionHandler<Integer, ByteBuffer> {
    private Boolean exit = false;
    private AsynchronousSocketChannel clientChannel;

    public EchoHandler(AsynchronousSocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        String readMessage = new String(buffer.array(), 0, buffer.remaining()).trim();
        System.out.println("【服务端接收到消息内容】" + readMessage);
        String resultMessage = "【ECHO】" + readMessage;
        if ("exit".equalsIgnoreCase(readMessage)) {
            resultMessage = "【EXIT】 Bye Bye...";
            this.exit = true;
        }
    }

    private void echoWrite(String result) {
        ByteBuffer buffer = ByteBuffer.allocate(50);
        buffer.put(result.getBytes());
        this.clientChannel.write(buffer, buffer, new CompletionHandler<Integer, Buffer>() {

            @Override
            public void completed(Integer result, Buffer attachment) {
                //当前有数据
                if (attachment.hasRemaining()) {
                    EchoHandler.this.clientChannel.write(buffer, buffer, this);
                } else {
                    if (EchoHandler.this.exit) {
                        //需要继续交互
                        ByteBuffer readBuffer = ByteBuffer.allocate(50);
                        EchoHandler.this.clientChannel.read(readBuffer, readBuffer, new EchoHandler(EchoHandler.this.clientChannel));
                    }
                }

            }

            @Override
            public void failed(Throwable exc, Buffer attachment) {
                try {
                    EchoHandler.this.clientChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 1.实现客户端连接回调的处理操作
 */
class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServerThread> {

    @Override
    public void completed(AsynchronousSocketChannel result, AioServerThread attachment) {
        //接收连接对象
        attachment.getServerChannel().accept(attachment, this);
        ByteBuffer buffer = ByteBuffer.allocate(50);
        result.read(buffer, buffer, new EchoHandler(result));
    }

    @Override
    public void failed(Throwable exc, AioServerThread attachment) {
        System.out.println("服务端连接失败...");
        //恢复执行
        attachment.getLatch().countDown();
    }
}

/**
 * IO处理线程类
 */
class AioServerThread implements Runnable {
    private AsynchronousServerSocketChannel serverChannel;
    //线程等待操作
    private CountDownLatch latch;

    public AsynchronousServerSocketChannel getServerChannel() {
        return this.serverChannel;
    }

    public CountDownLatch getLatch() {
        return this.latch;
    }

    public AioServerThread() throws IOException {
        //设置线程等待个数
        this.latch = new CountDownLatch(1);
        //打开异步通道
        this.serverChannel = AsynchronousServerSocketChannel.open();
        this.serverChannel.bind(new InetSocketAddress(Constant.PORT));
        System.out.println("服务器启动成功， 在" + Constant.PORT + "端口监听服务...");
    }

    @Override
    public void run() {
        this.serverChannel.accept(this, new AcceptHandler());
        try {
            //进入等待
            this.latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class AioEchoServer {
    public static void main(String[] args) throws IOException {
        new Thread(new AioServerThread()).start();
    }
}
