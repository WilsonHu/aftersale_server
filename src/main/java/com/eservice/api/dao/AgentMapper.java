package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.agent.Agent;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AgentMapper extends Mapper<Agent> {

    List<Agent> findByName(@Param("name") String name, @Param("isFuzzy") boolean isFuzzy);
}