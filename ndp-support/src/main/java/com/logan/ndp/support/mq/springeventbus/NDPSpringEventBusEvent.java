package com.logan.ndp.support.mq.springeventbus;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 描述：消息
 *
 * @author tony
 */
@Getter
public class NDPSpringEventBusEvent extends ApplicationEvent {

    private NDPSpringEventSource NDPSpringEventSource;

    public NDPSpringEventBusEvent(Object source, NDPSpringEventSource NDPSpringEventSource) {
        super(source);
        this.NDPSpringEventSource = NDPSpringEventSource;
    }

}
