package com.logan.ndp.support.exception;

import com.logan.ndp.common.enums.RespStatusEnum;
import com.logan.ndp.support.pipeline.ProcessContext;

import java.util.Objects;

/**
 * @Description
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 12:50
 **/
public class ProcessException extends RuntimeException {

    /**
     * 流程处理上下文
     */
    private final ProcessContext processContext;

    public ProcessException(ProcessContext processContext) {
        super();
        this.processContext = processContext;
    }

    public ProcessException(ProcessContext processContext, Throwable cause) {
        super(cause);
        this.processContext = processContext;
    }

    @Override
    public String getMessage() {
        if (Objects.nonNull(this.processContext)) {
            return this.processContext.getResponse().getMsg();
        }
        return RespStatusEnum.CONTEXT_IS_NULL.getMsg();

    }

    public ProcessContext getProcessContext() {
        return processContext;
    }
}
