package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.maintain_record.MaintainRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaintainRecordMapper extends Mapper<MaintainRecord> {

    MaintainRecord selectByNameplate(@Param("nameplate")String nameplate);

    List<MaintainRecord> selectWaitProcessForGuest(@Param("contacter") String contacter);
}