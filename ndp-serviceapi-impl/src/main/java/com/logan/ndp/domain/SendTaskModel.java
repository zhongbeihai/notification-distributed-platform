package com.logan.ndp.domain;

import com.logan.ndp.common.domain.TaskInfo;
import com.logan.ndp.support.domain.MessageTemplate;
import com.logan.ndp.support.pipeline.ProcessModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 责任链模式
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 16:33
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel {

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 请求参数
     */
    private List<MessageParam> messageParamList;

    /**
     * 发送任务的信息
     */
    private List<TaskInfo> taskInfo;

    /**
     * 撤回任务的信息
     */
    private MessageTemplate messageTemplate;

}
