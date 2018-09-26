package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.install_record.InstallRecord;
import com.eservice.api.model.install_record.InstallRecordInfo;
import com.eservice.api.model.machine.Machine;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

public interface InstallRecordMapper extends Mapper<InstallRecord> {

    List<InstallRecord> selectByNameplate(@Param("nameplate")String nameplate);

    List<InstallRecordInfo> getInstallDetail(@Param("nameplate")String nameplate);

    List<InstallRecordInfo> getInstallRecordInfoList(@Param("nameplate")String nameplate,
                                                     @Param("orderNum")String orderNum,
                                                     @Param("machineType")String machineType,
                                                     @Param("agent")String agent,
                                                     @Param("installStatus")String installStatus,
                                                     @Param("installRecordCustomerName")String installRecordCustomerName,
                                                     @Param("installChargePersonName")String installChargePersonName,
                                                     @Param("query_start_install_plan_date")String query_start_install_plan_date,
                                                     @Param("query_finish_install_plan_date")String query_finish_install_plan_date,
                                                     @Param("query_start_facory_date")String query_start_facory_date,
                                                     @Param("query_finish_facory_date")String query_finish_facory_date,
                                                     @Param("query_start_install_actual_time")String query_start_install_actual_time,
                                                     @Param("query_finish_install_actual_time")String query_finish_install_actual_time,
                                                     @Param("installRecordId")String installRecordId,
                                                     @Param("machineCustomerName")String machineCustomerName,
                                                     @Param("isFuzzy")boolean isFuzzy);

    List<InstallRecordInfo> selectInstallTaskByUser(@Param("userName") String userName);
}