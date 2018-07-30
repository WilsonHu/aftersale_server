package com.eservice.api.service.impl;

import com.eservice.api.dao.RepairCustomerFeedbackMapper;
import com.eservice.api.model.repair_customer_feedback.RepairCustomerFeedback;
import com.eservice.api.service.RepairCustomerFeedbackService;
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
public class RepairCustomerFeedbackServiceImpl extends AbstractService<RepairCustomerFeedback> implements RepairCustomerFeedbackService {
    @Resource
    private RepairCustomerFeedbackMapper repairCustomerFeedbackMapper;

    public void saveAndGetID(RepairCustomerFeedback repairCustomerFeedback){
        repairCustomerFeedbackMapper.saveAndGetID(repairCustomerFeedback);
    }

}
