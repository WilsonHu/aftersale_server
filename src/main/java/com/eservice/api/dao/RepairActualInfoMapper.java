package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.repair_actual_info.RepairActualInfo;

import java.util.List;

public interface RepairActualInfoMapper extends Mapper<RepairActualInfo> {

    void saveAndGetID(RepairActualInfo repairActualInfo);

    List<RepairActualInfo> getRepairActualInfoInUpdating(String repairRecordId);
}