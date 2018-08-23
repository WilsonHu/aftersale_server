package com.eservice.api.service.impl;

import com.eservice.api.core.AbstractService;
import com.eservice.api.dao.UserMapper;
import com.eservice.api.model.user.StaffInfo;
import com.eservice.api.model.user.User;
import com.eservice.api.model.user.UserInfo;
import com.eservice.api.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/10.
*/
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService,UserDetailsService {
    @Resource
    private UserMapper userMapper;
    public User selectByAccount(String account){
        return userMapper.selectByAccount(account);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectByAccount(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return user;
    }

    public User requestLogin(String account, String password,String unionid) {
        return userMapper.requestLogin(account, password,unionid);
    }

    public List<UserInfo> getUsersByType(String roleId, String agentId, String customerCompany){
        return userMapper.getUsersByType(roleId, agentId, customerCompany);
    }

    public List<User> selectUsers(Integer currentUserAgent, String account, String name, Integer roleId, Boolean isAgent, Integer agent, String valid, Integer userType) {
        return userMapper.selectUsers(currentUserAgent, account, name, roleId, isAgent, agent, valid, userType);
    }

    public List<StaffInfo> getStaffByParam(String agentId)
    {
        return userMapper.getStaffByParam(agentId);
    }
  }
