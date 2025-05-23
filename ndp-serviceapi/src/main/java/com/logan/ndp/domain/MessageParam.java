package com.logan.ndp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @Description 消息的参数 single
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 15:01
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageParam {

    /**
     * @Description 接收者
     * 多个用,逗号号分隔开
     * 【不能大于100个】
     * 必传
     */
    private String receiver;

    /**
     * @Description 消息内容中的可变部分(占位符替换)
     * 可选
     */
    private Map<String, String> variables;

    /**
     * @Description 扩展参数
     * 可选
     */
    private Map<String, String> extra;
}
