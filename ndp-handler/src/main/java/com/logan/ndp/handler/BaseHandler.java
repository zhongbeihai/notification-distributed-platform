package com.logan.ndp.handler;

import com.logan.ndp.common.domain.AnchorInfo;
import com.logan.ndp.common.domain.TaskInfo;
import com.logan.ndp.common.enums.AnchorState;
import com.logan.ndp.flowcontrol.FlowControlFactory;
import com.logan.ndp.flowcontrol.FlowControlParam;
import com.logan.ndp.support.utils.LogUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Objects;

/**
 * 发送各个渠道的handler
 */
public abstract class BaseHandler implements Handler {
    /**
     * 标识渠道的Code
     * 子类初始化的时候指定
     */
    protected Integer channelCode;
    /**
     * 限流相关的参数
     * 子类初始化的时候指定
     */
    protected FlowControlParam flowControlParam;
    @Autowired
    private HandlerHolder handlerHolder;
    @Autowired
    private LogUtils logUtils;
    @Autowired
    private FlowControlFactory flowControlFactory;

    /**
     * 初始化渠道与Handler的映射关系
     */
    @PostConstruct
    private void init() {
        handlerHolder.putHandler(channelCode, this);
    }


    @Override
    public void doHandler(TaskInfo taskInfo) {
        // 只有子类指定了限流参数，才需要限流
        if (Objects.nonNull(flowControlParam)) {
            flowControlFactory.flowControl(taskInfo, flowControlParam);
        }
        if (handler(taskInfo)) {
            logUtils.print(AnchorInfo.builder().state(AnchorState.SEND_SUCCESS.getCode()).bizId(taskInfo.getBusinessId()).messageId(taskInfo.getMessageId()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
            return;
        }
        logUtils.print(AnchorInfo.builder().state(AnchorState.SEND_FAIL.getCode()).bizId(taskInfo.getBusinessId()).messageId(taskInfo.getMessageId()).businessId(taskInfo.getBusinessId()).ids(taskInfo.getReceiver()).build());
    }


    /**
     * 统一处理的handler接口
     *
     * @param taskInfo
     * @return
     */
    public abstract boolean handler(TaskInfo taskInfo);


}
