package com.eservice.api.model.install_record;

import javax.persistence.*;
import java.util.Date;

@Table(name = "install_record")
public class InstallRecordInfos {
    /**
     * ID号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 安装调试验收单的编号，先保留着，可能用不到,允许空。
     */
    @Column(name = "install_record_num")
    private String installRecordNum;

    /**
     * 安装调试计划日期（上门日期）
     */
    @Column(name = "install_plan_date")
    private Date installPlanDate;

    /**
     * 安装调试的结束时间（安装工提交时的时间）
     */
    @Column(name = "install_actual_time")
    private Date installActualTime;

    /**
     * 机器铭牌号，以此为依据查询机型信息,客户能看到的也是这个铭牌号
     */
    @Column(name = "machine_nameplate")
    private String machineNameplate;

    /**
     * 安装状态，0：已分配安装(但未接单）；1：已接受任务（不用驳回，如果有异议，电话沟通后可以重新派单）； 2：安装OK(客户未确认); 3：安装NG(如果用不到这个就不用）4：客户已确认
     */
    @Column(name = "install_status")
    private String installStatus;

    /**
     * 客户反馈和建议
     */
    @Column(name = "customer_feedback")
    private Integer customerFeedback;

    /**
     * 安装的负责人
     */
    @Column(name = "install_charge_person")
    private Integer installChargePerson;

    /**
     * 该记录的创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 客户
     */
    private Integer customer;

    /**
     * 安装结果(安装人员建议可以写在此处)
     */
    @Column(name = "install_result")
    private String installResult;

    /**
     * 全部安装信息，json格式
 [
   {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压A",
        "install_value":"220v"
    },
    {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压B",
        "install_value":"220v"
    }
]
     */
    @Column(name = "install_info")
    private String installInfo;
    private String machineTypeName;


    /**
     *添加
     */
    private String nameplate;

    /**
     * 获取ID号
     *
     * @return id - ID号
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID号
     *
     * @param id ID号
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取安装调试验收单的编号，先保留着，可能用不到,允许空。
     *
     * @return install_record_num - 安装调试验收单的编号，先保留着，可能用不到,允许空。
     */
    public String getInstallRecordNum() {
        return installRecordNum;
    }

    /**
     * 设置安装调试验收单的编号，先保留着，可能用不到,允许空。
     *
     * @param installRecordNum 安装调试验收单的编号，先保留着，可能用不到,允许空。
     */
    public void setInstallRecordNum(String installRecordNum) {
        this.installRecordNum = installRecordNum;
    }

    /**
     * 获取安装调试计划日期（上门日期）
     *
     * @return install_plan_date - 安装调试计划日期（上门日期）
     */
    public Date getInstallPlanDate() {
        return installPlanDate;
    }

    /**
     * 设置安装调试计划日期（上门日期）
     *
     * @param installPlanDate 安装调试计划日期（上门日期）
     */
    public void setInstallPlanDate(Date installPlanDate) {
        this.installPlanDate = installPlanDate;
    }

    /**
     * 获取安装调试的结束时间（安装工提交时的时间）
     *
     * @return install_actual_time - 安装调试的结束时间（安装工提交时的时间）
     */
    public Date getInstallActualTime() {
        return installActualTime;
    }

    /**
     * 设置安装调试的结束时间（安装工提交时的时间）
     *
     * @param installActualTime 安装调试的结束时间（安装工提交时的时间）
     */
    public void setInstallActualTime(Date installActualTime) {
        this.installActualTime = installActualTime;
    }

    /**
     * 获取机器铭牌号，以此为依据查询机型信息,客户能看到的也是这个铭牌号
     *
     * @return machine_nameplate - 机器铭牌号，以此为依据查询机型信息,客户能看到的也是这个铭牌号
     */
    public String getMachineNameplate() {
        return machineNameplate;
    }

    /**
     * 设置机器铭牌号，以此为依据查询机型信息,客户能看到的也是这个铭牌号
     *
     * @param machineNameplate 机器铭牌号，以此为依据查询机型信息,客户能看到的也是这个铭牌号
     */
    public void setMachineNameplate(String machineNameplate) {
        this.machineNameplate = machineNameplate;
    }

    /**
     * 获取安装状态，0：已分配安装(但未接单）；1：已接受任务（不用驳回，如果有异议，电话沟通后可以重新派单）； 2：安装OK(客户未确认); 3：安装NG(如果用不到这个就不用）4：客户已确认
     *
     * @return install_status - 安装状态，0：已分配安装(但未接单）；1：已接受任务（不用驳回，如果有异议，电话沟通后可以重新派单）； 2：安装OK(客户未确认); 3：安装NG(如果用不到这个就不用）4：客户已确认
     */
    public String getInstallStatus() {
        return installStatus;
    }

    /**
     * 设置安装状态，0：已分配安装(但未接单）；1：已接受任务（不用驳回，如果有异议，电话沟通后可以重新派单）； 2：安装OK(客户未确认); 3：安装NG(如果用不到这个就不用）4：客户已确认
     *
     * @param installStatus 安装状态，0：已分配安装(但未接单）；1：已接受任务（不用驳回，如果有异议，电话沟通后可以重新派单）； 2：安装OK(客户未确认); 3：安装NG(如果用不到这个就不用）4：客户已确认
     */
    public void setInstallStatus(String installStatus) {
        this.installStatus = installStatus;
    }

    /**
     * 获取客户反馈和建议
     *
     * @return customer_feedback - 客户反馈和建议
     */
    public Integer getCustomerFeedback() {
        return customerFeedback;
    }

    /**
     * 设置客户反馈和建议
     *
     * @param customerFeedback 客户反馈和建议
     */
    public void setCustomerFeedback(Integer customerFeedback) {
        this.customerFeedback = customerFeedback;
    }

    /**
     * 获取安装的负责人
     *
     * @return install_charge_person - 安装的负责人
     */
    public Integer getInstallChargePerson() {
        return installChargePerson;
    }

    /**
     * 设置安装的负责人
     *
     * @param installChargePerson 安装的负责人
     */
    public void setInstallChargePerson(Integer installChargePerson) {
        this.installChargePerson = installChargePerson;
    }

    /**
     * 获取该记录的创建时间
     *
     * @return create_time - 该记录的创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置该记录的创建时间
     *
     * @param createTime 该记录的创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取客户
     *
     * @return customer - 客户
     */
    public Integer getCustomer() {
        return customer;
    }

    /**
     * 设置客户
     *
     * @param customer 客户
     */
    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    /**
     * 获取安装结果(安装人员建议可以写在此处)
     *
     * @return install_result - 安装结果(安装人员建议可以写在此处)
     */
    public String getInstallResult() {
        return installResult;
    }

    /**
     * 设置安装结果(安装人员建议可以写在此处)
     *
     * @param installResult 安装结果(安装人员建议可以写在此处)
     */
    public void setInstallResult(String installResult) {
        this.installResult = installResult;
    }

    /**
     * 获取全部安装信息，json格式
 [
   {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压A",
        "install_value":"220v"
    },
    {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压B",
        "install_value":"220v"
    }
]
     *
     * @return install_info - 全部安装信息，json格式
 [
   {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压A",
        "install_value":"220v"
    },
    {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压B",
        "install_value":"220v"
    }
]
     */
    public String getInstallInfo() {
        return installInfo;
    }

    /**
     * 设置全部安装信息，json格式
 [
   {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压A",
        "install_value":"220v"
    },
    {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压B",
        "install_value":"220v"
    }
]
     *
     * @param installInfo 全部安装信息，json格式
 [
   {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压A",
        "install_value":"220v"
    },
    {
        "is_base_lib":1,
        "install_lib_name":"基础库",
        "install_content":"电源电压B",
        "install_value":"220v"
    }
]
     */
    public void setInstallInfo(String installInfo) {
        this.installInfo = installInfo;
    }
}