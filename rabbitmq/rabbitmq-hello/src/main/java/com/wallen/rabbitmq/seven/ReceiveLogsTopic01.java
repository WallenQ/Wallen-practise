package com.wallen.rabbitmq.seven;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wallen.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author wallen
 * @date 2023/3/21 09:30
 */
public class ReceiveLogsTopic01 {
    //交换机名称
    static String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);

        //声明一个临时队列
        /*
         * 生成一个临时队列，队列名称随机
         * 当消费者断开与队列的连接时，队列就自动删除
         */
        String queueName = "Q1";
        channel.queueDeclare(queueName,false,false,false,null);
        /*
         * 绑定交换机与队列
         */
        channel.queueBind(queueName, EXCHANGE_NAME, "*.orange.*");
        System.out.println("等待接收消息，把接收到的消息打印在屏幕上。。。");

        //接收消息
        //消费者取消消息时的回调接口
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("ReceiveLogsTopic01控制台打印接收到的消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
            System.out.println("接收队列：" + queueName + " 路由键：" + message.getEnvelope().getRoutingKey());
        };
        channel.basicConsume(queueName, true, deliverCallback, (consumerTag, message) -> {
        });
    }
}
