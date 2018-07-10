package com.eservice.api.service.impl;

import com.eservice.api.dao.MessagePushedMapper;
import com.eservice.api.model.message_pushed.MessagePushed;
import com.eservice.api.service.MessagePushedService;
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
public class MessagePushedServiceImpl extends AbstractService<MessagePushed> implements MessagePushedService {
    @Resource
    private MessagePushedMapper messagePushedMapper;

}
