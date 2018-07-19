package com.eservice.api.service.impl;

import com.eservice.api.dao.RepairRequestInfoMapper;
import com.eservice.api.model.repair_request_info.RepairRequestInfo;
import com.eservice.api.service.RepairRequestInfoService;
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
public class RepairRequestInfoServiceImpl extends AbstractService<RepairRequestInfo> implements RepairRequestInfoService {
    @Resource
    private RepairRequestInfoMapper repairRequestInfoMapper;

    /**
     * 保存并得到id号
     */
    public void saveAndGetID(RepairRequestInfo repairRequestInfo) {
        repairRequestInfoMapper.saveAndGetID(repairRequestInfo);
    }

}
