package com.wallen.io.aio;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 2.实现客户端回应处理操作
 *
 * @Author qianwenlong
 * @Date 2026/1/12 14:27
 */
public class EchoHandler  implements CompletionHandler<Integer, ByteBuffer> {
    private Boolean exit = false;
    private final AsynchronousSocketChannel clientChannel;

    public EchoHandler(AsynchronousSocketChannel clientChannel) {
        this.clientChannel = clientChannel;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        buffer.flip();
        String readMessage = new String(buffer.array(), 0, buffer.remaining()).trim();
        System.out.println("【服务端接收到消息内容】" + readMessage + "\n");
        String resultMessage = "【ECHO】" + readMessage;
        if ("exit".equalsIgnoreCase(readMessage)) {
            resultMessage = "【EXIT】 Bye Bye...\n";
            this.exit = true;
        }
        this.echoWrite(resultMessage);
    }

    private void echoWrite(String result) {
        ByteBuffer buffer = ByteBuffer.allocate(50);
        buffer.put(result.getBytes());
        this.clientChannel.write(buffer, buffer, new CompletionHandler<Integer, Buffer>() {

            @Override
            public void completed(Integer result, Buffer attachment) {
                //当前有数据
                if (attachment.hasRemaining()) {
                    EchoHandler.this.clientChannel.write(buffer, buffer, this);
                } else {
                    if (!EchoHandler.this.exit) {
                        //需要继续交互
                        ByteBuffer readBuffer = ByteBuffer.allocate(50);
                        EchoHandler.this.clientChannel.read(readBuffer, readBuffer, new EchoHandler(EchoHandler.this.clientChannel));
                    }
                }
            }

            @Override
            public void failed(Throwable exc, Buffer attachment) {
                try {
                    EchoHandler.this.clientChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        try {
            this.clientChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
