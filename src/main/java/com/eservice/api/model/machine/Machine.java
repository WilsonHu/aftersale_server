package com.eservice.api.model.machine;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对应的order id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 系统内部维护用的机器编号(年、月、类型、头数、针数、不大于台数的数字)
     */
    @Column(name = "machine_strid")
    private String machineStrId;

    /**
     * 技术部填入的机器编号（铭牌）
     */
    private String nameplate;

    /**
     * 机器的位置，一般由生产部管理员上传
     */
    private String location;

    /**
     * 机器状态（“1”==>"初始化"，“2”==>"开始安装"，“3”==>"安装完成"，“4”==>"无效"）
     */
    private Byte status;

    @Column(name = "machine_type")
    private Integer machineType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 安装完成时间
     */
    @Column(name = "installed_time")
    private Date installedTime;

    /**
     * 发货时间（如果分批交付，需要用到，否则已订单交付为准）
     */
    @Column(name = "ship_time")
    private Date shipTime;

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
     * 获取对应的order id
     *
     * @return order_id - 对应的order id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置对应的order id
     *
     * @param orderId 对应的order id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取系统内部维护用的机器编号(年、月、类型、头数、针数、不大于台数的数字)
     *
     * @return machineStrId - 系统内部维护用的机器编号(年、月、类型、头数、针数、不大于台数的数字)
     */
    public String getMachineStrId() {
        return machineStrId;
    }

    /**
     * 设置系统内部维护用的机器编号(年、月、类型、头数、针数、不大于台数的数字)
     *
     * @param machineStrId 系统内部维护用的机器编号(年、月、类型、头数、针数、不大于台数的数字)
     */
    public void setMachineStrId(String machineStrId) {
        this.machineStrId = machineStrId;
    }

    /**
     * 获取技术部填入的机器编号（铭牌）
     *
     * @return nameplate - 技术部填入的机器编号（铭牌）
     */
    public String getNameplate() {
        return nameplate;
    }

    /**
     * 设置技术部填入的机器编号（铭牌）
     *
     * @param nameplate 技术部填入的机器编号（铭牌）
     */
    public void setNameplate(String nameplate) {
        this.nameplate = nameplate;
    }

    /**
     * 获取机器的位置，一般由生产部管理员上传
     *
     * @return location - 机器的位置，一般由生产部管理员上传
     */
    public String getLocation() {
        return location;
    }

    /**
     * 设置机器的位置，一般由生产部管理员上传
     *
     * @param location 机器的位置，一般由生产部管理员上传
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * 获取机器状态（“1”==>"初始化"，“2”==>"开始安装"，“3”==>"安装完成"，“4”==>"无效"）
     *
     * @return status - 机器状态（“1”==>"初始化"，“2”==>"开始安装"，“3”==>"安装完成"，“4”==>"无效"）
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置机器状态（“1”==>"初始化"，“2”==>"开始安装"，“3”==>"安装完成"，“4”==>"无效"）
     *
     * @param status 机器状态（“1”==>"初始化"，“2”==>"开始安装"，“3”==>"安装完成"，“4”==>"无效"）
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return machine_type
     */
    public Integer getMachineType() {
        return machineType;
    }

    /**
     * @param machineType
     */
    public void setMachineType(Integer machineType) {
        this.machineType = machineType;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取安装完成时间
     *
     * @return installed_time - 安装完成时间
     */
    public Date getInstalledTime() {
        return installedTime;
    }

    /**
     * 设置安装完成时间
     *
     * @param installedTime 安装完成时间
     */
    public void setInstalledTime(Date installedTime) {
        this.installedTime = installedTime;
    }

    /**
     * 获取发货时间（如果分批交付，需要用到，否则已订单交付为准）
     *
     * @return ship_time - 发货时间（如果分批交付，需要用到，否则已订单交付为准）
     */
    public Date getShipTime() {
        return shipTime;
    }

    /**
     * 设置发货时间（如果分批交付，需要用到，否则已订单交付为准）
     *
     * @param shipTime 发货时间（如果分批交付，需要用到，否则已订单交付为准）
     */
    public void setShipTime(Date shipTime) {
        this.shipTime = shipTime;
    }
}