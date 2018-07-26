package com.eservice.api.model.maintain_record;

import com.eservice.api.model.machine.Machine;

import javax.persistence.*;
import java.util.Date;

public class MaintainRecordInfo extends Machine {
    /**
     * customer索引转换实际名字,来自 machine.customer-->user.name
     */
    private String customerName;

    /**
     * 索引转换实际名字,来自 machine.customer-->user-->agent.name
     */
    private String agent;

    /**
     * 保养类型, 其实就是 maintain_lib_name
     */
    private String maintainLibName;

    /**
     * 保养员（负责人）
     */
    private String maintainChargePerson;


    /**
     * 保养计划日期
     */
    private Date maintainDatePlan;

    /**
     * 保养完成日期
     */
    private Date maintainDateActual;

    /**
     * 保养状态
     */
    private String maintainStatus;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent){
        this.agent = agent;
    }

    public String getMaintainLibName(){
        return this.maintainLibName;
    }

    public void setMaintainLibName(String maintainLibName){
        this.maintainLibName = maintainLibName;
    }

    public String getMaintainChargePerson() {
        return maintainChargePerson;
    }

    public void setMaintainChargePerson(String maintainChargePerson){
        this.maintainChargePerson = maintainChargePerson;
    }

    public Date getMaintainDateActual() {
        return maintainDateActual;
    }

    public void setMaintainDateActual(Date maintainDateActual){
        this.maintainDateActual = maintainDateActual;
    }

    public Date getMaintainDatePlan() {
        return maintainDatePlan;
    }

    public void setMaintainDatePlan(Date maintainDatePlan) {
        this.maintainDatePlan = maintainDatePlan;
    }

    public String getMaintainStatus() {
        return maintainStatus;
    }

    public void setMaintainStatus(String maintainStatus) {
        this.maintainStatus = maintainStatus;
    }
}