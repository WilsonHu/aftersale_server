package com.eservice.api.service.impl;

import com.eservice.api.dao.KnowledgeLibMapper;
import com.eservice.api.model.knowledge_lib.KnowledgeLib;
import com.eservice.api.service.KnowledgeLibService;
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
public class KnowledgeLibServiceImpl extends AbstractService<KnowledgeLib> implements KnowledgeLibService {
    @Resource
    private KnowledgeLibMapper knowledgeLibMapper;

}
