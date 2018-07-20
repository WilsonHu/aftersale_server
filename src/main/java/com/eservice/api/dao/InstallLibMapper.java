package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.install_lib.InstallLib;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InstallLibMapper extends Mapper<InstallLib> {

    List<InstallLib> selectLibList(@Param("isBaseLib") String isBaseLib, @Param("installLibName") String installLibName);

}