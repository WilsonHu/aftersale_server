package com.eservice.api.service.impl;

import com.eservice.api.dao.PartsInfoMapper;
import com.eservice.api.model.parts_info.PartsInfo;
import com.eservice.api.service.PartsInfoService;
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
public class PartsInfoServiceImpl extends AbstractService<PartsInfo> implements PartsInfoService {
    @Resource
    private PartsInfoMapper partsInfoMapper;

}
