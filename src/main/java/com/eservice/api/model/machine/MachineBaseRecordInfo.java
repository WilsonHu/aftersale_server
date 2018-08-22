package com.eservice.api.model.machine;

import java.util.Date;

public class MachineBaseRecordInfo extends Machine {

    /**
     * 实际完成安装时间
     */
    private Date installActualTime;

    /**
     * 安装工的负责人
     */
    private String installChargePersonName;

    /**
     * 安装的状态
     */
    private String installStatus;

    /**
     * 保养的实际日期
     */
    private Date maintainDateActual;

    /**
     * 保养工的负责人
     */
    private String maintainChargePersonName;

    /**
     * 保养的状态
     */
    private String maintainStatus;

    private Date repairEndTime;

    private String repairChargePersonName;

    private String repairStatus;

    public Date getInstallActualTime() {
        return installActualTime;
    }

    public void setInstallActualTime(Date installActualTime) {
        this.installActualTime = installActualTime;
    }

    public void setMaintainStatus(String maintainStatus) {
        this.maintainStatus = maintainStatus;
    }

    public String getMaintainStatus() {
        return maintainStatus;
    }

    public void setRepairEndTime(Date repairEndTime) {
        this.repairEndTime = repairEndTime;
    }

    public Date getRepairEndTime() {
        return this.repairEndTime;
    }

    public Date getMaintainDateActual() {
        return maintainDateActual;
    }

    public void setMaintainDateActual(Date maintainDateActual) {
        this.maintainDateActual = maintainDateActual;
    }

    public String getInstallChargePersonName() {
        return this.installChargePersonName;
    }

    public void setInstallChargePersonName(String installChargePersonName) {
        this.installChargePersonName = installChargePersonName;
    }

    public String getRepairStatus() {
        return repairStatus;
    }

    public void setRepairStatus(String repairStatus) {
        this.repairStatus = repairStatus;
    }

    public void setMaintainChargePersonName(String maintainChargePersonName) {
        this.maintainChargePersonName = maintainChargePersonName;
    }

    public String getMaintainChargePersonName() {
        return maintainChargePersonName;
    }

    public void setRepairChargePersonName(String repairChargePersonName) {
        this.repairChargePersonName = repairChargePersonName;
    }

    public String getRepairChargePersonName() {
        return repairChargePersonName;
    }

    public void setInstallStatus(String installStatus) {
        this.installStatus = installStatus;
    }

    public String getInstallStatus() {
        return installStatus;
    }

    /**
     * 寄回配件的状态
     * 1: 无需回寄，其他表示需要寄回(具体 2：未寄回，3：已寄回（待确认），4：已确认)
     */
    private String partsStatus;

    public void setPartsStatus(String partsStatus) {
        this.partsStatus = partsStatus;
    }

    public String getPartsStatus() {
        return partsStatus;
    }

    /**
     * 维修记录的ID
     */
    private Integer repairRecordId;

    public Integer getRepairRecordId() {
        return repairRecordId;
    }

    public void setRepairRecordId(Integer repairRecordId) {
        this.repairRecordId = repairRecordId;
    }

    /**
     * 安装记录的ID
     */
    private Integer installRecordId;

    public void setInstallRecordId(Integer installRecordId) {
        this.installRecordId = installRecordId;
    }

    public Integer getInstallRecordId() {
        return installRecordId;
    }

    /**
     * 保养记录的ID
     */
    private Integer maintainRecordId;

    public void setMaintainRecordId(Integer maintainRecordId) {
        this.maintainRecordId = maintainRecordId;
    }

    public Integer getMaintainRecordId() {
        return maintainRecordId;
    }
}