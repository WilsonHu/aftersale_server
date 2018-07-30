package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.repair_record.RepairRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairRecordMapper extends Mapper<RepairRecord> {

    List<RepairRecord> selectByNameplate(@Param("nameplate")String nameplate);
}