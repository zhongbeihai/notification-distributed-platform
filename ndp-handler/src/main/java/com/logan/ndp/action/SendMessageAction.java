package com.logan.ndp.action;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Sets;
import com.logan.ndp.common.domain.TaskInfo;
import com.logan.ndp.common.enums.ChannelType;
import com.logan.ndp.handler.HandlerHolder;
import com.logan.ndp.support.pipeline.BusinessProcess;
import com.logan.ndp.support.pipeline.ProcessContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 发送消息，路由到对应的渠道下发消息
 *
 */
@Service
public class SendMessageAction implements BusinessProcess<TaskInfo> {
    @Autowired
    private HandlerHolder handlerHolder;

    @Override
    public void process(ProcessContext<TaskInfo> context) {
        TaskInfo taskInfo = context.getProcessModel();

        // 微信小程序&服务号只支持单人推送，为了后续逻辑统一处理，于是在这做了单发处理
        if (true) {
            for (String receiver : taskInfo.getReceiver()) {
                TaskInfo taskClone = ObjectUtil.cloneByStream(taskInfo);
                taskClone.setReceiver(Sets.newHashSet(receiver));
                handlerHolder.route(taskInfo.getSendChannel()).doHandler(taskClone);
            }
            return;
        }
        handlerHolder.route(taskInfo.getSendChannel()).doHandler(taskInfo);
    }
}
