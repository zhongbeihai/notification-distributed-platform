package com.logan.ndp.deduplication.build;


import com.logan.ndp.common.domain.TaskInfo;
import com.logan.ndp.deduplication.DeduplicationParam;

public interface Builder {

    String DEDUPLICATION_CONFIG_PRE = "deduplication_";

    /**
     * 根据配置构建去重参数
     *
     * @param deduplication
     * @param taskInfo
     * @return
     */
    DeduplicationParam build(String deduplication, TaskInfo taskInfo);
}
