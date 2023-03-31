package com.wallen.rabbitmq.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 发布确认
 *
 * @author wallen
 * @date 2023/3/30 17:41
 */
@Configuration
public class ConfirmConfig {
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    public static final String CONFIRM_ROUTING_KEY = "key1";

    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    /**
     * 自定义延迟交换机
     */
    @Bean("confirmExchange")
    public DirectExchange directExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingConfirmQueue(@Qualifier("confirmQueue") Queue confirmQueue,
                                       @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }
}
