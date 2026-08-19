package com.cheshun.config;

import com.cheshun.entity.DirectExchange;
import com.cheshun.entity.FanoutExchange;
import com.cheshun.entity.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@Configurable
@PropertySource("classpath:cheshunmq.properties")
public class RabbitConfig {

    @Value("${com.cheshun.firstqueue}")
    private String firstQueue;

    @Value("${com.cheshun.secondqueue}")
    private String secondQueue;

    @Value("${com.cheshun.thirdqueue}")
    private String thirdQueue;

    @Value("${com.cheshun.fourthqueue}")
    private String fourthQueue;

    @Value("${com.cheshun.directexchange}")
    private String directExchange;

    @Value("${com.cheshun.topicexchange}")
    private String topicExchange;

    @Value("${com.cheshun.fanoutexchange}")
    private String fanoutExchange;

    //创建四个队列
    @Bean("vipFirstQueue")
    public Queue getFirstQueue() {
        return new Queue(firstQueue);
    }

    @Bean("vipSecondQueue")
    public Queue getSecondQueue() {
        return new Queue(secondQueue);
    }
    @Bean("vipThirdQueue")
    public Queue getThirdQueue() {
        return new Queue(thirdQueue);
    }
    @Bean("vipFourthQueue")
    public Queue getFourthQueue() {
        return new Queue(fourthQueue);
    }

    // 创建三个交换机
    @Bean("vipDirectExchange")
    public DirectExchange getDirectExchange() {
        return new DirectExchange(directExchange);
    }
    @Bean("topicExchange")
    public TopicExchange getTopicExchange() {
        return new TopicExchange(topicExchange);
    }
    @Bean("fanoutExchange")
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange(fanoutExchange);
    }

}
