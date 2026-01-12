package com.wallen.io.bio;

import com.wallen.common.Constant;

import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class BioEchoServerHandler implements AutoCloseable {
    private final ServerSocket serverSocket;

    public BioEchoServerHandler() throws IOException {
        //服务器端的socket启动
        serverSocket = new ServerSocket(Constant.PORT);
        System.out.println("ECHO服务端已经启动了，该服务再" + Constant.PORT + "端口上监听...");
        this.clientConnect();
    }

    private void clientConnect() throws IOException {
        while (true) {
            //等待客户端连接
            Socket client = this.serverSocket.accept();
            Thread thread = new Thread(() -> {
                try {
                    // 使用BufferedReader替代Scanner，更适用于网络流
                    BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    //服务器输出为客户端输入
                    PrintStream out = new PrintStream(client.getOutputStream());
                    boolean clientFlag = true;
                    while (clientFlag && !client.isClosed() && !Thread.currentThread().isInterrupted()) {
                        // 读取一行输入
                        String inputData = reader.readLine();
                        if (inputData != null) {
                            //信息结束
                            if ("exit".equalsIgnoreCase(inputData)) {
                                //结束内部循环
                                clientFlag = false;
                                out.println("【ECHO】 Bye Bye ...");
                            } else {
                                out.println("【ECHO】 " + inputData);
                            }
                            // 关键：刷新输出流以确保数据发送到客户端
                            out.flush();
                        } else {
                            // 如果读取到null，说明连接已关闭
                            break;
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Client connection error: " + e.getMessage());
                }
            });
            thread.start();
        }
    }

    @Override
    public void close() throws Exception {
        this.serverSocket.close();
    }
}
