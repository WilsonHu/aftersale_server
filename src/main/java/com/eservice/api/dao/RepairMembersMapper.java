package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.repair_members.RepairMembers;
import com.eservice.api.model.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RepairMembersMapper extends Mapper<RepairMembers> {

    List<User> getMembersByRepairRecordId(@Param("repairRecordId")String repairRecordId);
}