package com.eservice.api.service.impl;

import com.eservice.api.dao.InstallMembersMapper;
import com.eservice.api.model.install_members.InstallMembers;
import com.eservice.api.model.user.User;
import com.eservice.api.service.InstallMembersService;
import com.eservice.api.core.AbstractService;
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
public class InstallMembersServiceImpl extends AbstractService<InstallMembers> implements InstallMembersService {
    @Resource
    private InstallMembersMapper installMembersMapper;

    public List<User> getMembersByInstallRecordId(String installRecordId){
        return installMembersMapper.getMembersByInstallRecordId(installRecordId);
    }
}
