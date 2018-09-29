package com.eservice.api.service.impl;

import com.eservice.api.dao.InstallRecordMapper;
import com.eservice.api.model.install_record.InstallRecord;
import com.eservice.api.model.install_record.InstallRecordInfo;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.service.InstallRecordService;
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
public class InstallRecordServiceImpl extends AbstractService<InstallRecord> implements InstallRecordService {
    @Resource
    private InstallRecordMapper installRecordMapper;

    public  List<InstallRecord> selectByNameplate(String nameplate){
        return installRecordMapper.selectByNameplate(nameplate);
    }

    public List<InstallRecordInfo> getInstallDetail(String nameplate) {
        return installRecordMapper.getInstallDetail(nameplate);
    }

    public List<InstallRecordInfo> getInstallRecordInfoList(String nameplate,
                                                            String orderNum,
                                                            String machineType,
                                                            String agent,
                                                            String installStatus,
                                                            String installRecordCustomerName,
                                                            String installChargePersonName,
                                                            String query_start_install_plan_date,
                                                            String query_finish_install_plan_date,
                                                            String query_start_facory_date,
                                                            String query_finish_facory_date,
                                                            String query_start_install_actual_time,
                                                            String query_finish_install_actual_time,
                                                            String installRecordId,
                                                            String machineCustomerName,
                                                            boolean isAgent,
                                                            boolean isFuzzy) {
        return installRecordMapper.getInstallRecordInfoList(
                nameplate,
                orderNum,
                machineType,
                agent,
                installStatus,
                installRecordCustomerName,
                installChargePersonName,
                query_start_install_plan_date,
                query_finish_install_plan_date,
                query_start_facory_date,
                query_finish_facory_date,
                query_start_install_actual_time,
                query_finish_install_actual_time,
                installRecordId,
                machineCustomerName,
                isAgent,
                isFuzzy);
    }

    public List<InstallRecordInfo> selectInstallTaskByUser(String userName){
        return installRecordMapper.selectInstallTaskByUser(userName);
    }

}
