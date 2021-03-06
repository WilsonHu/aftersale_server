package com.eservice.api.service.impl;

import com.eservice.api.dao.PartsInfoMapper;
import com.eservice.api.model.parts_info.PartsAllInfo;
import com.eservice.api.model.parts_info.PartsInfo;
import com.eservice.api.model.parts_info.PartsInfoWithRepairRecordInfo;
import com.eservice.api.service.PartsInfoService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/10.
*/
@Service
@Transactional
public class PartsInfoServiceImpl extends AbstractService<PartsInfo> implements PartsInfoService {
    @Resource
    private PartsInfoMapper partsInfoMapper;

    public List<PartsAllInfo> getPartsInfoByRepairRecordId(Integer repairRecordId){
        return partsInfoMapper.getPartsInfoByRepairRecordId(repairRecordId);
    }

    public List<PartsAllInfo> getPartsInfoList( String nameplate,
                                                String partsName,
                                                String customerNameInMachine,
                                                String repairChargePersonName,
                                                String queryStartSendbackConfirmedTime,
                                                String queryFinishSendbackConfirmedTime,
                                                String supplier,
                                                String partsStatus,
                                                String sendbackTrackingNumber,
                                                String sendbackConfirmedPerson,
                                                boolean isFuzzy){
        return partsInfoMapper.getPartsInfoList(nameplate,
                partsName,
                customerNameInMachine,
                repairChargePersonName,
                queryStartSendbackConfirmedTime,
                queryFinishSendbackConfirmedTime,
                supplier,
                partsStatus,
                sendbackTrackingNumber,
                sendbackConfirmedPerson,
                isFuzzy);
    }

    public List<PartsInfoWithRepairRecordInfo> getPartsInfoTaskByUserName(String userName){
        return partsInfoMapper.getPartsInfoTaskByUserName(userName);
    }
}
