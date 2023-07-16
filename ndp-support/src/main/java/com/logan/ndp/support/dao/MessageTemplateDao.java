package com.logan.ndp.support.dao;

import com.logan.ndp.support.domain.MessageTemplate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MessageTemplateDao extends JpaRepository<MessageTemplate, Long>, JpaSpecificationExecutor<MessageTemplate> {
    /**
     * 查询 列表（分页)
     *
     * @Param deleted  0：未删除 1：删除
     * @Param pageable 分页对象
     *
     */
    List<MessageTemplate> findAllByIsDeletedEqualsOrderByUpdatedDesc(Integer deleted, Pageable pageable);


    /**
     * 统计未删除的条数
     *
     * @Param deleted
     *
     */
    Long countByIsDeletedEquals(Integer deleted);
}
