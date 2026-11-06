package com.wallen.practise.nettypro.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @Author qianwenlong
 * @Date 2026/11/6 15:39
 */
public class TestServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //加入处理器
        ChannelPipeline pipeline = socketChannel.pipeline();

        //加入netty提供的httpServerCodec 编解码器
        pipeline.addLast("MyHttpServerCodec", new HttpServerCodec());
        //增加自定义处理器
        pipeline.addLast("MyTestHttpServerHandler", new TestHttpServerHandler());
    }
}
