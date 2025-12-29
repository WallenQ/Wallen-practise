package com.wallen.nio.client;

import com.wallen.common.Constant;
import com.wallen.util.InputUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 进行NIO客户端连接访问
 *
 * @Author qianwenlong
 * @Date 2025/12/29 15:42
 */
public class NioEchoClient {
    public static void main(String[] args) {
        try (EchoClientHandler echoClientHandler = new EchoClientHandler()) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class EchoClientHandler implements AutoCloseable {
    private SocketChannel clientChannel;

    public EchoClientHandler() throws IOException {
        //创建一个客户端连接通道实例
        this.clientChannel = SocketChannel.open();
        //设置要连接的主机信息，包括主机名称及端口号
        this.clientChannel.connect(new InetSocketAddress(Constant.HOST, Constant.PORT));
        this.accessServer();
    }

    /**
     * 访问服务器端
     */
    public void accessServer() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(50);
        boolean flag = true;
        while (flag) {
            //清空缓冲区
            buffer.clear();
            String msg = InputUtil.getString("请输入要发送的内容：");
            //将数据保存在缓冲区中
            buffer.put(msg.getBytes());
            //重置缓冲区
            buffer.flip();
            //发送数据内容
            this.clientChannel.write(buffer);
            //消息发送过去之后，还需要接收处理返回的内容
            buffer.clear();
            //将内容读取到缓冲区中，并返回个数
            int readCount = this.clientChannel.read(buffer);
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, readCount));
            if ("exit".equalsIgnoreCase(msg)) {
                flag = false;
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.clientChannel.close();
    }
}