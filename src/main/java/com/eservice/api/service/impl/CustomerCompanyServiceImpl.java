package com.eservice.api.service.impl;

import com.eservice.api.dao.CustomerCompanyMapper;
import com.eservice.api.model.customer_company.CustomerCompany;
import com.eservice.api.service.CustomerCompanyService;
import com.eservice.api.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/08/01.
*/
@Service
@Transactional
public class CustomerCompanyServiceImpl extends AbstractService<CustomerCompany> implements CustomerCompanyService {
    @Resource
    private CustomerCompanyMapper customerCompanyMapper;

}
