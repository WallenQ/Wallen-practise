package com.wallen.rabbitmq.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
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
    /**
     * 发布确认
     */
    public static final String CONFIRM_QUEUE_NAME = "confirm_queue";
    public static final String CONFIRM_EXCHANGE_NAME = "confirm_exchange";
    public static final String CONFIRM_ROUTING_KEY = "key1";

    /**
     * 备份
     */
    public static final String BACKUP_QUEUE_NAME = "backup.queue";
    public static final String BACKUP_EXCHANGE_NAME = "backup.exchange";
    public static final String WARNING_QUEUE_NAME = "warning.queue";

    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE_NAME).build();
    }

    /**
     * confirm交换机
     */
    /*@Bean("confirmExchange")
    public DirectExchange directExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE_NAME);
    }*/
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        ExchangeBuilder exchangeBuilder = ExchangeBuilder.directExchange(CONFIRM_EXCHANGE_NAME).durable(true)
                //设置备份交换机
                .withArgument("alternate-exchange", BACKUP_EXCHANGE_NAME);
        return exchangeBuilder.build();
    }

    @Bean
    public Binding bindingConfirmQueue(@Qualifier("confirmQueue") Queue confirmQueue,
                                       @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }

    /**
     * 声明备份交换机
     */
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE_NAME);
    }


    @Bean("warningQueue")
    public Queue warningQueue() {
        return QueueBuilder.durable(WARNING_QUEUE_NAME).build();
    }

    @Bean
    public Binding bindingWarningQueue(@Qualifier("warningQueue") Queue warningQueue,
                                       @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }

    @Bean("backupQueue")
    public Queue backupQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE_NAME).build();
    }

    @Bean
    public Binding bindingBackupQueue(@Qualifier("backupQueue") Queue backupQueue,
                                      @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

}
