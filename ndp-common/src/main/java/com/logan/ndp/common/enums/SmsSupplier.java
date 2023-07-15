package com.logan.ndp.common.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 短信渠道商
 * @Author Logan 黄嘉林
 * @Date 2023/7/15 15:13
 */
@Getter
@ToString
@AllArgsConstructor
public enum SmsSupplier implements PowerfulEnum {


    /**
     * 腾讯渠道商
     */
    TENCENT(10, "腾讯渠道商"),
    /**
     * 云片渠道商
     */
    YUN_PAIN(20, "云片渠道商");
    private final Integer code;
    private final String description;

}
