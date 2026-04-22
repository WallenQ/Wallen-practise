package com.wallen.practise.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author qianwenlong
 * @Date 19/03/2026 08:15
 */
public class NioServer {
    public static void main(String[] args) throws IOException {
        //创建ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //创建Selector
        Selector selector = Selector.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //serverSocketChannel注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //循环等待客户端连接
        while (true) {
            if (selector.select(1000) == 0) {
                //没有连接事件发生
                System.out.println("Nothing after 1 second...");
                continue;
            }
            //获取相关的selectionKey集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                if (selectionKey.isAcceptable()) {
                    //有新的客户端连接，从 ServerSocketChannel 新接受的连接；
                    //ServerSocketChannel 注册用于接收连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("client connected：" + socketChannel.hashCode() );
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selector
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                } else if (selectionKey.isReadable()) {
                    //SocketChannel 注册用于读写数据
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();
                    channel.read(buffer);
                    System.out.println("from client " + new String(buffer.array()));
                }
                //手动移除selectionKey
                iterator.remove();
            }
        }
    }
}
