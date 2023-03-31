package com.wallen.rabbitmq.nine;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wallen.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * 消费者-优先级队列
 *
 * @author Wallen
 * @date 2021/10/18 18:04
 */
public class Consumer {
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        //获取信道
        Channel channel = RabbitMQUtils.getChannel();

        //设置队列的最大优先级，最大可设置255，推荐1-10，设置太高比较吃内存和CPU
        Map<String, Object> params = new HashMap<>();
        params.put("x-max-priority", 10);

        channel.queueDeclare(QUEUE_NAME,true,false,false,params);

        //声明接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            System.out.println("接收到消息：" + new String(message.getBody()));
        };

        /*
         * 消费消息
         * 1.队列名称
         * 2.消费成功后是否要自动应答
         * 3.未成功消费的回调
         * 4.取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {
            System.out.println("消息消费被中断");
        });

    }
}
