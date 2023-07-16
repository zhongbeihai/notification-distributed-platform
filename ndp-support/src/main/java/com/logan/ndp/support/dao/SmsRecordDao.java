package com.logan.ndp.support.dao;

import com.logan.ndp.support.domain.SmsRecord;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SmsRecordDao extends CrudRepository<SmsRecord, Long> {
    /**
     * 通过日期和手机号找到发送记录
     *
     * @Param phone
     * @Param sendDate
     * @Return List<SmsRecord>
     */
    List<SmsRecord> findByPhoneAndSendDate(Long phone, Integer sendDate);
}
