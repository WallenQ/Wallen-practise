package com.wallen;

import com.wallen.io.aio.AioClientThread;
import com.wallen.util.InputUtil;

import java.io.IOException;

/**
 * @Author qianwenlong
 * @Date 2026/1/12 14:14
 */
public class EchoClient {
    public static void main(String[] args) throws IOException {
        //BIO
        //new BioEchoClientHandler();

        //nio
        //try (EchoClientHandler echoClientHandler = new EchoClientHandler()) {
        //} catch (Exception e) {
        //    e.printStackTrace();
        //}

        //aio
        AioClientThread client = new AioClientThread();
        new Thread(client).start();
        while (client.sendMessage(InputUtil.getString("请输入要发送的信息："))) {

        }
    }

}
