package com.eservice.api.model.maintain_abnormal_record;

import javax.persistence.*;

@Table(name = "maintain_abnormal_record")
public class MaintainAbnormalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 一次保养，可能有多个异常
     */
    @Column(name = "maintain_record_id")
    private Integer maintainRecordId;

    /**
     * 异常对应的 保养内容
     */
    @Column(name = "maintain_content")
    private String maintainContent;

    /**
     * 保养的异常记录
     */
    @Column(name = "maitain_abnormal_content")
    private String maitainAbnormalContent;

    /**
     * 异常的修理结果
     */
    @Column(name = "maintain_fix_result")
    private String maintainFixResult;

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
     * 获取一次保养，可能有多个异常
     *
     * @return maintain_record_id - 一次保养，可能有多个异常
     */
    public Integer getMaintainRecordId() {
        return maintainRecordId;
    }

    /**
     * 设置一次保养，可能有多个异常
     *
     * @param maintainRecordId 一次保养，可能有多个异常
     */
    public void setMaintainRecordId(Integer maintainRecordId) {
        this.maintainRecordId = maintainRecordId;
    }

    /**
     * 获取异常对应的 保养内容
     *
     * @return maintain_content - 异常对应的 保养内容
     */
    public String getMaintainContent() {
        return maintainContent;
    }

    /**
     * 设置异常对应的 保养内容
     *
     * @param maintainContent 异常对应的 保养内容
     */
    public void setMaintainContent(String maintainContent) {
        this.maintainContent = maintainContent;
    }

    /**
     * 获取保养的异常记录
     *
     * @return maitain_abnormal_content - 保养的异常记录
     */
    public String getMaitainAbnormalContent() {
        return maitainAbnormalContent;
    }

    /**
     * 设置保养的异常记录
     *
     * @param maitainAbnormalContent 保养的异常记录
     */
    public void setMaitainAbnormalContent(String maitainAbnormalContent) {
        this.maitainAbnormalContent = maitainAbnormalContent;
    }

    /**
     * 获取异常的修理结果
     *
     * @return maintain_fix_result - 异常的修理结果
     */
    public String getMaintainFixResult() {
        return maintainFixResult;
    }

    /**
     * 设置异常的修理结果
     *
     * @param maintainFixResult 异常的修理结果
     */
    public void setMaintainFixResult(String maintainFixResult) {
        this.maintainFixResult = maintainFixResult;
    }
}