package com.logan.ndp.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.logan.ndp.common.constant.NDPConstant;
import com.logan.ndp.common.enums.RespStatusEnum;
import com.logan.ndp.common.vo.BasicResultVO;
import com.logan.ndp.domain.MessageParam;
import com.logan.ndp.domain.SendTaskModel;
import com.logan.ndp.support.pipeline.BusinessProcess;
import com.logan.ndp.support.pipeline.ProcessContext;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description 前置参数检验
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 17:00
 **/
public class PreParamCheckAction implements BusinessProcess<SendTaskModel> {
    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();

        Long messageTemplateId = sendTaskModel.getMessageTemplateId();
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();

        // 1.没有传入 消息模板Id 或者 messageParam
        if (Objects.isNull(messageTemplateId) || CollUtil.isEmpty(messageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        // 2.过滤 receiver=null 的messageParam
        List<MessageParam> resultMessageParamList = messageParamList.stream()
                .filter(messageParam -> !StrUtil.isBlank(messageParam.getReceiver()))
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(resultMessageParamList)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        // 3.过滤receiver大于群发目标上限的请求
        if (resultMessageParamList.stream().anyMatch(messageParam -> messageParam.getReceiver().split(StrUtil.COMMA).length > NDPConstant.BATCH_RECEIVER_SIZE)) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.TOO_MANY_RECEIVER));
            return;
        }

        sendTaskModel.setMessageParamList(resultMessageParamList);

    }
}
