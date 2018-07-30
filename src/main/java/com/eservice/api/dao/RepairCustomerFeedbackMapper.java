package com.eservice.api.dao;

import com.eservice.api.core.Mapper;
import com.eservice.api.model.repair_customer_feedback.RepairCustomerFeedback;

public interface RepairCustomerFeedbackMapper extends Mapper<RepairCustomerFeedback> {

    void saveAndGetID(RepairCustomerFeedback repairCustomerFeedback);

}