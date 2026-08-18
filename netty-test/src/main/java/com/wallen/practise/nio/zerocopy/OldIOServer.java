package com.wallen.practise.nio.zerocopy;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author qianwenlong
 * @Date 2026/7/4 9:48
 */
public class OldIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7001);
        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            try {
                byte[] byteArray = new byte[4096];

                while (true) {
                    int read = dataInputStream.read(byteArray, 0, byteArray.length);
                    if (read != -1) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
