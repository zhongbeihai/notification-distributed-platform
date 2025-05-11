package com.logan.ndp.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * @Description 埋点信息
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 14:34
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnchorInfo {

    /**
     * 消息唯一Id(数据追踪使用)
     * 生成逻辑参考 TaskInfoUtils
     */
    private long messageId;

    private long bizId;

    /**
     * 发送用户
     */
    private Set<String> ids;

    /**
     * 具体点位
     *
     * @see com.logan.ndp.common.enums.AnchorState
     */
    private int state;

    /**
     * 业务Id(数据追踪使用)
     * 生成逻辑参考 TaskInfoUtils
     */
    private Long businessId;


    /**
     * 日志生成时间
     */
    private long logTimestamp;

}
