package com.eservice.api.model.install_record;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "install_record")
public class InstallRecordInfo extends InstallRecord {

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getNeedleNum() {
        return needleNum;
    }

    public void setNeedleNum(String needleNum) {
        this.needleNum = needleNum;
    }

    public String getxDistance() {
        return xDistance;
    }

    public void setxDistance(String xDistance) {
        this.xDistance = xDistance;
    }

    public String getyDistance() {
        return yDistance;
    }

    public void setyDistance(String yDistance) {
        this.yDistance = yDistance;
    }

    public String getHeadDistance() {
        return headDistance;
    }

    public void setHeadDistance(String headDistance) {
        this.headDistance = headDistance;
    }

    public String getHeadNum() {
        return headNum;
    }

    public void setHeadNum(String headNum) {
        this.headNum = headNum;
    }

    public String getLoadinglist() {
        return loadinglist;
    }

    public void setLoadinglist(String loadinglist) {
        this.loadinglist = loadinglist;
    }

    public String getGeoLocation() {
        return geoLocation;
    }

    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getCustomerAddressInInstallRecord() {
        return customerAddressInInstallRecord;
    }

    public void setCustomerAddressInInstallRecord(String customerAddressInInstallRecord) {
        this.customerAddressInInstallRecord = customerAddressInInstallRecord;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getFacoryDate() {
        return facoryDate;
    }

    public void setFacoryDate(Date facoryDate) {
        this.facoryDate = facoryDate;
    }

    /**
     * 机型
     */
    @Column(name = "machine_type")
    private String machineType;

    /**
     * 针数
     */
    @Column(name = "needle_num")
    private String needleNum;

    /**
     * x行程
     */
    @Column(name = "x_distance")
    private String xDistance;

    /**
     * y行程
     */
    @Column(name = "y_distance")
    private String yDistance;

    /**
     * 头距
     */
    @Column(name = "head_distance")
    private String headDistance;

    /**
     * 头数
     */
    @Column(name = "head_num")
    private String headNum;

    /**
     * 装车单路径，共用流程管理系统的装车单，老机器允许空
     */
    private String loadinglist;

    /**
     * 机器的地理位置，经纬度，考虑在调试完成时上传，备用地图显示
     */
    @Column(name = "geo_location")
    private String geoLocation;

    /**
     * 该次安装的安装联系人地址（不是机器的地址）
     */
    private String customerAddressInInstallRecord;

    /**
     * 对应的订单号,来自sinsim_db.machine_order.order_num
     */
    @Column(name = "order_num")
    private String orderNum;
    /**
     * 出厂日期，老机器允许空
     */
    @Column(name = "facory_date")
    private Date facoryDate;

    /**
     * customer索引转换实际名字,来自 machine.customer-->user.name
     */
    private String machineCustomerName;

    /**
     * customer索引转换实际名字,来自 install_Record.customer-->user.name
     */
    private String installRecordCustomerName;

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
    private String installChargePersonName;


    public String getInstallChargePersonName() {
        return installChargePersonName;
    }

    public void setInstallChargePersonName(String installChargePersonName) {
        this.installChargePersonName = installChargePersonName;
    }

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

    public String getInstallRecordCustomerName() {
        return installRecordCustomerName;
    }

    public void setInstallRecordCustomerName(String installRecordCustomerName) {
        this.installRecordCustomerName = installRecordCustomerName;
    }

    public String getMachineCustomerPhone() {
        return machineCustomerPhone;
    }

    public void setMachineCustomerPhone(String machineCustomerPhone) {
        this.machineCustomerPhone = machineCustomerPhone;
    }

    /**
     * 调试员负责人电话
     */
    private String installChargePersonPhone;

    public void setInstallChargePersonPhone(String installChargePersonPhone) {
        this.installChargePersonPhone = installChargePersonPhone;
    }

    public String getInstallChargePersonPhone() {
        return installChargePersonPhone;
    }

    /**
     * 客户评价信息中的分数
     */
    private String installFeedbackCustomerMark;

    public void setInstallFeedbackCustomerMark(String installFeedbackCustomerMark) {
        this.installFeedbackCustomerMark = installFeedbackCustomerMark;
    }

    public String getInstallFeedbackCustomerMark() {
        return installFeedbackCustomerMark;
    }

    /**
     * 客户评价信息中的建议内容
     */
    private String installFeedbackCustomerSuggestion;

    public void setInstallFeedbackCustomerSuggestion(String installFeedbackCustomerSuggestion) {
        this.installFeedbackCustomerSuggestion = installFeedbackCustomerSuggestion;
    }

    public String getInstallFeedbackCustomerSuggestion() {
        return installFeedbackCustomerSuggestion;
    }

    /**
     * 代理商ID号
     */
    private String machineAgentId;

    public String getMachineAgentId() {
        return machineAgentId;
    }

    public void setMachineAgentId(String machineAgentId) {
        this.machineAgentId = machineAgentId;
    }

    /**
     * 客户的公司ID
     */
    private String machineCustomerCompanyId;

    public String getMachineCustomerCompanyId() {
        return machineCustomerCompanyId;
    }

    public void setMachineCustomerCompanyId(String machineCustomerCompanyId) {
        this.machineCustomerCompanyId = machineCustomerCompanyId;
    }
}