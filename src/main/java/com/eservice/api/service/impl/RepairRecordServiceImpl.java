package com.eservice.api.service.impl;

import com.eservice.api.dao.RepairRecordMapper;
import com.eservice.api.model.machine.MachineBaseRecordInfo;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.model.repair_record.RepairRecordInfo;
import com.eservice.api.service.RepairRecordService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


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
    public List<RepairRecord> selectByNameplate(String nameplate){
        return repairRecordMapper.selectByNameplate(nameplate);
    }

    public List<RepairRecordInfo> getRepairRecordInfoList( String nameplate,
                                                           String orderNum,
                                                           String repairStatus,
//                                                           String partsStatus,
                                                           String repairRecordCustomerName,
                                                           String agent,
                                                           String repairChargePersonName,
                                                           String issuePositionId,
                                                           String inWarrantyPeriod,
                                                           String queryStartRepairCreateTime,
                                                           String queryFinishRepairCreateTime,
                                                           String queryStartRepairEndTime,
                                                           String queryFinishRepairEndTime,
                                                           String repairRecordId,
                                                           String repairActualInfoId,
                                                           boolean isFuzzy){
        return repairRecordMapper.getRepairRecordInfoList(
                nameplate,
                orderNum,
                repairStatus,
//                partsStatus,
                repairRecordCustomerName,
                agent,
                repairChargePersonName,
                issuePositionId,
                inWarrantyPeriod,
                queryStartRepairCreateTime,
                queryFinishRepairCreateTime,
                queryStartRepairEndTime,
                queryFinishRepairEndTime,
                repairRecordId,
                repairActualInfoId,
                isFuzzy);

    }

    public List<RepairRecordInfo> selectRepairTaskByUser(String userName){
        return repairRecordMapper.selectRepairTaskByUser(userName);
    }

    public List<RepairRecord> selectRepairRecordByRepairRequestId(String repairRequestInfoId){
        return repairRecordMapper.selectRepairRecordByRepairRequestId(repairRequestInfoId);
    }

    public RepairRecord selectRepairRecordInRequesting(String repairRequestInfoId){
        return repairRecordMapper.selectRepairRecordInRequesting(repairRequestInfoId);
    }

    public RepairRecord selectRepairRecordByPartsInfoId(String partsInfoId){
        return repairRecordMapper.selectRepairRecordByPartsInfoId(partsInfoId);
    }

}
