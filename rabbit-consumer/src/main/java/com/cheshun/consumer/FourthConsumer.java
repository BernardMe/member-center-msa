package com.cheshun.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:cheshunmq.properties")
@RabbitListener(queues = "${com.cheshun.fourthqueue}", containerFactory = "rabbitListenerContainerFactory")
public class FourthConsumer {

    @RabbitHandler
    public void process (String msg) {
        //
        System.out.println("Fourth queue received msg: "  + msg);
    }
}
