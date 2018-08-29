package com.eservice.api.model.parts_info;

import javax.persistence.*;
import java.util.Date;

public class PartsAllInfo extends PartsInfo {

    private String sendbackConfirmedPersonName;

    public void setSendbackConfirmedPersonName(String sendbackConfirmedPersonName) {
        this.sendbackConfirmedPersonName = sendbackConfirmedPersonName;
    }

    public String getSendbackConfirmedPersonName() {
        return sendbackConfirmedPersonName;
    }

    /**
     * 维修负责人
     */
    private String repairChargePersonName;

    public void setRepairChargePersonName(String repairChargePersonName) {
        this.repairChargePersonName = repairChargePersonName;
    }

    public String getRepairChargePersonName() {
        return repairChargePersonName;
    }

    /**
     * 该次维修的维修联系人
     */
    private String customerNameInRepairRecord;

    public String getCustomerNameInRepairRecord() {
        return customerNameInRepairRecord;
    }

    public void setCustomerNameInRepairRecord(String customerNameInRepairRecord){
        this.customerNameInRepairRecord = customerNameInRepairRecord;
    }
}