package com.eservice.api.service.impl;

import com.eservice.api.dao.RepairRecordMapper;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.service.RepairRecordService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/19.
*/
@Service
@Transactional
public class RepairRecordServiceImpl extends AbstractService<RepairRecord> implements RepairRecordService {
    @Resource
    private RepairRecordMapper repairRecordMapper;
    public RepairRecord selectByNameplate(String nameplate){
        return repairRecordMapper.selectByNameplate(nameplate);
    }

}
