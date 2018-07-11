package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.customer.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper extends Mapper<Customer> {

    Customer requestLogin(@Param("account")String account, @Param("password")String password, @Param("unionid")String unionid);
}