package com.eservice.api.service.impl;

import com.eservice.api.dao.KnowledgePicturesMapper;
import com.eservice.api.model.knowledge_pictures.KnowledgePictures;
import com.eservice.api.service.KnowledgePicturesService;
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
public class KnowledgePicturesServiceImpl extends AbstractService<KnowledgePictures> implements KnowledgePicturesService {
    @Resource
    private KnowledgePicturesMapper knowledgePicturesMapper;

}
