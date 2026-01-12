package com.wallen.io.nio;

import com.wallen.common.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端处理类
 *
 * @Author qianwenlong
 * @Date 2026/1/12 14:21
 */
public class NioEchoServerHandler implements AutoCloseable {
    private final ExecutorService executorService;
    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;

    public NioEchoServerHandler() throws IOException {
        this.executorService = Executors.newFixedThreadPool(5);
        //打开服务端连接通道
        this.serverSocketChannel = ServerSocketChannel.open();
        //设置为非阻塞
        this.serverSocketChannel.configureBlocking(false);
        this.serverSocketChannel.bind(new InetSocketAddress(Constant.PORT));
        //NIO的Reactor模型重点在于所有的Channel需要向Selector中进行注册
        //获取Selector实例
        this.selector = Selector.open();
        //服务端需要进行接收
        this.serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端程序启动，在" + Constant.PORT + "端口上进行监听...");
        this.clientHandler();
    }

    private void clientHandler() throws IOException {
        //需要进行连接等待
        while (this.selector.select() > 0) {
            //获取全部连接通道信息
            Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
            Iterator<SelectionKey> selectionIterator = selectionKeys.iterator();
            while (selectionIterator.hasNext()) {
                //获取每一个通道
                SelectionKey selectionKey = selectionIterator.next();
                //如果通道为接收状态
                if (selectionKey.isAcceptable()) {
                    //等待连接
                    //客户端的信息
                    SocketChannel clientChannel = this.serverSocketChannel.accept();
                    if (clientChannel != null) {
                        //当前有连接
                        this.executorService.submit(new SocketClientChannelThread(clientChannel));
                    }
                }
                //移除通道
                selectionIterator.remove();
            }
        }
    }

    @Override
    public void close() throws Exception {
        //关闭线程池
        this.executorService.shutdown();
        //关闭服务端
        this.serverSocketChannel.close();
    }
}

