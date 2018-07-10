package com.eservice.api.service.impl;

import com.eservice.api.dao.ForwardInfoMapper;
import com.eservice.api.model.forward_info.ForwardInfo;
import com.eservice.api.service.ForwardInfoService;
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
public class ForwardInfoServiceImpl extends AbstractService<ForwardInfo> implements ForwardInfoService {
    @Resource
    private ForwardInfoMapper forwardInfoMapper;

}
