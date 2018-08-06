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
     * 该次维修的维修联系人地址（不是机器的地址）
     */
    private String customerAddressInRepairRecord;

    public void setCustomerAddressInRepairRecord(String customerAddressInRepairRecord) {
        this.customerAddressInRepairRecord = customerAddressInRepairRecord;
    }

    public String getCustomerAddressInRepairRecord() {
        return customerAddressInRepairRecord;
    }

    /**
     * 报修信息的标题
     */
    private String repairRequestTitle;

    public void setRepairRequestTitle(String repairRequestTitle){
        this.repairRequestTitle = repairRequestTitle;
    }

    public String getRepairRequestTitle(){
        return repairRequestTitle;
    }

    /**
     * 报修信息的内容描述
     */
    private String repairRequestContent;

    public void setRepairRequestContent(String repairRequestContent){
        this.repairRequestContent = repairRequestContent;
    }

    public String getRepairRequestContent(){
        return repairRequestContent;
    }

    /**
     * 报修信息的语音（保存的地址）
     */
    private String repairRequestVoice;

    public String getRepairRequestVoice(){
        return repairRequestVoice;
    }

    public void setRepairRequestVoice(String repairRequestVoice){
        this.repairRequestVoice = repairRequestVoice;
    }


    /**
     * 报修信息的照片（保存的地址）
     */
    private String repairRequestPictures;

    public void setRepairRequestPictures(String repairRequestPictures) {
        this.repairRequestPictures = repairRequestPictures;
    }

    public String getRepairRequestPictures() {
        return repairRequestPictures;
    }

    /**
     * 实际维修的问题描述
     */
    private String repairActualIssueDescription;

    public String getRepairActualIssueDescription() {
        return repairActualIssueDescription;
    }

    public void setRepairActualIssueDescription(String repairActualIssueDescription) {
        this.repairActualIssueDescription = repairActualIssueDescription;
    }

    /**
     * 实际维修的处理方法
     */
    private String repairActualMethod;

    public String getRepairActualMethod() {
        return repairActualMethod;
    }

    public void setRepairActualMethod(String repairActualMethod) {
        this.repairActualMethod = repairActualMethod;
    }


    /**
     * 实际维修的处理方法
     */
    private String afterResolvePictures;

    public void setAfterResolvePictures(String afterResolvePictures) {
        this.afterResolvePictures = afterResolvePictures;
    }

    public String getAfterResolvePictures() {
        return afterResolvePictures;
    }

    /**
     * 维修员负责人电话
     */
    private String repairChargePersonPhone;

    public void setRepairChargePersonPhone(String repairChargePersonPhone) {
        this.repairChargePersonPhone = repairChargePersonPhone;
    }

    public String getRepairChargePersonPhone() {
        return repairChargePersonPhone;
    }

    /**
     * 配件名称，可以有多个配件要寄回.
     */
    private String partsName;

    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    public String getPartsName() {
        return partsName;
    }

    /**
     * 配件供应商
     */
    private String partsSupplier;

    public void setPartsSupplier(String partsSupplier) {
        this.partsSupplier = partsSupplier;
    }

    public String getPartsSupplier() {
        return partsSupplier;
    }

    /**
     * 配件状态
     */
    public String partsStatus;

    public String getPartsStatus() {
        return partsStatus;
    }

    public void setPartsStatus(String partsStatus) {
        this.partsStatus = partsStatus;
    }

    /**
     * 配件回寄的快递单号
     */
    private String sendbackTrackingNumber;

    public void setSendbackTrackingNumber(String sendbackTrackingNumber) {
        this.sendbackTrackingNumber = sendbackTrackingNumber;
    }

    public String getSendbackTrackingNumber() {
        return sendbackTrackingNumber;
    }

    /**
     * 配件回寄的快递照片文件保存地址
     */
    private String sendbackTrackingPictrue;

    public void setSendbackTrackingPictrue(String sendbackTrackingPictrue) {
        this.sendbackTrackingPictrue = sendbackTrackingPictrue;
    }

    public String getSendbackTrackingPictrue() {
        return sendbackTrackingPictrue;
    }

    /**
     * 配件的寄回日期
     */
    private Date sendbackDate;

    public void setSendbackDate(Date sendbackDate) {
        this.sendbackDate = sendbackDate;
    }

    public Date getSendbackDate() {
        return sendbackDate;
    }

    private Date sendbackConfirmedTime;

    public Date getSendbackConfirmedTime() {
        return sendbackConfirmedTime;
    }

    public void setSendbackConfirmedTime(Date sendbackConfirmedTime) {
        this.sendbackConfirmedTime = sendbackConfirmedTime;
    }

    /**
     * 回寄后，确认收到的人的名称
     */
    public String  sendbackConfirmedPersonName;

    public void setSendbackConfirmedPersonName(String sendbackConfirmedPersonName) {
        this.sendbackConfirmedPersonName = sendbackConfirmedPersonName;
    }

    public String getSendbackConfirmedPersonName() {
        return sendbackConfirmedPersonName;
    }

    /**
     * 客户评价信息中的分数
     */
    private String repairFeedbackCustomerMark;

    public void setRepairFeedbackCustomerMark(String repairFeedbackCustomerMark) {
        this.repairFeedbackCustomerMark = repairFeedbackCustomerMark;
    }

    public String getRepairFeedbackCustomerMark() {
        return repairFeedbackCustomerMark;
    }

    /**
     * 客户评价信息中的建议内容
     */
    private String repairFeedbackCustomerSuggestion;

    public void setRepairFeedbackCustomerSuggestion(String repairFeedbackCustomerSuggestion) {
        this.repairFeedbackCustomerSuggestion = repairFeedbackCustomerSuggestion;
    }

    public String getRepairFeedbackCustomerSuggestion() {
        return repairFeedbackCustomerSuggestion;
    }

    /**
     * 客户评价信息中的维修结果
     */
    private String repairFeedbackRepairResult;

    public void setRepairFeedbackRepairResult(String repairFeedbackRepairResult) {
        this.repairFeedbackRepairResult = repairFeedbackRepairResult;
    }

    public String getRepairFeedbackRepairResult() {
        return repairFeedbackRepairResult;
    }
}
