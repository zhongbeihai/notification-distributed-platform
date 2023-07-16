package com.logan.ndp.support.mq.rocketmq;

import com.logan.ndp.support.constant.MessageQueueConstant;
import com.logan.ndp.support.mq.SendMqService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * Description:
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "ndp.mq.pipeline", havingValue = MessageQueueConstant.ROCKET_MQ)
public class RocketMqSendMqServiceImpl implements SendMqService {

    @Autowired
    private RocketMQTemplate rocketMqTemplate;

    @Override
    public void send(String topic, String jsonValue, String tagId) {
        if (StringUtils.isNotBlank(tagId)) {
            topic = topic + ":" + tagId;
        }
        send(topic, jsonValue);
    }

    @Override
    public void send(String topic, String jsonValue) {
        rocketMqTemplate.send(topic, MessageBuilder.withPayload(jsonValue).build());
    }
}
