package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.model.repair_record.RepairRecordInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairRecordMapper extends Mapper<RepairRecord> {

    List<RepairRecord> selectByNameplate(@Param("nameplate")String nameplate);

    List<RepairRecordInfo> getRepairRecordInfoList(@Param("nameplate")String nameplate,
                                                   @Param("orderNum")String orderNum,
                                                   @Param("repairStatus")String repairStatus,
//                                                   @Param("partsStatus")String partsStatus,
                                                   @Param("repairRecordCustomerName")String repairRecordCustomerName,
                                                   @Param("agent")String agent,
                                                   @Param("repairChargePersonName")String repairChargePersonName,
                                                   @Param("issuePositionId")String issuePositionId,
                                                   @Param("inWarrantyPeriod")String inWarrantyPeriod,
                                                   @Param("queryStartRepairCreateTime")String queryStartRepairCreateTime,
                                                   @Param("queryFinishRepairCreateTime")String queryFinishRepairCreateTime,
                                                   @Param("queryStartRepairEndTime")String queryStartRepairEndTime,
                                                   @Param("queryFinishRepairEndTime")String queryFinishRepairEndTime,
                                                   @Param("repairRecordId")String repairRecordId,
                                                   @Param("repairActualInfoId") String repairActualInfoId,
                                                   @Param("isFuzzy")boolean isFuzzy);

    List<RepairRecordInfo> selectRepairTaskByUser(@Param("userName")String userName);
	
    List<RepairRecord> selectRepairRecordByRepairRequestId(@Param("repairRequestInfoId")String repairRequestInfoId);
    
	RepairRecord selectRepairRecordInRequesting(@Param("repairRequestInfoId")String repairRequestInfoId);
    RepairRecord selectRepairRecordByPartsInfoId(@Param("partsInfoId")String partsInfoId);
}