package com.logan.ndp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 发送/撤回接口的参数
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 15:03
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendRequest {

    /**
     * 执行业务类型
     * send:发送消息
     * recall:撤回消息
     */
    private String code;

    /**
     * 消息模板Id
     * 【必填】
     */
    private Long messageTemplateId;


    /**
     * 消息相关的参数
     * 当业务类型为"send"，必传
     */
    private MessageParam messageParam;


}