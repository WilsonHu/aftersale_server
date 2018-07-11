package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.install_record.InstallRecord;

import java.util.List;

public interface InstallRecordMapper extends Mapper<InstallRecord> {

    List<InstallRecord> selectWaitForProcess();
}