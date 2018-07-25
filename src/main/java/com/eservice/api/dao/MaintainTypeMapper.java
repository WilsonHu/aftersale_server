package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.maintain_type.MaintainType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaintainTypeMapper extends Mapper<MaintainType> {
    List<MaintainType> getListByParam(@Param("name") String name);
}