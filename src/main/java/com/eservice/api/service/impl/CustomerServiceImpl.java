package com.eservice.api.service.impl;

import com.eservice.api.dao.CustomerMapper;
import com.eservice.api.model.customer.Customer;
import com.eservice.api.service.CustomerService;
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
public class CustomerServiceImpl extends AbstractService<Customer> implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;

    public Customer requestLogin(String account, String password,String unionid) {
        return customerMapper.requestLogin(account, password,unionid);
    }
}