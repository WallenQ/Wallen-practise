package com.wallen.practise.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author qianwenlong
 * @Date 12/03/2026 07:35
 */
public class BioServer {
    public static void main(String[] args) throws IOException {
        //线程池机制
        //创建线程池
        //如果有客户端连接，创建线程与之通讯

        ExecutorService executorService = Executors.newCachedThreadPool();

        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");

        while (true) {
            System.out.println("主线程信息：" + Thread.currentThread().getId() + ", 主线程名称：" + Thread.currentThread().getName());
            //监听，等待客户端连接
            System.out.println("等待连接。。。");
            final Socket accept = serverSocket.accept();
            System.out.println("连接到一个客户端");
            //创建一个线程，与之通讯
            executorService.execute(() -> {
                handler(accept);
            });
        }
    }

    public static void handler(Socket socket) {
        byte[] bytes = new byte[1024];
        //通过socket获取输入流
        try {
            System.out.println("线程信息：" + Thread.currentThread().getId() + ", 线程名称：" + Thread.currentThread().getName());
            InputStream inputStream = socket.getInputStream();
            //循环读取客户端发送的数据
            while (true) {
                System.out.println("线程信息：" + Thread.currentThread().getId() + ", 线程名称：" + Thread.currentThread().getName());

                System.out.println("read。。。");
                int read = inputStream.read(bytes);
                if (read != -1) {
                    //输出客户端发送的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("关闭连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
