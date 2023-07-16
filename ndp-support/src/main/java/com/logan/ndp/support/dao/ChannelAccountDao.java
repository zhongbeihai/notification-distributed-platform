package com.logan.ndp.support.dao;

import com.logan.ndp.support.domain.ChannelAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Description 渠道账号Dao
 */
public interface ChannelAccountDao extends JpaRepository<ChannelAccount, Long> {
    /**
     * 查询 列表
     *
     * @Param deleted     0：未删除 1：删除
     * @Param channelType 渠道值
     * @Param creator     创建者
     */
    List<ChannelAccount> findAllByIsDeletedEqualsAndCreatorEqualsAndSendChannelEquals(Integer deleted, String creator, Integer channelType);

    /**
     * 查询 列表
     *
     * @Param deleted     0：未删除 1：删除
     * @Param channelType 渠道值
     */
    List<ChannelAccount> findAllByIsDeletedEqualsAndSendChannelEquals(Integer deleted, Integer channelType);

    /**
     * 根据创建者检索相关的记录
     *
     * @Param creator
     */
    List<ChannelAccount> findAllByCreatorEquals(String creator);

    /**
     * 统计未删除的条数
     *
     * @Param deleted
     */
    Long countByIsDeletedEquals(Integer deleted);
}
