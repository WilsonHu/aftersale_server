package com.eservice.api.service.impl;

import com.eservice.api.dao.MaintainAbnormalRecordMapper;
import com.eservice.api.model.maintain_abnormal_record.MaintainAbnormalRecord;
import com.eservice.api.service.MaintainAbnormalRecordService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/10.
*/
@Service
@Transactional
public class MaintainAbnormalRecordServiceImpl extends AbstractService<MaintainAbnormalRecord> implements MaintainAbnormalRecordService {
    @Resource
    private MaintainAbnormalRecordMapper maintainAbnormalRecordMapper;

}
