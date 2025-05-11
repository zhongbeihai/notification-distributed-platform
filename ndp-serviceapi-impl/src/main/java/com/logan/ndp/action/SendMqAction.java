package com.logan.ndp.action;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Throwables;
import com.logan.ndp.common.enums.RespStatusEnum;
import com.logan.ndp.common.vo.BasicResultVO;
import com.logan.ndp.domain.SendTaskModel;
import com.logan.ndp.enums.BusinessCode;
import com.logan.ndp.support.mq.SendMqService;
import com.logan.ndp.support.pipeline.BusinessProcess;
import com.logan.ndp.support.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description 将任务发布至消息队列
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 17:03
 **/

@Slf4j
@Service
public class SendMqAction implements BusinessProcess<SendTaskModel> {


    @Autowired
    private SendMqService sendMqService;

    @Value("${ndp.business.topic.name}")
    private String sendMessageTopic;

    @Value("${ndp.business.recall.topic.name}")
    private String ndpRecall;
    @Value("${ndp.business.tagId.value}")
    private String tagId;

    @Value("${ndp.mq.pipeline}")
    private String mqPipeline;


    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        try {
            if (BusinessCode.COMMON_SEND.getCode().equals(context.getCode())) {
                String message = JSON.toJSONString(sendTaskModel.getTaskInfo(), new SerializerFeature[]{SerializerFeature.WriteClassName});
                sendMqService.send(sendMessageTopic, message, tagId);
            } else if (BusinessCode.RECALL.getCode().equals(context.getCode())) {
                String message = JSON.toJSONString(sendTaskModel.getMessageTemplate(), new SerializerFeature[]{SerializerFeature.WriteClassName});
                sendMqService.send(ndpRecall, message, tagId);
            }
        } catch (Exception e) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("send {} fail! e:{},params:{}", mqPipeline, Throwables.getStackTraceAsString(e)
                    , JSON.toJSONString(CollUtil.getFirst(sendTaskModel.getTaskInfo().listIterator())));
        }
    }

}
