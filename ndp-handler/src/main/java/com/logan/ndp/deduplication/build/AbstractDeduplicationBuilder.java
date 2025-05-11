package com.logan.ndp.deduplication.build;

import com.alibaba.fastjson.JSONObject;
import com.logan.ndp.common.domain.TaskInfo;
import com.logan.ndp.deduplication.DeduplicationHolder;
import com.logan.ndp.deduplication.DeduplicationParam;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Objects;


public abstract class AbstractDeduplicationBuilder implements Builder {

    protected Integer deduplicationType;

    @Autowired
    private DeduplicationHolder deduplicationHolder;

    @PostConstruct
    public void init() {
        deduplicationHolder.putBuilder(deduplicationType, this);
    }

    public DeduplicationParam getParamsFromConfig(Integer key, String duplicationConfig, TaskInfo taskInfo) {
        JSONObject object = JSONObject.parseObject(duplicationConfig);
        if (Objects.isNull(object)) {
            return null;
        }
        DeduplicationParam deduplicationParam = JSONObject.parseObject(object.getString(DEDUPLICATION_CONFIG_PRE + key), DeduplicationParam.class);
        if (Objects.isNull(deduplicationParam)) {
            return null;
        }
        deduplicationParam.setTaskInfo(taskInfo);
        return deduplicationParam;
    }

}
