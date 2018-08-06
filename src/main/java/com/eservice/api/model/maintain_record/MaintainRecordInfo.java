package com.eservice.api.model.maintain_record;

import javax.persistence.Column;
import java.util.Date;

public class MaintainRecordInfo extends MaintainRecord {


    public String getMaintainChargePersonName() {
        return maintainChargePersonName;
    }

    public void setMaintainChargePersonName(String maintainChargePersonName) {
        this.maintainChargePersonName = maintainChargePersonName;
    }

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

    public String getCustomerAddressInMaintainRecord() {
        return customerAddressInMaintainRecord;
    }

    public void setCustomerAddressInMaintainRecord(String customerAddressInMaintainRecord) {
        this.customerAddressInMaintainRecord = customerAddressInMaintainRecord;
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
    private String customerAddressInMaintainRecord;

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
     * 保养员负责人
     */
    private String maintainChargePersonName;


    /**
     * customer索引转换实际名字,来自 machine.customer-->user.name
     */
    private String machineCustomerName;

    /**
     * 索引转换实际名字,来自 machine.customer-->user-->agent.name
     */
    private String machineAgentName;

    public String getMachineAgentName() {
        return machineAgentName;
    }

    public void setMachineAgentName(String machineAgentName) {
        this.machineAgentName = machineAgentName;
    }

    private String machineAgentPhone;

    public String getMachineAgentPhone() {
        return machineAgentPhone;
    }

    public void setMachineAgentPhone(String machineAgentPhone) {
        this.machineAgentPhone = machineAgentPhone;
    }


    public String getMachineCustomerName() {
        return machineCustomerName;
    }

    public void setMachineCustomerName(String machineCustomerName){
        this.machineCustomerName = machineCustomerName;
    }


    /**
     * 该次保养的客户联系人的名字
     */
    private String customerNameInMaintainRecord;

    public String getCustomerNameInMaintainRecord() {
        return customerNameInMaintainRecord;
    }

    public void setCustomerNameInMaintainRecord(String customerNameInMaintainRecord) {
        this.customerNameInMaintainRecord = customerNameInMaintainRecord;
    }

    /**
     * 该次保养的客户联系人的电话
     */
    private String customerPhoneInMaintainRecord;

    public String getCustomerPhoneInMaintainRecord() {
        return customerPhoneInMaintainRecord;
    }

    public void setCustomerPhoneInMaintainRecord(String customerPhoneInMaintainRecord) {
        this.customerPhoneInMaintainRecord = customerPhoneInMaintainRecord;
    }

    /**
     * 保养员负责人电话
     */
    private String maintainChargePersonPhone;

    public void setMaintainChargePersonPhone(String maintainChargePersonPhone) {
        this.maintainChargePersonPhone = maintainChargePersonPhone;
    }

    public String getMaintainChargePersonPhone(){
        return maintainChargePersonPhone;
    }

    /**
     * 机器所属客户的电话
     */
    private String machineCustomerPhone;

    public String getMachineCustomerPhone() {
        return machineCustomerPhone;
    }

    public void setMachineCustomerPhone(String machineCustomerPhone) {
        this.machineCustomerPhone = machineCustomerPhone;
    }

}