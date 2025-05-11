package com.logan.ndp.deduplication.build;

import com.logan.ndp.common.domain.TaskInfo;
import com.logan.ndp.common.enums.AnchorState;
import com.logan.ndp.common.enums.DeduplicationType;
import com.logan.ndp.deduplication.DeduplicationParam;
import org.springframework.stereotype.Service;

import java.util.Objects;



@Service
public class ContentDeduplicationBuilder extends AbstractDeduplicationBuilder implements Builder {

    public ContentDeduplicationBuilder() {
        deduplicationType = DeduplicationType.CONTENT.getCode();
    }

    @Override
    public DeduplicationParam build(String deduplication, TaskInfo taskInfo) {
        DeduplicationParam deduplicationParam = getParamsFromConfig(deduplicationType, deduplication, taskInfo);
        if (Objects.isNull(deduplicationParam)) {
            return null;
        }
        deduplicationParam.setAnchorState(AnchorState.CONTENT_DEDUPLICATION);
        return deduplicationParam;

    }
}
