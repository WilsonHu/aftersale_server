package com.eservice.api.service.impl;

import com.eservice.api.dao.RepairMembersMapper;
import com.eservice.api.model.repair_members.RepairMembers;
import com.eservice.api.model.user.User;
import com.eservice.api.service.RepairMembersService;
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
public class RepairMembersServiceImpl extends AbstractService<RepairMembers> implements RepairMembersService {
    @Resource
    private RepairMembersMapper repairMembersMapper;

    public List<User> getMembersByRepairRecordId(String repairRecordId){
        return repairMembersMapper.getMembersByRepairRecordId(repairRecordId);
    }
}
