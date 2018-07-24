package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.machine.MachineInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MachineMapper extends Mapper<Machine> {
    List<Machine> selectByAccount(@Param("account") String account);

    List<MachineInfo> getSaledMachineInfoList(@Param("nameplate") String nameplate,
                                              @Param("orderNum") String orderNum,
                                              @Param("machineType") String machineType,
                                              @Param("agent") String agent,
                                              @Param("status") String status,
                                              @Param("customerName") String customerName ,
                                              @Param("query_start_time_install") String query_start_time_install,
                                              @Param("query_finish_time_install") String query_finish_time_install,
                                              @Param("query_start_time_maintain") String query_start_time_maintain,
                                              @Param("query_finish_time_maintain") String query_finish_time_maintain,
                                              @Param("query_start_time_repair") String query_start_time_repair,
                                              @Param("query_finish_time_repair") String query_finish_time_repair,
                                              @Param("isFuzzy") boolean isFuzzy);
}