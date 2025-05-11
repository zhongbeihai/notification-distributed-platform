package com.logan.ndp.deduplication.service;


import com.logan.ndp.deduplication.DeduplicationParam;

public interface DeduplicationService {

    /**
     * 去重
     *
     * @param param
     */
    void deduplication(DeduplicationParam param);
}
