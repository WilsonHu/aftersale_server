package com.eservice.api.service.impl;

import com.eservice.api.dao.MaintainCustomerFeedbackMapper;
import com.eservice.api.model.maintain_customer_feedback.MaintainCustomerFeedback;
import com.eservice.api.service.MaintainCustomerFeedbackService;
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
public class MaintainCustomerFeedbackServiceImpl extends AbstractService<MaintainCustomerFeedback> implements MaintainCustomerFeedbackService {
    @Resource
    private MaintainCustomerFeedbackMapper maintainCustomerFeedbackMapper;

    public void saveAndGetID(MaintainCustomerFeedback maintainCustomerFeedback){
        maintainCustomerFeedbackMapper.saveAndGetID(maintainCustomerFeedback);
    }
}
