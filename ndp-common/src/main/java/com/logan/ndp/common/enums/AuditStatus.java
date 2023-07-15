package com.logan.ndp.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description
 * @Author Logan 黄嘉林
 * @Date 2023/7/15 15:13
 **/
@Getter
@ToString
@AllArgsConstructor
public enum AuditStatus implements PowerfulEnum {

    /**
     * 10.待审核
     */
    WAIT_AUDIT(10, "待审核"),
    /**
     * 20.审核成功
     */
    AUDIT_SUCCESS(20, "审核成功"),
    /**
     * 30.被拒绝'
     */
    AUDIT_REJECT(30, "被拒绝");

    private final Integer code;
    private final String description;


}