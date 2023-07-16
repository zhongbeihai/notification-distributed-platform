package com.logan.ndp.support.pipeline;

import java.util.List;

/**
 * @Description 业务执行模板（把责任链的逻辑串起来）
 * @Author Logan 黄嘉林
 * @Date 2023/7/16 12:51
 **/
public class ProcessTemplate {
    private List<BusinessProcess> processList;

    public List<BusinessProcess> getProcessList() {
        return processList;
    }

    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }
}
