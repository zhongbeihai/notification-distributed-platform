package com.logan.ndp.service;

import com.logan.ndp.domain.BatchSendRequest;
import com.logan.ndp.domain.SendRequest;
import com.logan.ndp.domain.SendResponse;

/**
 * @Description 发送接口
 */
public interface SendService {


    /**
     * 单文案发送接口
     *
     * @param sendRequest 发送/撤回接口的参数
     * @return SendResponse
     */
    SendResponse send(SendRequest sendRequest);


    /**
     * 多文案发送接口
     *
     * @param batchSendRequest 群发消息接口的参数
     * @return SendResponse
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);

}
