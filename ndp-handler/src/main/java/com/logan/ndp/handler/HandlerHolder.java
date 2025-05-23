package com.logan.ndp.handler;


import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * channel->Handler的映射关系
 *
 */
@Component
public class HandlerHolder {

    private Map<Integer, Handler> handlers = new HashMap<Integer, Handler>(128);

    public void putHandler(Integer channelCode, Handler handler) {
        handlers.put(channelCode, handler);
    }

    public Handler route(Integer channelCode) {
        return handlers.get(channelCode);
    }
}
