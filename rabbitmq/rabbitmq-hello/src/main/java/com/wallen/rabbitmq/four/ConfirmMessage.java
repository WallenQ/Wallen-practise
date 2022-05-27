package com.wallen.rabbitmq.four;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.wallen.rabbitmq.utils.RabbitMQUtils;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

/**
 * 发布确认
 *
 * @author wallen
 * @date 2022/5/26 14:12
 */
public class ConfirmMessage {
    /**
     * 批量发消息个数
     */
    public static final int MESSAGE_COUNT = 1000;

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {

        //单个确认
        //发布1000个单独确认消息，耗时：301
        ConfirmMessage.publishMessageIndividually();

        //批量确认
        //发布1000个批量确认消息，耗时：98
        ConfirmMessage.publishMessageBatch();

        //异步确认
        //发布1000个异步确认消息，耗时：49
        ConfirmMessage.publishMessageAsync();
    }

    /**
     * 单个确认
     *
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public static void publishMessageIndividually() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMQUtils.getChannel();
        //声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long beginTime = System.currentTimeMillis();
        //批量发消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            //发消息
            channel.basicPublish("", queueName, null, message.getBytes());
            //单个消息马上进行发布确认
            boolean flag = channel.waitForConfirms();
            if (flag) {
                System.out.println("消息发布成功" + i);
            }
        }
        //结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个单独确认消息，耗时：" + (endTime - beginTime));
    }

    /**
     * 批量确认
     *
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public static void publishMessageBatch() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMQUtils.getChannel();
        //声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();
        //开始时间
        long beginTime = System.currentTimeMillis();
        //批量确认消息大小
        int batchSize = 100;

        //批量发消息，批量确认
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            //发消息
            channel.basicPublish("", queueName, null, message.getBytes());
            //达到100条的时候，批量确认一次
            if (i % batchSize == 0) {
                //单个消息马上进行发布确认
                boolean flag = channel.waitForConfirms();
                if (flag) {
                    System.out.println("消息发布成功" + i);
                }
            }

        }
        //结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个批量确认消息，耗时：" + (endTime - beginTime));
    }

    /**
     * 异步发布确认
     *
     * @throws IOException
     * @throws TimeoutException
     * @throws InterruptedException
     */
    public static void publishMessageAsync() throws IOException, TimeoutException, InterruptedException {
        Channel channel = RabbitMQUtils.getChannel();
        //声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, false, false, false, null);
        //开启发布确认
        channel.confirmSelect();

        //有序安全哈希表，适应于高并发
        ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();

        ConfirmCallback ackCallback = (deliveryTag, multiple) -> {
            if (multiple) {
                //删除已经确认的消息
                ConcurrentNavigableMap<Long, String> headMap = outstandingConfirms.headMap(deliveryTag);
                headMap.clear();
            } else {
                outstandingConfirms.remove(deliveryTag);
            }
            System.out.println("确认的消息：" + deliveryTag);
        };
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            //打印未确认的消息
            String message = outstandingConfirms.get(deliveryTag);
            System.out.println("未确认的消息是：" + message + "，消息tag：" + deliveryTag);
        };
        //监听器-异步
        channel.addConfirmListener(ackCallback, nackCallback);

        //开始时间
        long beginTime = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            //发消息
            channel.basicPublish("", queueName, null, message.getBytes());
            //记录所有要发送的消息
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        }

        //结束时间
        long endTime = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个异步确认消息，耗时：" + (endTime - beginTime));
    }
}
