package com.eservice.api.service.impl;

import com.eservice.api.dao.RepairActualInfoMapper;
import com.eservice.api.model.repair_actual_info.RepairActualInfo;
import com.eservice.api.service.RepairActualInfoService;
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
public class RepairActualInfoServiceImpl extends AbstractService<RepairActualInfo> implements RepairActualInfoService {
    @Resource
    private RepairActualInfoMapper repairActualInfoMapper;

}
