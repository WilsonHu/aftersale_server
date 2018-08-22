package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.maintain_members.MaintainMembers;
import com.eservice.api.model.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MaintainMembersMapper extends Mapper<MaintainMembers> {
    List<User> getMembersByMaintainRecordId(@Param("maintainRecordId")String maintainRecordId);
}