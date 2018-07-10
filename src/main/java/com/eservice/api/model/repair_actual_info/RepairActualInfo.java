package com.eservice.api.model.repair_actual_info;

import javax.persistence.*;

@Table(name = "repair_actual_info")
public class RepairActualInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 对应的维修记录，一次报修可以有多个实际维修
     */
    @Column(name = "repair_record_id")
    private Integer repairRecordId;

    /**
     * 维修部位
     */
    @Column(name = "issue_position")
    private Integer issuePosition;

    /**
     * 解决后的照片（保存文件路径）
     */
    @Column(name = "after_resolve_pictures")
    private String afterResolvePictures;

    /**
     * 实际维修中的“故障描述”, 也用于“经验库”中的“问题描述”
     */
    @Column(name = "issue_description")
    private String issueDescription;

    /**
     * 实际维修中的“处理方法”
     */
    @Column(name = "repair_method")
    private String repairMethod;

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
     * 获取对应的维修记录，一次报修可以有多个实际维修
     *
     * @return repair_record_id - 对应的维修记录，一次报修可以有多个实际维修
     */
    public Integer getRepairRecordId() {
        return repairRecordId;
    }

    /**
     * 设置对应的维修记录，一次报修可以有多个实际维修
     *
     * @param repairRecordId 对应的维修记录，一次报修可以有多个实际维修
     */
    public void setRepairRecordId(Integer repairRecordId) {
        this.repairRecordId = repairRecordId;
    }

    /**
     * 获取维修部位
     *
     * @return issue_position - 维修部位
     */
    public Integer getIssuePosition() {
        return issuePosition;
    }

    /**
     * 设置维修部位
     *
     * @param issuePosition 维修部位
     */
    public void setIssuePosition(Integer issuePosition) {
        this.issuePosition = issuePosition;
    }

    /**
     * 获取解决后的照片（保存文件路径）
     *
     * @return after_resolve_pictures - 解决后的照片（保存文件路径）
     */
    public String getAfterResolvePictures() {
        return afterResolvePictures;
    }

    /**
     * 设置解决后的照片（保存文件路径）
     *
     * @param afterResolvePictures 解决后的照片（保存文件路径）
     */
    public void setAfterResolvePictures(String afterResolvePictures) {
        this.afterResolvePictures = afterResolvePictures;
    }

    /**
     * 获取实际维修中的“故障描述”, 也用于“经验库”中的“问题描述”
     *
     * @return issue_description - 实际维修中的“故障描述”, 也用于“经验库”中的“问题描述”
     */
    public String getIssueDescription() {
        return issueDescription;
    }

    /**
     * 设置实际维修中的“故障描述”, 也用于“经验库”中的“问题描述”
     *
     * @param issueDescription 实际维修中的“故障描述”, 也用于“经验库”中的“问题描述”
     */
    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    /**
     * 获取实际维修中的“处理方法”
     *
     * @return repair_method - 实际维修中的“处理方法”
     */
    public String getRepairMethod() {
        return repairMethod;
    }

    /**
     * 设置实际维修中的“处理方法”
     *
     * @param repairMethod 实际维修中的“处理方法”
     */
    public void setRepairMethod(String repairMethod) {
        this.repairMethod = repairMethod;
    }
}