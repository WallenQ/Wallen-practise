package com.wallen.rabbitmq.nine;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.wallen.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 生产者-优先级队列
 *
 * @author Wallen
 * @date 2021/10/18 18:04
 */
public class Producer {
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        //channel.exchangeDeclare("", BuiltinExchangeType.DIRECT);

        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().priority(5).build();

        //该信息是用作演示队列个数限制
        for (int i = 0; i < 11; i++) {
            String message = "info" + i;
            if (i == 5) {
                channel.basicPublish("", QUEUE_NAME, properties, message.getBytes(StandardCharsets.UTF_8));
            }else {
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            }
            System.out.println("生产者发送消息：" + message);
        }

    }
}
