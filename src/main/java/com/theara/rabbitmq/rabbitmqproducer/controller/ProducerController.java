package com.theara.rabbitmq.rabbitmqproducer.controller;

import com.theara.rabbitmq.rabbitmqproducer.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Theara Seng
 * created on Mar 30, 2018
 */

@RestController
@RequestMapping("/producer")
public class ProducerController {

    private MessageProducer messageProducer;

    @Autowired
    public ProducerController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @GetMapping
    public String produce(@RequestParam String message) {

        messageProducer.sendMessage(message);

        return "Message \"" + message + "\" was sent to RabbitMQ";
    }

}
