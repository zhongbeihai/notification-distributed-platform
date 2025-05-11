package com.logan.ndp.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.logan.ndp.common.domain.AnchorInfo;
import com.logan.ndp.common.domain.TaskInfo;
import com.logan.ndp.common.enums.AnchorState;
import com.logan.ndp.common.enums.ShieldType;
import com.logan.ndp.support.pipeline.BusinessProcess;
import com.logan.ndp.support.pipeline.ProcessContext;
import com.logan.ndp.support.utils.LogUtils;
import com.logan.ndp.support.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * 屏蔽消息
 * 1. 当接收到该消息是夜间，直接屏蔽（不发送）
 * 2. 当接收到该消息是夜间，次日9点发送
 * example:当消息下发至austin平台时，已经是凌晨1点，业务希望此类消息在次日的早上9点推送
 * (配合 分布式任务定时任务框架搞掂)
 *
 */
@Service
public class ShieldAction implements BusinessProcess<TaskInfo> {

    private static final String NIGHT_SHIELD_BUT_NEXT_DAY_SEND_KEY = "night_shield_send";
    private static final long SECONDS_OF_A_DAY = 86400L;

    /**
     * 默认早上8点之前是凌晨
     */
    private static final int NIGHT = 8;

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private LogUtils logUtils;


    @Override
    public void process(ProcessContext<TaskInfo> context) {
        TaskInfo taskInfo = context.getProcessModel();

        if (ShieldType.NIGHT_NO_SHIELD.getCode().equals(taskInfo.getShieldType())) {
            return;
        }

        if (LocalDateTime.now().getHour() < NIGHT) {
            if (ShieldType.NIGHT_SHIELD.getCode().equals(taskInfo.getShieldType())) {
                logUtils.print(AnchorInfo.builder().state(AnchorState.NIGHT_SHIELD.getCode())
                        .businessId(taskInfo.getBusinessId()).messageId(taskInfo.getMessageId()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
            }
            if (ShieldType.NIGHT_SHIELD_BUT_NEXT_DAY_SEND.getCode().equals(taskInfo.getShieldType())) {
                redisUtils.lPush(NIGHT_SHIELD_BUT_NEXT_DAY_SEND_KEY, JSON.toJSONString(taskInfo,
                                SerializerFeature.WriteClassName),
                        SECONDS_OF_A_DAY);
                logUtils.print(AnchorInfo.builder().state(AnchorState.NIGHT_SHIELD_NEXT_SEND.getCode()).businessId(taskInfo.getBusinessId()).messageId(taskInfo.getMessageId()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
            }
            context.setNeedBreak(true);
        }

    }
}
