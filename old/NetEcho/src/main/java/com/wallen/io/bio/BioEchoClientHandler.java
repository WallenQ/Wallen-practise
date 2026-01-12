package com.wallen.io.bio;

import com.wallen.common.Constant;
import com.wallen.util.InputUtil;

import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author qianwenlong
 * @Date 2025/12/25 12:56
 */

public class BioEchoClientHandler implements AutoCloseable {
    private final Socket client;

    public BioEchoClientHandler() throws IOException {
        this.client = new Socket(Constant.HOST, Constant.PORT);
        System.out.println("已经成功连接到服务器，可以进行消息发送");
        this.accessServer();
    }

    /**
     * 数据交互处理
     *
     * @throws IOException
     */
    private void accessServer() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
        PrintStream out = new PrintStream(this.client.getOutputStream());
        boolean flag = true;
        while (flag) {
            String data = InputUtil.getString("请输入要发送的数据信息：");
            out.println(data);
            // 关键：刷新输出流以确保数据发送到服务器
            out.flush();
            if ("exit".equalsIgnoreCase(data)) {
                flag = false;
            }
            // 读取服务器响应
            String response = reader.readLine();
            if (response != null) {
                System.out.println(response);
            }
        }
    }

    @Override
    public void close() throws Exception {
        this.client.close();
    }
}
