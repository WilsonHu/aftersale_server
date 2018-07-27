package com.eservice.api.model.install_record;

import com.eservice.api.model.machine.Machine;

import javax.persistence.*;
import java.util.Date;

@Table(name = "install_record")
public class InstallRecordInfo extends Machine {
    /**
     * customer索引转换实际名字,来自 machine.customer-->user.name
     */
    private String machineCustomerName;

    /**
     * customer索引转换实际名字,来自 machine.customer-->user.phone
     */
    private String machineCustomerPhone;

    /**
     * 索引转换实际名字,来自 machine.customer-->user-->agent.name
     */
    private String machineAgentName;

    /**
     * 索引转换实际名字,来自 machine.customer-->user-->agent.phone
     */
    private String machineAgentPhone;

    /**
     * 该次调试的调试联系人
     */
    private String customerInInstallRecord;

    /**
     * 该次调试的调试联系人电话
     */
    private String customerPhoneInInstallRecord;

    /**
     * 调试员
     */
    private String installChargePerson;

    public String getMachineAgentName() {
        return machineAgentName;
    }

    public void setMachineAgentName(String machineAgentName){
        this.machineAgentName = machineAgentName;
    }

    public String getCustomerInInstallRecord() {
        return customerInInstallRecord;
    }

    public void setCustomerInInstallRecord(String customerInInstallRecord) {
        this.customerInInstallRecord = customerInInstallRecord;
    }
    public String getCustomerPhoneInInstallRecord() {
        return customerPhoneInInstallRecord;
    }

    public void setCustomerPhoneInInstallRecord(String customerPhoneInInstallRecord) {
        this.customerPhoneInInstallRecord = customerPhoneInInstallRecord;
    }

    public String getInstallChargePerson() {
        return installChargePerson;
    }

    public void setInstallChargePerson(String installChargePerson) {
        this.installChargePerson = installChargePerson;
    }

    public String getMachineAgentPhone() {
        return machineAgentPhone;
    }

    public void setMachineAgentPhone(String machineAgentPhone) {
        this.machineAgentPhone = machineAgentPhone;
    }

    public String getMachineCustomerName() {
        return machineCustomerName;
    }

    public void setMachineCustomerName(String machineCustomerName) {
        this.machineCustomerName = machineCustomerName;
    }

    public String getMachineCustomerPhone() {
        return machineCustomerPhone;
    }

    public void setMachineCustomerPhone(String machineCustomerPhone) {
        this.machineCustomerPhone = machineCustomerPhone;
    }
}