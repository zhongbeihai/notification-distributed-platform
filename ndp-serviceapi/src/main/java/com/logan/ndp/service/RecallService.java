package com.logan.ndp.service;

import com.logan.ndp.domain.SendRequest;
import com.logan.ndp.domain.SendResponse;

/**
 * @Description 撤回接口
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 15:08
 **/
public interface RecallService {


    /**
     * 根据模板ID撤回消息
     *
     * @param sendRequest 发送/撤回消息参数
     * @return SendResponse
     */
    SendResponse recall(SendRequest sendRequest);
}
