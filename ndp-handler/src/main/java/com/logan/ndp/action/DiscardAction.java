package com.logan.ndp.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.logan.ndp.common.constant.CommonConstant;
import com.logan.ndp.common.domain.AnchorInfo;
import com.logan.ndp.common.domain.TaskInfo;
import com.logan.ndp.common.enums.AnchorState;
import com.logan.ndp.support.pipeline.BusinessProcess;
import com.logan.ndp.support.pipeline.ProcessContext;
import com.logan.ndp.support.service.ConfigService;
import com.logan.ndp.support.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 丢弃消息
 * 一般将需要丢弃的模板id写在分布式配置中心
 *
 */
@Service
public class DiscardAction implements BusinessProcess<TaskInfo> {
    private static final String DISCARD_MESSAGE_KEY = "discardMsgIds";

    @Autowired
    private ConfigService config;
    @Autowired
    private LogUtils logUtils;

    @Override
    public void process(ProcessContext<TaskInfo> context) {
        TaskInfo taskInfo = context.getProcessModel();
        // 配置示例:	["1","2"]
        JSONArray array = JSON.parseArray(config.getProperty(DISCARD_MESSAGE_KEY, CommonConstant.EMPTY_VALUE_JSON_ARRAY));
        if (array.contains(String.valueOf(taskInfo.getMessageId()))) {
            logUtils.print(AnchorInfo.builder().businessId(taskInfo.getBusinessId()).messageId(taskInfo.getMessageId()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).state(AnchorState.DISCARD.getCode()).build());
            context.setNeedBreak(true);
        }

    }
}
