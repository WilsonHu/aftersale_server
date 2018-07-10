package com.eservice.api.model.maintain_type;

import javax.persistence.*;

@Table(name = "maintain_type")
public class MaintainType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 保养类型，比如清洁清理/注油润滑/检查修理
     */
    @Column(name = "maintain_type_name")
    private String maintainTypeName;

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
     * 获取保养类型，比如清洁清理/注油润滑/检查修理
     *
     * @return maintain_type_name - 保养类型，比如清洁清理/注油润滑/检查修理
     */
    public String getMaintainTypeName() {
        return maintainTypeName;
    }

    /**
     * 设置保养类型，比如清洁清理/注油润滑/检查修理
     *
     * @param maintainTypeName 保养类型，比如清洁清理/注油润滑/检查修理
     */
    public void setMaintainTypeName(String maintainTypeName) {
        this.maintainTypeName = maintainTypeName;
    }
}