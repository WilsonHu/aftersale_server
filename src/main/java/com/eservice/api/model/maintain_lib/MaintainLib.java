package com.eservice.api.model.maintain_lib;

import javax.persistence.*;

@Table(name = "maintain_lib")
public class MaintainLib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 保养库的名字， 一期，二期，三期等
     */
    @Column(name = "maintain_lib_name")
    private String maintainLibName;

    /**
     * 1: 清理清洁， 2：注油润滑， 3： 检查修理
     */
    @Column(name = "maintain_type")
    private Integer maintainType;

    /**
     * 保养内容
     */
    @Column(name = "maintain_content")
    private String maintainContent;

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
     * 获取保养库的名字， 一期，二期，三期等
     *
     * @return maintain_lib_name - 保养库的名字， 一期，二期，三期等
     */
    public String getMaintainLibName() {
        return maintainLibName;
    }

    /**
     * 设置保养库的名字， 一期，二期，三期等
     *
     * @param maintainLibName 保养库的名字， 一期，二期，三期等
     */
    public void setMaintainLibName(String maintainLibName) {
        this.maintainLibName = maintainLibName;
    }

    /**
     * 获取1: 清理清洁， 2：注油润滑， 3： 检查修理
     *
     * @return maintain_type - 1: 清理清洁， 2：注油润滑， 3： 检查修理
     */
    public Integer getMaintainType() {
        return maintainType;
    }

    /**
     * 设置1: 清理清洁， 2：注油润滑， 3： 检查修理
     *
     * @param maintainType 1: 清理清洁， 2：注油润滑， 3： 检查修理
     */
    public void setMaintainType(Integer maintainType) {
        this.maintainType = maintainType;
    }

    /**
     * 获取保养内容
     *
     * @return maintain_content - 保养内容
     */
    public String getMaintainContent() {
        return maintainContent;
    }

    /**
     * 设置保养内容
     *
     * @param maintainContent 保养内容
     */
    public void setMaintainContent(String maintainContent) {
        this.maintainContent = maintainContent;
    }
}