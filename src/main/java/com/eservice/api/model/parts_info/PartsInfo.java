package com.eservice.api.model.parts_info;

import java.util.Date;
import javax.persistence.*;

@Table(name = "parts_info")
public class PartsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 配件名称
     */
    @Column(name = "parts_name")
    private String partsName;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 配件参数
     */
    private String params;

    /**
     * 一次报修，可以有多个维修，对应多个零件
     */
    @Column(name = "repair_actual_info_id")
    private Integer repairActualInfoId;

    /**
     * 1: 无需回寄，其他表示需要寄回(具体 2：未寄回，3：已寄回（待确认），4：已确认)
     */
    @Column(name = "parts_status")
    private String partsStatus;

    /**
     * 快递单号，如果不用寄回，则为空
     */
    @Column(name = "sendback_tracking_number")
    private String sendbackTrackingNumber;

    /**
     * 寄回的快递单号的照片的保存路径
     */
    @Column(name = "sendback_tracking_pictrue")
    private String sendbackTrackingPictrue;

    /**
     * 寄回日期
     */
    @Column(name = "sendback_date")
    private Date sendbackDate;

    /**
     *  确认寄回的时间
     */
    @Column(name = "sendback_confirmed_time")
    private Date sendbackConfirmedTime;

    /**
     * sinsim确认人 确认寄回的人
     */
    @Column(name = "sendback_confirmed_person")
    private Integer sendbackConfirmedPerson;

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
     * 获取配件名称
     *
     * @return parts_name - 配件名称
     */
    public String getPartsName() {
        return partsName;
    }

    /**
     * 设置配件名称
     *
     * @param partsName 配件名称
     */
    public void setPartsName(String partsName) {
        this.partsName = partsName;
    }

    /**
     * 获取供应商
     *
     * @return supplier - 供应商
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * 设置供应商
     *
     * @param supplier 供应商
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * 获取配件参数
     *
     * @return params - 配件参数
     */
    public String getParams() {
        return params;
    }

    /**
     * 设置配件参数
     *
     * @param params 配件参数
     */
    public void setParams(String params) {
        this.params = params;
    }

    /**
     * 获取一次报修，可以有多个维修，对应多个零件
     *
     * @return repair_actual_info_id - 一次报修，可以有多个维修，对应多个零件
     */
    public Integer getRepairActualInfoId() {
        return repairActualInfoId;
    }

    /**
     * 设置一次报修，可以有多个维修，对应多个零件
     *
     * @param repairActualInfoId 一次报修，可以有多个维修，对应多个零件
     */
    public void setRepairActualInfoId(Integer repairActualInfoId) {
        this.repairActualInfoId = repairActualInfoId;
    }

    /**
     * 获取1: 无需回寄，其他表示需要寄回(具体 2：未寄回，3：已寄回（待确认），4：已确认)
     *
     * @return parts_status - 1: 无需回寄，其他表示需要寄回(具体 2：未寄回，3：已寄回（待确认），4：已确认)
     */
    public String getPartsStatus() {
        return partsStatus;
    }

    /**
     * 设置1: 无需回寄，其他表示需要寄回(具体 2：未寄回，3：已寄回（待确认），4：已确认)
     *
     * @param partsStatus 1: 无需回寄，其他表示需要寄回(具体 2：未寄回，3：已寄回（待确认），4：已确认)
     */
    public void setPartsStatus(String partsStatus) {
        this.partsStatus = partsStatus;
    }

    /**
     * 获取快递单号，如果不用寄回，则为空
     *
     * @return sendback_tracking_number - 快递单号，如果不用寄回，则为空
     */
    public String getSendbackTrackingNumber() {
        return sendbackTrackingNumber;
    }

    /**
     * 设置快递单号，如果不用寄回，则为空
     *
     * @param sendbackTrackingNumber 快递单号，如果不用寄回，则为空
     */
    public void setSendbackTrackingNumber(String sendbackTrackingNumber) {
        this.sendbackTrackingNumber = sendbackTrackingNumber;
    }

    /**
     * 获取寄回的快递单号的照片的保存路径
     *
     * @return sendback_tracking_pictrue - 寄回的快递单号的照片的保存路径
     */
    public String getSendbackTrackingPictrue() {
        return sendbackTrackingPictrue;
    }

    /**
     * 设置寄回的快递单号的照片的保存路径
     *
     * @param sendbackTrackingPictrue 寄回的快递单号的照片的保存路径
     */
    public void setSendbackTrackingPictrue(String sendbackTrackingPictrue) {
        this.sendbackTrackingPictrue = sendbackTrackingPictrue;
    }

    /**
     * 获取寄回日期
     *
     * @return sendback_date - 寄回日期
     */
    public Date getSendbackDate() {
        return sendbackDate;
    }

    /**
     * 设置寄回日期
     *
     * @param sendbackDate 寄回日期
     */
    public void setSendbackDate(Date sendbackDate) {
        this.sendbackDate = sendbackDate;
    }

    /**
     * 获取 确认寄回的时间
     *
     * @return sendback_confirmed_time -  确认寄回的时间
     */
    public Date getSendbackConfirmedTime() {
        return sendbackConfirmedTime;
    }

    /**
     * 设置 确认寄回的时间
     *
     * @param sendbackConfirmedTime  确认寄回的时间
     */
    public void setSendbackConfirmedTime(Date sendbackConfirmedTime) {
        this.sendbackConfirmedTime = sendbackConfirmedTime;
    }

    /**
     * 获取sinsim确认人 确认寄回的人
     *
     * @return sendback_confirmed_person - sinsim确认人 确认寄回的人
     */
    public Integer getSendbackConfirmedPerson() {
        return sendbackConfirmedPerson;
    }

    /**
     * 设置sinsim确认人 确认寄回的人
     *
     * @param sendbackConfirmedPerson sinsim确认人 确认寄回的人
     */
    public void setSendbackConfirmedPerson(Integer sendbackConfirmedPerson) {
        this.sendbackConfirmedPerson = sendbackConfirmedPerson;
    }
}