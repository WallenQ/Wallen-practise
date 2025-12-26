package com.wallen.nio;

import com.wallen.common.Constant;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author qianwenlong
 * @Date 2025/12/26 8:54
 */
public class NioEchoServer {
    public static void main(String[] args) throws IOException {
        new EchoServerHandler();
    }
}


/**
 * 实现一个专门用于客户端请求处理的线程对象
 */
class SocketClientChannelThread implements Runnable {
    private SocketChannel clientChannel;
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

/**
 * 服务端处理类
 */
class EchoServerHandler implements AutoCloseable {
    private ExecutorService executorService;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    //客户端的信息
    private SocketChannel clientChannel;

    public EchoServerHandler() throws IOException {
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
        //保存当前状态
        int keySelect = 0;
        //需要进行连接等待
        while ((keySelect = this.selector.select()) > 0) {
            //获取全部连接通道信息
            Set<SelectionKey> selectionKeys = this.selector.selectedKeys();
            Iterator<SelectionKey> selectionIterator = selectionKeys.iterator();
            while (selectionIterator.hasNext()) {
                //获取每一个通道
                SelectionKey selectionKey = selectionIterator.next();
                //如果通道为接收状态
                if (selectionKey.isAcceptable()) {
                    //等待连接
                    this.clientChannel = this.serverSocketChannel.accept();
                    if (this.clientChannel != null) {
                        //当前有连接
                        this.executorService.submit(new SocketClientChannelThread(this.clientChannel));
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