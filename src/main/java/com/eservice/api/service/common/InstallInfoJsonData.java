package com.eservice.api.service.common;

public class InstallInfoJsonData {


    public String getIs_base_lib() {
        return is_base_lib;
    }

    public void setIs_base_lib(String is_base_lib) {
        this.is_base_lib = is_base_lib;
    }

    public String getInstall_lib_name() {
        return install_lib_name;
    }

    public void setInstall_lib_name(String install_lib_name) {
        this.install_lib_name = install_lib_name;
    }

    public String getInstall_content() {
        return install_content;
    }

    public void setInstall_content(String install_content) {
        this.install_content = install_content;
    }

    public String getInstall_value() {
        return install_value;
    }

    public void setInstall_value(String install_value) {
        this.install_value = install_value;
    }

    /**
     * 0:非基础库，1：基础库
     */
    private String is_base_lib;

    /**
     * 所属的安装库的名称，
     */
    private String install_lib_name;

    /**
     * 安装验收的内容
     */
    private String install_content;

    private String install_value;

}
