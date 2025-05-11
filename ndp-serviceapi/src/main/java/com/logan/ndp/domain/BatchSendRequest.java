package com.logan.ndp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Description 群发消息接口的参数
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 14:59
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BatchSendRequest {


    /**
     * 执行业务类型
     * 必传,参考 BusinessCode枚举
     */
    private String code;


    /**
     * 消息模板Id
     * 必传
     */
    private Long messageTemplateId;


    /**
     * 消息相关的参数
     * 必传
     */
    private List<MessageParam> messageParamList;


}
