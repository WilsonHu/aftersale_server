package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.user.User;
import com.eservice.api.model.user.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    User selectByAccount(@Param("account") String account);

    User requestLogin(@Param("account")String account, @Param("password")String password, @Param("unionid")String unionid);

    List<UserInfo> getUsersByType(@Param("roleId")String roleId, @Param("agentId")String agentId);

    List<User> selectUsers(@Param("currentUserAgent")Integer currentUserAgent, @Param("account")String account, @Param("name")String name, @Param("roleId")Integer roleId,
                           @Param("isAgent")Boolean isAgent, @Param("agent")Integer agent, @Param("valid")String valid,@Param("userType")Integer userType);
}