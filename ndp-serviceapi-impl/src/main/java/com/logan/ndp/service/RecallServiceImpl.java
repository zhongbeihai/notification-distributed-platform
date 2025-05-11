package com.logan.ndp.service;

import com.logan.ndp.common.enums.RespStatusEnum;
import com.logan.ndp.common.vo.BasicResultVO;
import com.logan.ndp.domain.SendRequest;
import com.logan.ndp.domain.SendResponse;
import com.logan.ndp.domain.SendTaskModel;
import com.logan.ndp.support.pipeline.ProcessContext;
import com.logan.ndp.support.pipeline.ProcessController;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 撤回接口
 *
 * @author 3y
 */
@Service
public class RecallServiceImpl implements RecallService {

    @Autowired
    private ProcessController processController;

    @Override
    public SendResponse recall(SendRequest sendRequest) {
        if(ObjectUtils.isEmpty(sendRequest)){
            return new SendResponse(RespStatusEnum.CLIENT_BAD_PARAMETERS.getCode(), RespStatusEnum.CLIENT_BAD_PARAMETERS.getMsg());
        }
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .build();
        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();
        ProcessContext process = processController.process(context);
        return new SendResponse(process.getResponse().getStatus(), process.getResponse().getMsg());
    }
}
