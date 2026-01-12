package com.wallen.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 1.实现客户端连接回调的处理操作
 *
 * @Author qianwenlong
 * @Date 2026/1/12 14:27
 */
public class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AioServerThread> {

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
