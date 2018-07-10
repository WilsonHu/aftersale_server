package com.eservice.api.model.experience_lib;

import java.util.Date;
import javax.persistence.*;

@Table(name = "experience_lib")
public class ExperienceLib {
    /**
     * 这个表原始数据和repair_actual_info一样，但是允许管理员修改编辑
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 维修记录的ID
     */
    @Column(name = "repair_actual_info_id")
    private Integer repairActualInfoId;

    /**
     * 解决前的照片，
和repair_request_info.pictures是同一份图片 ，
管理员可以编辑。但是不让删除图片。
     */
    @Column(name = "before_resolve_pictures")
    private String beforeResolvePictures;

    /**
     * 解决后图片，来自于repair_actual_info.after_resolve_pictures。
是同一份图片 ，
管理员可以编辑。也不让删除图片。
     */
    @Column(name = "after_resolve_pictures")
    private String afterResolvePictures;

    private String author;

    @Column(name = "read_times")
    private String readTimes;

    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间，比如管理员编辑的时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 问题描述，来自于repair_actual_info.issue_description，但是这里可以被管理员编辑
     */
    @Column(name = "issue_description")
    private String issueDescription;

    /**
     * 解决方案，来自于repair_actual_info.irepair_method。管理员可以编辑
     */
    @Column(name = "repair_method")
    private String repairMethod;

    /**
     * 获取这个表原始数据和repair_actual_info一样，但是允许管理员修改编辑
     *
     * @return id - 这个表原始数据和repair_actual_info一样，但是允许管理员修改编辑
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置这个表原始数据和repair_actual_info一样，但是允许管理员修改编辑
     *
     * @param id 这个表原始数据和repair_actual_info一样，但是允许管理员修改编辑
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取维修记录的ID
     *
     * @return repair_actual_info_id - 维修记录的ID
     */
    public Integer getRepairActualInfoId() {
        return repairActualInfoId;
    }

    /**
     * 设置维修记录的ID
     *
     * @param repairActualInfoId 维修记录的ID
     */
    public void setRepairActualInfoId(Integer repairActualInfoId) {
        this.repairActualInfoId = repairActualInfoId;
    }

    /**
     * 获取解决前的照片，
和repair_request_info.pictures是同一份图片 ，
管理员可以编辑。但是不让删除图片。
     *
     * @return before_resolve_pictures - 解决前的照片，
和repair_request_info.pictures是同一份图片 ，
管理员可以编辑。但是不让删除图片。
     */
    public String getBeforeResolvePictures() {
        return beforeResolvePictures;
    }

    /**
     * 设置解决前的照片，
和repair_request_info.pictures是同一份图片 ，
管理员可以编辑。但是不让删除图片。
     *
     * @param beforeResolvePictures 解决前的照片，
和repair_request_info.pictures是同一份图片 ，
管理员可以编辑。但是不让删除图片。
     */
    public void setBeforeResolvePictures(String beforeResolvePictures) {
        this.beforeResolvePictures = beforeResolvePictures;
    }

    /**
     * 获取解决后图片，来自于repair_actual_info.after_resolve_pictures。
是同一份图片 ，
管理员可以编辑。也不让删除图片。
     *
     * @return after_resolve_pictures - 解决后图片，来自于repair_actual_info.after_resolve_pictures。
是同一份图片 ，
管理员可以编辑。也不让删除图片。
     */
    public String getAfterResolvePictures() {
        return afterResolvePictures;
    }

    /**
     * 设置解决后图片，来自于repair_actual_info.after_resolve_pictures。
是同一份图片 ，
管理员可以编辑。也不让删除图片。
     *
     * @param afterResolvePictures 解决后图片，来自于repair_actual_info.after_resolve_pictures。
是同一份图片 ，
管理员可以编辑。也不让删除图片。
     */
    public void setAfterResolvePictures(String afterResolvePictures) {
        this.afterResolvePictures = afterResolvePictures;
    }

    /**
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return read_times
     */
    public String getReadTimes() {
        return readTimes;
    }

    /**
     * @param readTimes
     */
    public void setReadTimes(String readTimes) {
        this.readTimes = readTimes;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间，比如管理员编辑的时间
     *
     * @return update_time - 更新时间，比如管理员编辑的时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间，比如管理员编辑的时间
     *
     * @param updateTime 更新时间，比如管理员编辑的时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取问题描述，来自于repair_actual_info.issue_description，但是这里可以被管理员编辑
     *
     * @return issue_description - 问题描述，来自于repair_actual_info.issue_description，但是这里可以被管理员编辑
     */
    public String getIssueDescription() {
        return issueDescription;
    }

    /**
     * 设置问题描述，来自于repair_actual_info.issue_description，但是这里可以被管理员编辑
     *
     * @param issueDescription 问题描述，来自于repair_actual_info.issue_description，但是这里可以被管理员编辑
     */
    public void setIssueDescription(String issueDescription) {
        this.issueDescription = issueDescription;
    }

    /**
     * 获取解决方案，来自于repair_actual_info.irepair_method。管理员可以编辑
     *
     * @return repair_method - 解决方案，来自于repair_actual_info.irepair_method。管理员可以编辑
     */
    public String getRepairMethod() {
        return repairMethod;
    }

    /**
     * 设置解决方案，来自于repair_actual_info.irepair_method。管理员可以编辑
     *
     * @param repairMethod 解决方案，来自于repair_actual_info.irepair_method。管理员可以编辑
     */
    public void setRepairMethod(String repairMethod) {
        this.repairMethod = repairMethod;
    }
}