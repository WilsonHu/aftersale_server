package com.eservice.api.model.machine;

import java.util.Date;

public class MachineInfo extends Machine {

    /**
     * customer索引转换实际名字,来自 machine.customer-->user.name
     */
    private String customerName;

    /**
     * 索引转换实际名字,来自 machine.customer-->user-->agent.name
     */
    private String agent;

    /**
     * 索引转换实际名字,来自 machine.nameplate-->install_record.installChargePerson-->user.name
     */
    private String installChargePerson;

    /**
     * 来自 machine.nameplate-->maintain_record.maintain_record.maintain_charge_person
     */
    private String repairChargePerson;

    /**
     * 安装计划上门日期
     */
    private Date installPlanDate;

    /**
     * 实际完成安装时间
     */
    private Date InstallActualTime;

    /**
     * 故障部位
     */
    private String issuePosition;

    /**
     * 是否在保修期
     */
    private String inWarrantyPeriod;

    /**
     * 代理商改派
     */
    private String forwardInfo;

    /**
     * 报修日期
     */
    private Date repairRequestDate;
    /**
     * 实际完成维修时间...
     */
    private Date repairActualTime;


    private String maintainChargePerson;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAgent() {
        return agent;
    }

     public void setAgent(String agent) {
        this.agent = agent;
     }

     public String getInstallChargePerson(){
        return this.installChargePerson;
     }

     public void setInstallChargePerson(String installChargePerson){
         this.installChargePerson = installChargePerson;
     }

     public String getRepairChargePerson(){
         return this.repairChargePerson;
     }

     public void setRepairChargePerson(String repairChargePerson1){
         this.repairChargePerson = repairChargePerson1;
     }

     public  Date getInstallPlanDate(){
         return this.installPlanDate;
     }

     public void setInstallPlanDate(Date installPlanDate1){
         this.installPlanDate= installPlanDate1;
     }

    public Date getInstallActualTime() {
        return InstallActualTime;
    }

    public void setInstallActualTime(Date installActualTime){
         this.InstallActualTime = installActualTime;
    }

    public String getForwardInfo() {
        return forwardInfo;
    }

    public void setForwardInfo(String forwardInfo){
        this.forwardInfo = forwardInfo;
    }

    public Date getRepairRequestDate() {
        return this.repairRequestDate;
    }

    public void setRepairRequestDate(Date repairRequestDate){
        this.repairRequestDate = repairRequestDate;
    }

    public String getIssuePosition(){
        return this.issuePosition;
    }

    public void setIssuePosition(String issuePosition){
        this.issuePosition = issuePosition;
    }

    public String getInWarrantyPeriod(){
        return this.inWarrantyPeriod;
    }

    public void setInWarrantyPeriod(String inWarrantyPeriod){
        this.inWarrantyPeriod = inWarrantyPeriod;
    }

    public Date getRepairActualTime(){
        return this.repairActualTime;
    }

    public void setRepairActualTime( Date repairActualTime){
        this.repairActualTime = repairActualTime;
    }

    public String  getMaintainChargePerson(){
        return  this.maintainChargePerson;
    }

    public void setMaintainChargePerson(String maintainChargePerson){
        this.maintainChargePerson = maintainChargePerson;
    }
}