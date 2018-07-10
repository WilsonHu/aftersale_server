package com.eservice.api.service.impl;

import com.eservice.api.dao.ExperienceLibMapper;
import com.eservice.api.model.experience_lib.ExperienceLib;
import com.eservice.api.service.ExperienceLibService;
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
public class ExperienceLibServiceImpl extends AbstractService<ExperienceLib> implements ExperienceLibService {
    @Resource
    private ExperienceLibMapper experienceLibMapper;

}
