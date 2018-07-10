package com.eservice.api.service.impl;

import com.eservice.api.dao.ContactsMapper;
import com.eservice.api.model.contacts.Contacts;
import com.eservice.api.service.ContactsService;
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
public class ContactsServiceImpl extends AbstractService<Contacts> implements ContactsService {
    @Resource
    private ContactsMapper contactsMapper;

}
