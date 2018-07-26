package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.maintain_lib.MaintainLib;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaintainLibMapper extends Mapper<MaintainLib> {
    List<MaintainLib> selectLibList(@Param("maintainType") String maintainType, @Param("maintainLibName") String maintainLibName);
    Integer deleteByName(@Param("maintainLibName") String maintainLibName);
}