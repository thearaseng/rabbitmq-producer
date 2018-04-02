package com.theara.rabbitmq.rabbitmqproducer.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Theara Seng
 * created on Mar 30, 2018
 */

@Configuration
public class RabbitMqConfig {

    public static final String TOPIC_EXCHANGE_KEY = "my_queue_exchange";
    public static final String INFO_ROUTING_KEY = "server1.app1.module1.info";
    public static final String ERROR_ROUTING_KEY = "server1.app1.module1.error";

    @Bean("infoQueue")
    public Queue queue() {
        return new Queue(INFO_ROUTING_KEY, true);
    }

    @Bean("errorQueue")
    public Queue errorQueue() {
        return new Queue(ERROR_ROUTING_KEY, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_KEY);
    }

    @Bean
    public Binding binding(@Qualifier("infoQueue") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(INFO_ROUTING_KEY);
    }

    @Bean
    public Binding errorBinding(@Qualifier("errorQueue") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ERROR_ROUTING_KEY);
    }

}
