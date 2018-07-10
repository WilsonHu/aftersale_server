package com.eservice.api.model.maintain_members;

import javax.persistence.*;

@Table(name = "maintain_members")
public class MaintainMembers {
    /**
     * 这个表只是为了方便查询（查询员工的任务），一个记录有多个安装组员，一对多,
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 安装记录的ID
     */
    @Column(name = "maintain_record_id")
    private Integer maintainRecordId;

    /**
     * 员工的ID
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 获取这个表只是为了方便查询（查询员工的任务），一个记录有多个安装组员，一对多,
     *
     * @return id - 这个表只是为了方便查询（查询员工的任务），一个记录有多个安装组员，一对多,
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置这个表只是为了方便查询（查询员工的任务），一个记录有多个安装组员，一对多,
     *
     * @param id 这个表只是为了方便查询（查询员工的任务），一个记录有多个安装组员，一对多,
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取安装记录的ID
     *
     * @return maintain_record_id - 安装记录的ID
     */
    public Integer getMaintainRecordId() {
        return maintainRecordId;
    }

    /**
     * 设置安装记录的ID
     *
     * @param maintainRecordId 安装记录的ID
     */
    public void setMaintainRecordId(Integer maintainRecordId) {
        this.maintainRecordId = maintainRecordId;
    }

    /**
     * 获取员工的ID
     *
     * @return user_id - 员工的ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置员工的ID
     *
     * @param userId 员工的ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}