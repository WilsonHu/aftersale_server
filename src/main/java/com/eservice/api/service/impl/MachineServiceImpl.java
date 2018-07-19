package com.eservice.api.service.impl;

import com.eservice.api.dao.MachineMapper;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.service.MachineService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/19.
*/
@Service
@Transactional
public class MachineServiceImpl extends AbstractService<Machine> implements MachineService {
    @Resource
    private MachineMapper machineMapper;

    public List<Machine> selectByAccount(String account){
       return  machineMapper.selectByAccount(account);
    }

}
