package com.logan.ndp.support.mq.springeventbus;

import lombok.Builder;
import lombok.Data;

/**
 * @author 3y
 */
@Data
@Builder
public class NDPSpringEventSource {
    public String topic;
    public String jsonValue;
    public String tagId;
}
