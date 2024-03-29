package com.wallen.rabbitmq.boot.consumer;

import com.wallen.rabbitmq.boot.config.DelayedQueueConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author wallen
 * @date 2023/3/29 19:42
 */
@Slf4j
@Component
public class DelayQueueConsumer {
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayedQueue(Message message) {
        String msg = new String(message.getBody());

        log.info("当前时间:{},收到延时队列消息:{}", new Date(), msg);

    }

}
