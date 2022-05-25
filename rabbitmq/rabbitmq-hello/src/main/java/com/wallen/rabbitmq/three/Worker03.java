package com.wallen.rabbitmq.three;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.wallen.rabbitmq.utils.RabbitMQUtils;
import com.wallen.rabbitmq.utils.SleepUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * 应答模式消费者
 *
 * @author: Wallen
 * @date: 2022/5/11 20:05
 */
public class Worker03 {
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "ask_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        System.out.println("C3等待接受消息处理时间较短");

        //声明接收消息
        DeliverCallback deliverCallback = (consumerTag, message) -> {
            //睡1秒
            SleepUtils.sleep(1);
            System.out.println("接收到的消息：" + new String(message.getBody(), StandardCharsets.UTF_8));
            //手动应答
            channel.basicAck(message.getEnvelope().getDeliveryTag(),false);
        };
        //取消消息时回调
        CancelCallback cancelCallback = consumerTag -> {
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");
        };

        //不公平分发
        channel.basicQos(2);

        /*
         * 消费消息
         * 1.队列名称
         * 2.消费成功后是否要自动应答
         * 3.未成功消费的回调
         * 4.取消消费的回调
         */
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
    }
}
