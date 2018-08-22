package com.eservice.api.service.impl;

import com.eservice.api.dao.MaintainMembersMapper;
import com.eservice.api.model.maintain_members.MaintainMembers;
import com.eservice.api.model.user.User;
import com.eservice.api.service.MaintainMembersService;
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
public class MaintainMembersServiceImpl extends AbstractService<MaintainMembers> implements MaintainMembersService {
    @Resource
    private MaintainMembersMapper maintainMembersMapper;

    public List<User> getMembersByMaintainRecordId(String maintainRecordId){
        return maintainMembersMapper.getMembersByMaintainRecordId(maintainRecordId);
    }
}
