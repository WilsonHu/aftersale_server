package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.install_members.InstallMembers;
import com.eservice.api.model.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface InstallMembersMapper extends Mapper<InstallMembers> {

    List<User> getMembersByInstallRecordId(@Param("installRecordId")String installRecordId);
}