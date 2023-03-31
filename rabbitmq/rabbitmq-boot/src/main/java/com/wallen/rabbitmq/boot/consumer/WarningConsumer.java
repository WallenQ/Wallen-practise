package com.wallen.rabbitmq.boot.consumer;

import com.wallen.rabbitmq.boot.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wallen
 * @date 2023/3/31 16:22
 */
@Slf4j
@Component
public class WarningConsumer {
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE_NAME)
    public void receiveWarningMsg(Message message) {
        log.error("报警发现不可路由消息：{}", new String(message.getBody()));
    }
}
