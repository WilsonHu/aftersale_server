package com.eservice.api.model.machine;

import java.util.Date;
import javax.persistence.*;

public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 技术部填入的机器编号（铭牌），来自于sinsim_db.machine. nameplate
     */
    private String nameplate;

    /**
     * 对应的订单号,来自sinsim_db.machine_order.order_num
     */
    @Column(name = "order_num")
    private String orderNum;

    /**
     * 对应的合同号，可能用不到，允许空
     */
    @Column(name = "contract_num")
    private String contractNum;

    /**
     * 机器的地理位置，经纬度，考虑在调试完成时上传，备用地图显示
     */
    @Column(name = "geo_location")
    private String geoLocation;

    /**
     * 机器地址，厂家门牌号
     */
    private String address;

    /**
     * 机器状态 0：未绑定，1：已绑定，2：待安装，3：正常工作状态（已安装、已保养，已维修），4：待保养，5：待修理，6： 待审核
     */
    private String status;

    /**
     * 老机器，客户拍的铭牌照片的保存地址；非老机器可以为空
     */
    @Column(name = "nameplate_picture")
    private String nameplatePicture;

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
     * 合同里的客户
     */
    @Column(name = "customer_in_contract")
    private String customerInContract;

    /**
     * 机器和客户绑定，空则表示未绑定,。通常是和custmer_in_contact是一样的。
     */
    private Integer customer;

    /**
     * 出厂日期，老机器允许空
     */
    @Column(name = "facory_date")
    private Date facoryDate;

    /**
     * 0表示不是老机器，1表示老机器（生产部新系统之前生产的机器，不在生产部数据库）
     */
    @Column(name = "is_old_machine")
    private String isOldMachine;

    /**
     * 老机器审核是否通过，空表示非老机器，0:未通过，1：通过
     */
    @Column(name = "old_machine_check")
    private String oldMachineCheck;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取技术部填入的机器编号（铭牌），来自于sinsim_db.machine. nameplate
     *
     * @return nameplate - 技术部填入的机器编号（铭牌），来自于sinsim_db.machine. nameplate
     */
    public String getNameplate() {
        return nameplate;
    }

    /**
     * 设置技术部填入的机器编号（铭牌），来自于sinsim_db.machine. nameplate
     *
     * @param nameplate 技术部填入的机器编号（铭牌），来自于sinsim_db.machine. nameplate
     */
    public void setNameplate(String nameplate) {
        this.nameplate = nameplate;
    }

    /**
     * 获取对应的订单号,来自sinsim_db.machine_order.order_num
     *
     * @return order_num - 对应的订单号,来自sinsim_db.machine_order.order_num
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * 设置对应的订单号,来自sinsim_db.machine_order.order_num
     *
     * @param orderNum 对应的订单号,来自sinsim_db.machine_order.order_num
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取对应的合同号，可能用不到，允许空
     *
     * @return contract_num - 对应的合同号，可能用不到，允许空
     */
    public String getContractNum() {
        return contractNum;
    }

    /**
     * 设置对应的合同号，可能用不到，允许空
     *
     * @param contractNum 对应的合同号，可能用不到，允许空
     */
    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    /**
     * 获取机器的地理位置，经纬度，考虑在调试完成时上传，备用地图显示
     *
     * @return geo_location - 机器的地理位置，经纬度，考虑在调试完成时上传，备用地图显示
     */
    public String getGeoLocation() {
        return geoLocation;
    }

    /**
     * 设置机器的地理位置，经纬度，考虑在调试完成时上传，备用地图显示
     *
     * @param geoLocation 机器的地理位置，经纬度，考虑在调试完成时上传，备用地图显示
     */
    public void setGeoLocation(String geoLocation) {
        this.geoLocation = geoLocation;
    }

    /**
     * 获取机器地址，厂家门牌号
     *
     * @return address - 机器地址，厂家门牌号
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置机器地址，厂家门牌号
     *
     * @param address 机器地址，厂家门牌号
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取机器状态 0：未绑定，1：已绑定，2：待安装，3：正常工作状态（已安装、已保养，已维修），4：待保养，5：待修理，6： 待审核
     *
     * @return status - 机器状态 0：未绑定，1：已绑定，2：待安装，3：正常工作状态（已安装、已保养，已维修），4：待保养，5：待修理，6： 待审核
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置机器状态 0：未绑定，1：已绑定，2：待安装，3：正常工作状态（已安装、已保养，已维修），4：待保养，5：待修理，6： 待审核
     *
     * @param status 机器状态 0：未绑定，1：已绑定，2：待安装，3：正常工作状态（已安装、已保养，已维修），4：待保养，5：待修理，6： 待审核
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取老机器，客户拍的铭牌照片的保存地址；非老机器可以为空
     *
     * @return nameplate_picture - 老机器，客户拍的铭牌照片的保存地址；非老机器可以为空
     */
    public String getNameplatePicture() {
        return nameplatePicture;
    }

    /**
     * 设置老机器，客户拍的铭牌照片的保存地址；非老机器可以为空
     *
     * @param nameplatePicture 老机器，客户拍的铭牌照片的保存地址；非老机器可以为空
     */
    public void setNameplatePicture(String nameplatePicture) {
        this.nameplatePicture = nameplatePicture;
    }

    /**
     * 获取机型
     *
     * @return machine_type - 机型
     */
    public String getMachineType() {
        return machineType;
    }

    /**
     * 设置机型
     *
     * @param machineType 机型
     */
    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    /**
     * 获取针数
     *
     * @return needle_num - 针数
     */
    public String getNeedleNum() {
        return needleNum;
    }

    /**
     * 设置针数
     *
     * @param needleNum 针数
     */
    public void setNeedleNum(String needleNum) {
        this.needleNum = needleNum;
    }

    /**
     * 获取x行程
     *
     * @return x_distance - x行程
     */
    public String getxDistance() {
        return xDistance;
    }

    /**
     * 设置x行程
     *
     * @param xDistance x行程
     */
    public void setxDistance(String xDistance) {
        this.xDistance = xDistance;
    }

    /**
     * 获取y行程
     *
     * @return y_distance - y行程
     */
    public String getyDistance() {
        return yDistance;
    }

    /**
     * 设置y行程
     *
     * @param yDistance y行程
     */
    public void setyDistance(String yDistance) {
        this.yDistance = yDistance;
    }

    /**
     * 获取头距
     *
     * @return head_distance - 头距
     */
    public String getHeadDistance() {
        return headDistance;
    }

    /**
     * 设置头距
     *
     * @param headDistance 头距
     */
    public void setHeadDistance(String headDistance) {
        this.headDistance = headDistance;
    }

    /**
     * 获取头数
     *
     * @return head_num - 头数
     */
    public String getHeadNum() {
        return headNum;
    }

    /**
     * 设置头数
     *
     * @param headNum 头数
     */
    public void setHeadNum(String headNum) {
        this.headNum = headNum;
    }

    /**
     * 获取装车单路径，共用流程管理系统的装车单，老机器允许空
     *
     * @return loadinglist - 装车单路径，共用流程管理系统的装车单，老机器允许空
     */
    public String getLoadinglist() {
        return loadinglist;
    }

    /**
     * 设置装车单路径，共用流程管理系统的装车单，老机器允许空
     *
     * @param loadinglist 装车单路径，共用流程管理系统的装车单，老机器允许空
     */
    public void setLoadinglist(String loadinglist) {
        this.loadinglist = loadinglist;
    }

    /**
     * 获取合同里的客户
     *
     * @return customer_in_contract - 合同里的客户
     */
    public String getCustomerInContract() {
        return customerInContract;
    }

    /**
     * 设置合同里的客户
     *
     * @param customerInContract 合同里的客户
     */
    public void setCustomerInContract(String customerInContract) {
        this.customerInContract = customerInContract;
    }

    /**
     * 获取机器和客户绑定，空则表示未绑定,。通常是和custmer_in_contact是一样的。
     *
     * @return customer - 机器和客户绑定，空则表示未绑定,。通常是和custmer_in_contact是一样的。
     */
    public Integer getCustomer() {
        return customer;
    }

    /**
     * 设置机器和客户绑定，空则表示未绑定,。通常是和custmer_in_contact是一样的。
     *
     * @param customer 机器和客户绑定，空则表示未绑定,。通常是和custmer_in_contact是一样的。
     */
    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    /**
     * 获取出厂日期，老机器允许空
     *
     * @return facory_date - 出厂日期，老机器允许空
     */
    public Date getFacoryDate() {
        return facoryDate;
    }

    /**
     * 设置出厂日期，老机器允许空
     *
     * @param facoryDate 出厂日期，老机器允许空
     */
    public void setFacoryDate(Date facoryDate) {
        this.facoryDate = facoryDate;
    }

    /**
     * 获取0表示不是老机器，1表示老机器（生产部新系统之前生产的机器，不在生产部数据库）
     *
     * @return is_old_machine - 0表示不是老机器，1表示老机器（生产部新系统之前生产的机器，不在生产部数据库）
     */
    public String getIsOldMachine() {
        return isOldMachine;
    }

    /**
     * 设置0表示不是老机器，1表示老机器（生产部新系统之前生产的机器，不在生产部数据库）
     *
     * @param isOldMachine 0表示不是老机器，1表示老机器（生产部新系统之前生产的机器，不在生产部数据库）
     */
    public void setIsOldMachine(String isOldMachine) {
        this.isOldMachine = isOldMachine;
    }

    /**
     * 获取老机器审核是否通过，空表示非老机器，0:未通过，1：通过
     *
     * @return old_machine_check - 老机器审核是否通过，空表示非老机器，0:未通过，1：通过
     */
    public String getOldMachineCheck() {
        return oldMachineCheck;
    }

    /**
     * 设置老机器审核是否通过，空表示非老机器，0:未通过，1：通过
     *
     * @param oldMachineCheck 老机器审核是否通过，空表示非老机器，0:未通过，1：通过
     */
    public void setOldMachineCheck(String oldMachineCheck) {
        this.oldMachineCheck = oldMachineCheck;
    }
}