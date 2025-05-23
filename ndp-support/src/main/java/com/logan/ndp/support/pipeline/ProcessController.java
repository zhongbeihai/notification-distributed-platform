package com.logan.ndp.support.pipeline;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.logan.ndp.common.enums.RespStatusEnum;
import com.logan.ndp.common.vo.BasicResultVO;
import com.logan.ndp.support.exception.ProcessException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * @Description ProcessTemplate是一整个责任链。BusinessProcess是责任链的一个步骤，并且绑定一个ProcessContext
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 12:55
 **/
@Slf4j
@Data
public class ProcessController {

    /**
     * 模板映射
     */
    private Map<String, ProcessTemplate> templateConfig = null;


    /**
     * 执行责任链
     * @Param context
     * @Return 返回上下文内容
     */
    public ProcessContext process(ProcessContext context) {

        // 前置检查
        try {
            preCheck(context);
        } catch (ProcessException e) {
            return e.getProcessContext();
        }

        /**
         * 遍历流程节点
         */
        List<BusinessProcess> processList = templateConfig.get(context.getCode()).getProcessList();
        for (BusinessProcess businessProcess : processList) {
            businessProcess.process(context);
            if (context.getNeedBreak()) {
                break;
            }
        }

        // 返回传进来的上下文
        return context;
    }


    /**
     * 执行前检查，出错则抛出异常
     *
     * @Param context 执行上下文
     * @Throws ProcessException 异常信息
     */
    private void preCheck(ProcessContext context) throws ProcessException {
        // 上下文
        if (Objects.isNull(context)) {
            context = new ProcessContext();
            context.setResponse(BasicResultVO.fail(RespStatusEnum.CONTEXT_IS_NULL));
            throw new ProcessException(context);
        }

        // 业务代码
        String businessCode = context.getCode();
        if (StrUtil.isBlank(businessCode)) {
            context.setResponse(BasicResultVO.fail(RespStatusEnum.BUSINESS_CODE_IS_NULL));
            throw new ProcessException(context);
        }

        // 执行模板
        ProcessTemplate processTemplate = templateConfig.get(businessCode);
        if (Objects.isNull(processTemplate)) {
            context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_TEMPLATE_IS_NULL));
            throw new ProcessException(context);
        }

        // 执行模板列表
        List<BusinessProcess> processList = processTemplate.getProcessList();
        if (CollUtil.isEmpty(processList)) {
            context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_LIST_IS_NULL));
            throw new ProcessException(context);
        }

    }


}
