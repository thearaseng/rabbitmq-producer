package com.theara.rabbitmq.rabbitmqproducer.producer;

import com.theara.rabbitmq.rabbitmqproducer.config.RabbitMqConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Theara Seng
 * created on Mar 30, 2018
 */

@Component
public class MessageProducer {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {

        System.out.println(new Date());
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE_KEY, RabbitMqConfig.INFO_ROUTING_KEY, message);
        System.out.println("Is listener returned ::: " + rabbitTemplate.isReturnListener());
        System.out.println(new Date());

    }

    public void sendErrorMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE_KEY, RabbitMqConfig.ERROR_ROUTING_KEY, message);
    }

}
