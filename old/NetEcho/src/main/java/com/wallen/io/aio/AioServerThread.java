package com.wallen.io.aio;

import com.wallen.common.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @Author qianwenlong
 * @Date 2025/12/31 15:51
 */

/**
 * IO处理线程类
 */
public class AioServerThread implements Runnable {
    private final AsynchronousServerSocketChannel serverChannel;
    //线程等待操作
    private final CountDownLatch latch;

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
