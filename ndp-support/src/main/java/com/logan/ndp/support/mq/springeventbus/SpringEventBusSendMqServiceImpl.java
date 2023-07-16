package com.logan.ndp.support.mq.springeventbus;

import com.logan.ndp.support.constant.MessageQueueConstant;
import com.logan.ndp.support.mq.SendMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * 描述：
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "ndp.mq.pipeline", havingValue = MessageQueueConstant.SPRING_EVENT_BUS)
public class SpringEventBusSendMqServiceImpl implements SendMqService {

    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public void send(String topic, String jsonValue, String tagId) {
        NDPSpringEventSource source = NDPSpringEventSource.builder().topic(topic).jsonValue(jsonValue).tagId(tagId).build();
        NDPSpringEventBusEvent NDPSpringEventBusEvent = new NDPSpringEventBusEvent(this, source);
        applicationContext.publishEvent(NDPSpringEventBusEvent);
    }

    @Override
    public void send(String topic, String jsonValue) {
        send(topic, jsonValue, null);
    }
}
