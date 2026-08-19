package com.cheshun.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:cheshunmq.properties")
@RabbitListener(queues = "${com.cheshun.thirdhqueue}", containerFactory = "rabbitListenerContainerFactory")
public class ThirdConsumer {

    @RabbitHandler
    public void process (String msg) {
        //
        System.out.println("Third queue received msg: "  + msg);
    }
}
