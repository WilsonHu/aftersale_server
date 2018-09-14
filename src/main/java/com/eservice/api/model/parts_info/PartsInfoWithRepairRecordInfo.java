package com.eservice.api.model.parts_info;

import com.eservice.api.model.repair_record.RepairRecordInfo;

import java.util.Date;

public class PartsInfoWithRepairRecordInfo extends RepairRecordInfo {

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
    private String partsStatus;

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
    private String  sendbackConfirmedPersonName;

    public void setSendbackConfirmedPersonName(String sendbackConfirmedPersonName) {
        this.sendbackConfirmedPersonName = sendbackConfirmedPersonName;
    }

    public String getSendbackConfirmedPersonName() {
        return sendbackConfirmedPersonName;
    }

    private String partsInfoId;

    public String getPartsInfoId() {
        return partsInfoId;
    }

    public void setPartsInfoId(String partsInfoId) {
        this.partsInfoId = partsInfoId;
    }

//    private String repairActualInfoId;
//
//    public String getRepairActualInfoId() {
//        return repairActualInfoId;
//    }
//
//    public void setRepairActualInfoId(String repairActualInfoId) {
//        this.repairActualInfoId = repairActualInfoId;
//    }

}
