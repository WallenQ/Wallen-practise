package com.wallen.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq工具类
 *
 * @author Wallen
 * @date 2021/10/20 23:12
 */
public class RabbitMQUtils {
    /**
     * 获得一个连接channel
     *
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Channel getChannel() throws IOException, TimeoutException {
        //创建一个连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //工厂IP 连接RabbitMQ的队列
        connectionFactory.setHost("127.0.0.1");
        //用户名
        connectionFactory.setUsername("user1");
        //密码
        connectionFactory.setPassword("123456");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //获取信道
        return connection.createChannel();
    }
}
