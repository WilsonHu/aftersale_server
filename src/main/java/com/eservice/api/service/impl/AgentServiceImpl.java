package com.eservice.api.service.impl;

import com.eservice.api.dao.AgentMapper;
import com.eservice.api.model.agent.Agent;
import com.eservice.api.service.AgentService;
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
public class AgentServiceImpl extends AbstractService<Agent> implements AgentService {
    @Resource
    private AgentMapper agentMapper;

}
