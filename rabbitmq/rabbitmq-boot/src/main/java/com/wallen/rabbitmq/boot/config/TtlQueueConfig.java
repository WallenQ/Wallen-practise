package com.wallen.rabbitmq.boot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wallen
 * @date 2023/3/28 11:34
 */
@Configuration
public class TtlQueueConfig {
    public static final String X_EXCHANGE = "X";
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";
    public static final String QUEUE_C = "QC";

    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    public static final String DEAD_LETTER_QUEUE = "QD";

    /**
     * 声明xExchange
     *
     * @return
     */
    @Bean("xExchange")
    public DirectExchange xExchange() {
        return new DirectExchange(X_EXCHANGE);
    }

    /**
     * 声明yExchange
     *
     * @return
     */
    @Bean("yExchange")
    public DirectExchange yExchange() {
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明队列A ttl为10s，并绑定到对应的死信交换机
     *
     * @return
     */
    @Bean("queueA")
    public Queue queueA() {
        Map<String, Object> args = new HashMap<>();
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        //声明当前队列的死信路由
        args.put("x-dead-letter-routing-key", "YD");
        //声明队列的TTL
        args.put("x-message-ttl", 10_000L);
        return QueueBuilder.durable(QUEUE_A).withArguments(args).build();
    }
    /**
     * 声明队列B绑定X交换机
     *
     * @param queueA
     * @param xExchange
     * @return
     */
    @Bean
    public Binding queueaBindingX(@Qualifier("queueA") Queue queueA, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueA).to(xExchange).with("XA");
    }
    /**
     * 声明队列A ttl为10s，并绑定到对应的死信交换机
     *
     * @return
     */
    @Bean("queueB")
    public Queue queueB() {
        Map<String, Object> args = new HashMap<>();
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        //声明当前队列的死信路由
        args.put("x-dead-letter-routing-key", "YD");
        //声明队列的TTL
        args.put("x-message-ttl", 40_000L);
        return QueueBuilder.durable(QUEUE_B).withArguments(args).build();
    }

    /**
     * 声明队列B绑定X交换机
     *
     * @param queueB
     * @param xExchange
     * @return
     */
    @Bean
    public Binding queuebBindingX(@Qualifier("queueB") Queue queueB, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueB).to(xExchange).with("XB");
    }

    /**
     * 声明队列C，并绑定到对应的死信交换机
     *
     * @return
     */
    @Bean("queueC")
    public Queue queueC() {
        Map<String, Object> args = new HashMap<>();
        //声明当前队列绑定的死信交换机
        args.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        //声明当前队列的死信路由
        args.put("x-dead-letter-routing-key", "YD");
        return QueueBuilder.durable(QUEUE_C).withArguments(args).build();
    }

    /**
     * 声明队列C绑定X交换机
     *
     * @param queueC
     * @param xExchange
     * @return
     */
    @Bean
    public Binding queueBindingX(@Qualifier("queueC") Queue queueC, @Qualifier("xExchange") DirectExchange xExchange) {
        return BindingBuilder.bind(queueC).to(xExchange).with("XC");
    }

    /**
     * 声明死信队列QD
     *
     * @return
     */
    @Bean("queueD")
    public Queue queueD() {
        return new Queue(DEAD_LETTER_QUEUE);
    }
    /**
     * 声明死信队列
     *
     * @param queueD
     * @param yExchange
     * @return
     */
    @Bean
    public Binding deadLetterBindingQAD(@Qualifier("queueD") Queue queueD, @Qualifier("yExchange") DirectExchange yExchange) {
        return BindingBuilder.bind(queueD).to(yExchange).with("YD");
    }
}
