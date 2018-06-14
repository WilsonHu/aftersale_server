package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.user.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends Mapper<User> {

    User selectByAccount(@Param("account") String account);
}