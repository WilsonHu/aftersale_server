package com.eservice.api.service.impl;

import com.eservice.api.dao.MachineMapper;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.machine.MachineBaseRecordInfo;
import com.eservice.api.model.machine.MachineInfo;
import com.eservice.api.service.MachineService;
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
public class MachineServiceImpl extends AbstractService<Machine> implements MachineService {
    @Resource
    private MachineMapper machineMapper;

    public List<Machine> selectByAccount(String account){
       return  machineMapper.selectByAccount(account);
    }

    public List<MachineInfo> getSaledMachineInfoList(String nameplate,
                                                     String orderNum,
                                                     String machineType,
                                                     String agent,
                                                     String status,
                                                     String customerName,
                                                     String query_start_time_install,
                                                     String query_finish_time_install,
                                                     String machineWhereFrom,
                                                     String installChargePerson,
                                                     boolean isFuzzy) {
        return machineMapper.getSaledMachineInfoList( nameplate,
                orderNum,
                machineType,
                agent,
                status,
                customerName,
                query_start_time_install,
                query_finish_time_install,
                machineWhereFrom,
                installChargePerson,
                isFuzzy);
    }

    public List<MachineBaseRecordInfo> selectBaseRecordByNameplate(String nameplate){
        return machineMapper.selectBaseRecordByNameplate(nameplate);
    }

    public List<MachineBaseRecordInfo> selectBaseRecordByUser(String userName){
        return machineMapper.selectBaseRecordByUser(userName);
    }

}
