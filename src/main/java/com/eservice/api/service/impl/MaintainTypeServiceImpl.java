package com.eservice.api.service.impl;

import com.eservice.api.dao.MaintainTypeMapper;
import com.eservice.api.model.maintain_type.MaintainType;
import com.eservice.api.service.MaintainTypeService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/10.
*/
@Service
@Transactional
public class MaintainTypeServiceImpl extends AbstractService<MaintainType> implements MaintainTypeService {
    @Resource
    private MaintainTypeMapper maintainTypeMapper;

}
