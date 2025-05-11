package com.logan.ndp.support.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Description 渠道账号实体。例如腾讯短信平台是一个实例
 * @Author Logan
 * @Date 2023/7/15 18:51
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChannelAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 渠道账号名称分
     */
    private String name;

    /**
     * 发送渠道
     * 枚举值：com.logan.ndp.common.enums.ChannelType
     */
    private Integer sendChannel;

    /**
     * 账号配置
     */
    private String accountConfig;

    /**
     * 是否删除
     * 0：未删除
     * 1：已删除
     */
    private Integer isDeleted;

    /**
     * 账号拥有者
     */
    private String creator;

    /**
     * 创建时间 单位 s
     */
    private Integer created;

    /**
     * 更新时间 单位s
     */
    private Integer updated;
}
