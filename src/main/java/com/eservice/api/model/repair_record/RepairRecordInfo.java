package com.eservice.api.model.repair_record;

import javax.persistence.*;
import java.util.Date;

public class RepairRecordInfo extends RepairRecord {

    /**
     * 对应的订单号,来自sinsim_db.machine_order.order_num
     */
    @Column(name = "order_num")
    private String orderNum;

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * customer索引转换实际名字,来自 machine.customer-->user.name
     */
    private String machineCustomerName;

    public String getMachineCustomerName() {
        return machineCustomerName;
    }

    public void setMachineCustomerName(String machineCustomerName) {
        this.machineCustomerName = machineCustomerName;
    }

    /**
     * 索引转换实际名字,来自 machine.customer-->user-->agent.name
     */
    private String machineAgentName;

    public String getMachineAgentName() {
        return machineAgentName;
    }

    public void setMachineAgentName(String machineAgentName){
        this.machineAgentName = machineAgentName;
    }

    /**
     * 出厂日期（就是发货日期），老机器允许空
     */
    @Column(name = "facory_date")
    private Date facoryDate;


    public Date getFacoryDate() {
        return facoryDate;
    }

    public void setFacoryDate(Date facoryDate) {
        this.facoryDate = facoryDate;
    }

    /**
     * 故障部位，来自于repair_actual_info.issue_position
     */
    private String issuePositionName;

    public String getIssuePositionName() {
        return issuePositionName;
    }

    public void setIssuePositionName(String issuePositionName){
        this.issuePositionName = issuePositionName;
    }

    /**
     * 维修员
     */
    private String repairChargePersonName;

    public void setRepairChargePersonName(String repairChargePersonName) {
        this.repairChargePersonName = repairChargePersonName;
    }

    public String getRepairChargePersonName() {
        return repairChargePersonName;
    }

    /**
     * customer索引转换实际名字,来自 machine.customer-->user.phone
     */
    private String machineCustomerPhone;

    public void setMachineCustomerPhone(String machineCustomerPhone) {
        this.machineCustomerPhone = machineCustomerPhone;
    }

    public String getMachineCustomerPhone() {
        return machineCustomerPhone;
    }

    /**
     * 机器的客户的代理商的电话,来自 machine.customer-->user-->agent.phone
     */
    private String machineAgentPhone;

    public void setMachineAgentPhone(String machineAgentPhone) {
        this.machineAgentPhone = machineAgentPhone;
    }

    public String getMachineAgentPhone() {
        return machineAgentPhone;
    }

    /**
     * 机型
     */
    private String machineType;

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getMachineType() {
        return machineType;
    }

    /**
     * 针数
     */
//    @Column(name = "needle_num")
    private String needleNum;

    public String getNeedleNum() {
        return needleNum;
    }

    public void setNeedleNum(String needleNum) {
        this.needleNum = needleNum;
    }

    /**
     * 头数
     */
//    @Column(name = "head_num")
    private String headNum;

    public String getHeadNum() {
        return headNum;
    }

    public void setHeadNum(String headNum) {
        this.headNum = headNum;
    }

//    @Column(name = "head_distance")
    private String headDistance;

    public String getHeadDistance() {
        return headDistance;
    }

    public void setHeadDistance(String headDistance) {
        this.headDistance = headDistance;
    }

//    @Column(name = "x_distance")
    private String xDistance;

    public String getxDistance() {
        return xDistance;
    }

    public void setxDistance(String xDistance) {
        this.xDistance = xDistance;
    }

//    @Column(name = "y_distance")
    private String yDistance;

    public String getyDistance() {
        return yDistance;
    }

    public void setyDistance(String yDistance) {
        this.yDistance = yDistance;
    }

    /**
     * 该次维修的维修联系人
     */
    private String customerNameInRepairRecord;

    /**
     * 该次维修的维修联系人电话
     */
    private String customerPhoneInRepairRecord;

    public void setCustomerPhoneInRepairRecord(String customerPhoneInRepairRecord) {
        this.customerPhoneInRepairRecord = customerPhoneInRepairRecord;
    }

    public String getCustomerPhoneInRepairRecord(){
        return customerPhoneInRepairRecord;
    }

    public String getCustomerNameInRepairRecord() {
        return customerNameInRepairRecord;
    }

    public void setCustomerNameInRepairRecord(String customerNameInRepairRecord){
        this.customerNameInRepairRecord = customerNameInRepairRecord;
    }
    /**
     * 该次维修的维修联系人地址,就是机器的地址
     */
    private String machineAddress;

    public void setMachineAddress(String machineAddress) {
        this.machineAddress = machineAddress;
    }

    public String getMachineAddress() {
        return machineAddress;
    }
}



















