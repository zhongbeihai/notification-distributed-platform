package com.logan.ndp.config;

import com.logan.ndp.action.AfterParamCheckAction;
import com.logan.ndp.action.AssembleAction;
import com.logan.ndp.action.PreParamCheckAction;
import com.logan.ndp.action.SendMqAction;
import com.logan.ndp.enums.BusinessCode;
import com.logan.ndp.support.pipeline.ProcessController;
import com.logan.ndp.support.pipeline.ProcessTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description api层pipeline配置类
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 17:15
 **/
@Configuration
public class PipelineConfig {

    @Autowired
    private PreParamCheckAction preParamCheckAction;
    @Autowired
    private AssembleAction assembleAction;
    @Autowired
    private AfterParamCheckAction afterParamCheckAction;
    @Autowired
    private SendMqAction sendMqAction;

    /**
     * 普通发送执行流程
     * 1. 前置参数校验
     * 2. 组装参数
     * 3. 后置参数校验
     * 4. 发送消息至MQ
     *
     * @return
     */
    @Bean("commonSendTemplate")
    public ProcessTemplate commonSendTemplate() {
        ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(Arrays.asList(preParamCheckAction, assembleAction,
                afterParamCheckAction, sendMqAction));
        return processTemplate;
    }

    /**
     * 消息撤回执行流程
     * 1.组装参数
     * 2.发送MQ
     *
     * @return
     */
    @Bean("recallMessageTemplate")
    public ProcessTemplate recallMessageTemplate() {
        ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(Arrays.asList(assembleAction, sendMqAction));
        return processTemplate;
    }

    /**
     * pipeline流程控制器
     * 后续扩展则加BusinessCode和ProcessTemplate
     *
     * @return
     */
    @Bean
    public ProcessController processController() {
        ProcessController processController = new ProcessController();
        Map<String, ProcessTemplate> templateConfig = new HashMap<>(4);
        templateConfig.put(BusinessCode.COMMON_SEND.getCode(), commonSendTemplate());
        templateConfig.put(BusinessCode.RECALL.getCode(), recallMessageTemplate());
        processController.setTemplateConfig(templateConfig);
        return processController;
    }

}
