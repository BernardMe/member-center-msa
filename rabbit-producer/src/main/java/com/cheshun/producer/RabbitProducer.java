package com.cheshun.producer;

import com.cheshun.entity.Merchant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class RabbitProducer {

    @Autowired
    private String directExchange;
    @Autowired
    private String topicExchange;
    @Autowired
    private String fanoutExchange;

    @Value("cheshun.directRoutingKey")
    private String directRoutingKey;

    @Value("cheshun.key1")
    private String topicRoutingKey1;
    @Value("cheshun.key2")
    private String topicRoutingKey2;

    @Autowired
    AmqpTemplate cheshunTemplate;

    public void send() throws JsonProcessingException {
        Merchant merchant = new Merchant(1001, "a direct msg: 中原镖局", "中原省");

        cheshunTemplate.convertAndSend(directExchange, directRoutingKey, merchant);

        cheshunTemplate.convertAndSend(topicExchange, topicRoutingKey1, "a topic msg: ");

        cheshunTemplate.convertAndSend(fanoutExchange, topicRoutingKey2, "a fanout msg:");

        //发送JSON字符串
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(merchant);
        System.out.println(json);
        cheshunTemplate.convertAndSend(fanoutExchange, "", json);
    }
}
