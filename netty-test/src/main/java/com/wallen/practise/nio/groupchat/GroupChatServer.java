package com.wallen.practise.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author qianwenlong
 * @Date 2026/5/12 8:03
 */
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;

    public GroupChatServer() {
        try {
            selector = Selector.open();

            listenChannel = ServerSocketChannel.open();

            listenChannel.socket().bind(new InetSocketAddress(PORT));

            listenChannel.configureBlocking(false);

            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 监听
     */
    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    //有事件要处理

                    //遍历得到selectionKey集合
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        //取出selectionkey
                        SelectionKey key = iterator.next();

                        //监听到accept
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            //注册到selector
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + " online");
                        } else if (key.isReadable()) {
                            //通道可读状态
                            readData(key);
                        }
                        //手动移除selectionKey
                        iterator.remove();
                    }
                } else {
                    System.out.println("Waiting...");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    /**
     * 读取客户端消息
     *
     * @param key
     */
    private void readData(SelectionKey key) {
        //定义socketChannel
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            int count = channel.read(buffer);

            //根据count值做处理
            if (count > 0) {
                //把缓冲区数据转换成字符串
                String msg = new String(buffer.array());
                System.out.println("From Client: " + msg);
                //向其他客户端转发消息
                sendInfoToOtherClients(msg, channel);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                System.out.println(channel.getRemoteAddress() + " offline");
                //取消注册
                key.channel();
                //关闭通道
                channel.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * 转发消息给其他客户端
     *
     * @param msg
     */
    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("Server forwarding message...");
        //遍历所有注册到selector的socketchannel
        for (SelectionKey key : selector.keys()) {
            Channel targetChannel = key.channel();

            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                //存入buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer写入通道
                dest.write(buffer);
            }
        }
    }

    public static void main(String[] args) {

    }
}
