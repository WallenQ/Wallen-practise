package com.wallen.practise.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @Author qianwenlong
 * @Date 2026/5/13 19:28
 */
public class GroupChatClient {

    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;

    private Selector selector;
    private SocketChannel socketChannel;

    private String userName;

    public GroupChatClient() throws IOException {
        selector = Selector.open();

        socketChannel.open(new InetSocketAddress(HOST, PORT));

        socketChannel.configureBlocking(false);

        socketChannel.register(selector, SelectionKey.OP_READ);

        userName = socketChannel.getLocalAddress().toString();

        System.out.println(userName + " is ok...");
    }

    /**
     * 发送消息
     *
     * @param info
     */
    public void sendInfo(String info) {
        info = userName + " say: " + info;
        try {
            socketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readInfo() {
        try {
            int count = selector.select();
            if (count > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        sc.read(buffer);
                        System.out.println(new String(buffer.array()));
                    }
                }
            } else {
                System.out.println("no channel ...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
