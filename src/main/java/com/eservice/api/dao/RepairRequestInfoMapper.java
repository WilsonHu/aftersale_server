package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.repair_request_info.RepairRequestInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairRequestInfoMapper extends Mapper<RepairRequestInfo> {

    void saveAndGetID(RepairRequestInfo repairRequestInfo);

    List<RepairRequestInfo> getRecordsInRequesting(@Param("nameplate") String nameplate);
}