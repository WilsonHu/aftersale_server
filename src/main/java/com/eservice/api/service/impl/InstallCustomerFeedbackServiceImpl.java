package com.eservice.api.service.impl;

import com.eservice.api.dao.InstallCustomerFeedbackMapper;
import com.eservice.api.model.install_customer_feedback.InstallCustomerFeedback;
import com.eservice.api.service.InstallCustomerFeedbackService;
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
public class InstallCustomerFeedbackServiceImpl extends AbstractService<InstallCustomerFeedback> implements InstallCustomerFeedbackService {
    @Resource
    private InstallCustomerFeedbackMapper installCustomerFeedbackMapper;

}
