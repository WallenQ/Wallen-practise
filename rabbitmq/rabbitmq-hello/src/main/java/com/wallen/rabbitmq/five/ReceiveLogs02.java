package com.wallen.rabbitmq.five;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wallen.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author wallen
 * @date 2023/3/19 13:53
 */
public class ReceiveLogs02 {
    //交换机名称
    static String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        //声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        //声明一个临时队列
        /*
         * 生成一个临时队列，队列名称随机
         * 当消费者断开与队列的连接时，队列就自动删除
         */
        String queueName = channel.queueDeclare().getQueue();
        /*
         * 绑定交换机与队列
         */
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println("等待接收消息，把接收到的消息打印在屏幕上。。。");

        //接收消息
        //消费者取消消息时的回调接口
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("ReceiveLogs02控制台打印接收到的消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
        };
        channel.basicConsume(queueName, true, deliverCallback, (consumerTag, message) -> {
        });
    }
}
