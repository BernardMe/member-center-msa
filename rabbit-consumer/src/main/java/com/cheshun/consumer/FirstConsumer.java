package com.cheshun.consumer;

import com.cheshun.entity.Merchant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:cheshunmq.properties")
@RabbitListener(queues = "${com.cheshun.firstqueue}", containerFactory = "rabbitListenerContainerFactory")
public class FirstConsumer {

    @RabbitHandler
    public void process (@Payload Merchant merchant) {
        //
        System.out.println("firstqueue received msg: "  + merchant.getName());
    }
}
