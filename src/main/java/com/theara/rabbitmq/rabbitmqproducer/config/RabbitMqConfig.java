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

    public static final String ROUTING_KEY = "my.queue.key";
    public static final String ERROR_ROUTING_KEY = "server1.app1.module1.error";

    @Bean
    public Queue queue() {
        return new Queue(ROUTING_KEY, true);
    }

    @Bean("errorQueue")
    public Queue errorQueue() {
        return new Queue(ERROR_ROUTING_KEY, true);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("my_queue_exchange");
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ROUTING_KEY);
    }

    @Bean Binding errorBinding(@Qualifier("errorQueue") Queue queue, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue).to(topicExchange).with(ERROR_ROUTING_KEY);
    }

}
