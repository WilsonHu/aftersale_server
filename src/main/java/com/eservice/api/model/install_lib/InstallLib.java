package com.eservice.api.model.install_lib;

import javax.persistence.*;

@Table(name = "install_lib")
public class InstallLib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 0:非基础库，1：基础库
     */
    @Column(name = "is_base_lib")
    private String isBaseLib;

    /**
     * 所属的安装库的名称， 
     */
    @Column(name = "install_lib_name")
    private String installLibName;

    /**
     * 安装验收的内容
     */
    @Column(name = "install_content")
    private String installContent;

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
     * 获取0:非基础库，1：基础库
     *
     * @return is_base_lib - 0:非基础库，1：基础库
     */
    public String getIsBaseLib() {
        return isBaseLib;
    }

    /**
     * 设置0:非基础库，1：基础库
     *
     * @param isBaseLib 0:非基础库，1：基础库
     */
    public void setIsBaseLib(String isBaseLib) {
        this.isBaseLib = isBaseLib;
    }

    /**
     * 获取所属的安装库的名称， 
     *
     * @return install_lib_name - 所属的安装库的名称， 
     */
    public String getInstallLibName() {
        return installLibName;
    }

    /**
     * 设置所属的安装库的名称， 
     *
     * @param installLibName 所属的安装库的名称， 
     */
    public void setInstallLibName(String installLibName) {
        this.installLibName = installLibName;
    }

    /**
     * 获取安装验收的内容
     *
     * @return install_content - 安装验收的内容
     */
    public String getInstallContent() {
        return installContent;
    }

    /**
     * 设置安装验收的内容
     *
     * @param installContent 安装验收的内容
     */
    public void setInstallContent(String installContent) {
        this.installContent = installContent;
    }
}