package com.wallen;

import com.wallen.io.aio.AioServerThread;

import java.io.IOException;

/**
 * @Author qianwenlong
 * @Date 2026/1/12 14:14
 */
public class EchoServer {
    public static void main(String[] args) throws IOException {
        //BIO
        //new BioEchoServerHandler();

        //nio
        //new EchoServerHandler();

        //aio
        new Thread(new AioServerThread()).start();
    }
}
