package com.logan.ndp.common.constant;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description 初始化线程池的常量信息
 * (仅初始化的使用，代码里的线程池配置有可能被apollo动态修改)
 * @Author Logan 黄嘉林
 * @Date 2023/7/15 14:43
 **/
public class ThreadPoolConstant {
    /**
     * small
     */
    public static final Integer SINGLE_CORE_POOL_SIZE = 1;
    public static final Integer SINGLE_MAX_POOL_SIZE = 1;
    public static final Integer SMALL_KEEP_LIVE_TIME = 10;

    /**
     * medium
     */
    public static final Integer COMMON_CORE_POOL_SIZE = 2;
    public static final Integer COMMON_MAX_POOL_SIZE = 2;
    public static final Integer COMMON_KEEP_LIVE_TIME = 60;
    public static final Integer COMMON_QUEUE_SIZE = 128;


    /**
     * big
     */
    public static final Integer BIG_QUEUE_SIZE = 1024;
    public static final BlockingQueue BIG_BLOCKING_QUEUE = new LinkedBlockingQueue(BIG_QUEUE_SIZE);

}
