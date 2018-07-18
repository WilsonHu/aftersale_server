package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.machine.Machine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MachineMapper extends Mapper<Machine> {
    List<Machine> selectByAccount(@Param("account") String account);
}