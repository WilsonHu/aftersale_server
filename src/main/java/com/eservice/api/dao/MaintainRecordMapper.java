package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.maintain_record.MaintainRecord;
import com.eservice.api.model.maintain_record.MaintainRecordInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaintainRecordMapper extends Mapper<MaintainRecord> {

    List<MaintainRecord>  selectByNameplate(@Param("nameplate")String nameplate);

    List<MaintainRecordInfo> getMaintainRecordInfoList(@Param("nameplate")String nameplate,
                                                       @Param("orderNum")String orderNum,
                                                       @Param("agent")String agent,
                                                       @Param("maintainStatus")String maintainStatus,
                                                       @Param("customerName")String customerName,
                                                       @Param("machineType")String machineType,
                                                       @Param("maintainChargePerson")String maintainChargePerson,
                                                       @Param("query_start_time_maintain")String query_start_time_maintain,
                                                       @Param("query_finish_time_maintain")String query_finish_time_maintain,
                                                       @Param("isFuzzy")boolean isFuzzy);

    List<Machine> selectMaintainTaskMachine(@Param("userName") String userName);
}