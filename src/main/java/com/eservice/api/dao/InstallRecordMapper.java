package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.install_record.InstallRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InstallRecordMapper extends Mapper<InstallRecord> {

    List<InstallRecord> selectWaitForProcess();

    InstallRecord selectByNameplate(@Param("nameplate")String nameplate);
}