package com.eservice.api.service.impl;

import com.eservice.api.dao.MaintainLibMapper;
import com.eservice.api.model.maintain_lib.MaintainLib;
import com.eservice.api.service.MaintainLibService;
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
public class MaintainLibServiceImpl extends AbstractService<MaintainLib> implements MaintainLibService {
    @Resource
    private MaintainLibMapper maintainLibMapper;

}
