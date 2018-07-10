package com.eservice.api.service.impl;

import com.eservice.api.dao.InstallLibMapper;
import com.eservice.api.model.install_lib.InstallLib;
import com.eservice.api.service.InstallLibService;
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
public class InstallLibServiceImpl extends AbstractService<InstallLib> implements InstallLibService {
    @Resource
    private InstallLibMapper installLibMapper;

}
