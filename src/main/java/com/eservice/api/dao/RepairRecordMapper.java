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
                                                   @Param("repairRecordCustomerName")String repairRecordCustomerName,
                                                   @Param("agent")String agent,
                                                   @Param("repairChargePersonName")String repairChargePersonName,
                                                   @Param("issuePositionName")String issuePositionName,
                                                   @Param("inWarrantyPeriod")String inWarrantyPeriod,
                                                   @Param("queryStartRepairCreateTime")String queryStartRepairCreateTime,
                                                   @Param("queryFinishRepairCreateTime")String queryFinishRepairCreateTime,
                                                   @Param("queryStartRepairEndTime")String queryStartRepairEndTime,
                                                   @Param("queryFinishRepairEndTime")String queryFinishRepairEndTime,
                                                   @Param("isFuzzy")boolean isFuzzy);
}