package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.parts_info.PartsAllInfo;
import com.eservice.api.model.parts_info.PartsInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PartsInfoMapper extends Mapper<PartsInfo> {
    List<PartsAllInfo> getPartsInfoByRepairRecordId(@Param("repairRecordId") Integer repairRecordId);
}