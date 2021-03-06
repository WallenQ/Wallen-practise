package com.wallen.rabbitmq.three;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.wallen.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * 应答模式生产者
 *
 * @author: Wallen
 * @date: 2022/5/11 20:05
 */
public class Task02 {
    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "ask_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMQUtils.getChannel();

        /*
         * 生成一个队列
         * 1.队列名称
         * 2.队列里面的消息是否持久化（磁盘），默认情况消息存储在内存中
         * 3.该队列是否只供一个消费者进行消费，是否进行消息共享，true可以多个消费者消费，false只能一个消费者消费
         * 4.是否自动删除，最后一个消费者断开连接以后，该队列是否自动删除，true自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        //从控制台中接收信息
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String message = scanner.next();
            /*
             * 发送一个消息
             * 1.交换机
             * 2.路由的Key，队列名称
             * 3.其他参数信息：消息持久化(MessageProperties.PERSISTENT_TEXT_PLAIN)
             * 4.发送的消息体
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println("生产者发出消息：" + message);
        }

    }
}
