package com.eservice.api.model.maintain_record;

import java.util.Date;
import javax.persistence.*;

@Table(name = "maintain_record")
public class MaintainRecord {
    /**
     * ID号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机器铭牌号，以此为依据查询机型信息,客户能看到的也是这个铭牌号
     */
    @Column(name = "machine_nameplate")
    private String machineNameplate;

    /**
     * 1：一期，2：二期，3：三期保养
     */
    @Column(name = "maintain_lib_name")
    private String maintainLibName;

    @Column(name = "maintain_date_plan")
    private Date maintainDatePlan;

    /**
     * 实际保养日期
     */
    @Column(name = "maintain_date_actual")
    private Date maintainDateActual;

    /**
     * 保养人员
     */
    @Column(name = "maintain_charge_person")
    private Integer maintainChargePerson;

    /**
     * 保养建议
     */
    @Column(name = "maintain_suggestion")
    private String maintainSuggestion;

    /**
     * 联系人(用户方的联系人）
     */
    private Integer contacter;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 保养状态 0：待分配，1：已分配(但未接单）2：已接受任务，3：保养完成(客户未确认)，4：客户已确认保养结果
     */
    @Column(name = "maintain_status")
    private String maintainStatus;

    /**
     * 用户反馈（意见&建议）
     */
    @Column(name = "customer_feedback")
    private Integer customerFeedback;

    /**
     * 保养json 举例:
[
  {
        "maintain_lib_name":"一期",
        "maintain_type":"注油润滑",
        "maintain_content":"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。" 
		"maintain_value":"1"			//1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"1.检查中间脚盘是否正常" 
		"maintain_value":"1" //1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；" 
		"maintain_value":"0"  //0代表异常  
		"maintain_abnormal_record": {}  //maintain_abnormal_record类型的json
    }
]
     */
    @Column(name = "maintain_info")
    private String maintainInfo;

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
     * 获取1：一期，2：二期，3：三期保养
     *
     * @return maintain_lib_name - 1：一期，2：二期，3：三期保养
     */
    public String getMaintainLibName() {
        return maintainLibName;
    }

    /**
     * 设置1：一期，2：二期，3：三期保养
     *
     * @param maintainLibName 1：一期，2：二期，3：三期保养
     */
    public void setMaintainLibName(String maintainLibName) {
        this.maintainLibName = maintainLibName;
    }

    /**
     * @return maintain_date_plan
     */
    public Date getMaintainDatePlan() {
        return maintainDatePlan;
    }

    /**
     * @param maintainDatePlan
     */
    public void setMaintainDatePlan(Date maintainDatePlan) {
        this.maintainDatePlan = maintainDatePlan;
    }

    /**
     * 获取实际保养日期
     *
     * @return maintain_date_actual - 实际保养日期
     */
    public Date getMaintainDateActual() {
        return maintainDateActual;
    }

    /**
     * 设置实际保养日期
     *
     * @param maintainDateActual 实际保养日期
     */
    public void setMaintainDateActual(Date maintainDateActual) {
        this.maintainDateActual = maintainDateActual;
    }

    /**
     * 获取保养人员
     *
     * @return maintain_charge_person - 保养人员
     */
    public Integer getMaintainChargePerson() {
        return maintainChargePerson;
    }

    /**
     * 设置保养人员
     *
     * @param maintainChargePerson 保养人员
     */
    public void setMaintainChargePerson(Integer maintainChargePerson) {
        this.maintainChargePerson = maintainChargePerson;
    }

    /**
     * 获取保养建议
     *
     * @return maintain_suggestion - 保养建议
     */
    public String getMaintainSuggestion() {
        return maintainSuggestion;
    }

    /**
     * 设置保养建议
     *
     * @param maintainSuggestion 保养建议
     */
    public void setMaintainSuggestion(String maintainSuggestion) {
        this.maintainSuggestion = maintainSuggestion;
    }

    /**
     * 获取联系人(用户方的联系人）
     *
     * @return contacter - 联系人(用户方的联系人）
     */
    public Integer getContacter() {
        return contacter;
    }

    /**
     * 设置联系人(用户方的联系人）
     *
     * @param contacter 联系人(用户方的联系人）
     */
    public void setContacter(Integer contacter) {
        this.contacter = contacter;
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
     * 获取保养状态 0：待分配，1：已分配(但未接单）2：已接受任务，3：保养完成(客户未确认)，4：客户已确认保养结果
     *
     * @return maintain_status - 保养状态 0：待分配，1：已分配(但未接单）2：已接受任务，3：保养完成(客户未确认)，4：客户已确认保养结果
     */
    public String getMaintainStatus() {
        return maintainStatus;
    }

    /**
     * 设置保养状态 0：待分配，1：已分配(但未接单）2：已接受任务，3：保养完成(客户未确认)，4：客户已确认保养结果
     *
     * @param maintainStatus 保养状态 0：待分配，1：已分配(但未接单）2：已接受任务，3：保养完成(客户未确认)，4：客户已确认保养结果
     */
    public void setMaintainStatus(String maintainStatus) {
        this.maintainStatus = maintainStatus;
    }

    /**
     * 获取用户反馈（意见&建议）
     *
     * @return customer_feedback - 用户反馈（意见&建议）
     */
    public Integer getCustomerFeedback() {
        return customerFeedback;
    }

    /**
     * 设置用户反馈（意见&建议）
     *
     * @param customerFeedback 用户反馈（意见&建议）
     */
    public void setCustomerFeedback(Integer customerFeedback) {
        this.customerFeedback = customerFeedback;
    }

    /**
     * 获取保养json 举例:
[
  {
        "maintain_lib_name":"一期",
        "maintain_type":"注油润滑",
        "maintain_content":"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。" 
		"maintain_value":"1"			//1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"1.检查中间脚盘是否正常" 
		"maintain_value":"1" //1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；" 
		"maintain_value":"0"  //0代表异常  
		"maintain_abnormal_record": {}  //maintain_abnormal_record类型的json
    }
]
     *
     * @return maintain_info - 保养json 举例:
[
  {
        "maintain_lib_name":"一期",
        "maintain_type":"注油润滑",
        "maintain_content":"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。" 
		"maintain_value":"1"			//1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"1.检查中间脚盘是否正常" 
		"maintain_value":"1" //1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；" 
		"maintain_value":"0"  //0代表异常  
		"maintain_abnormal_record": {}  //maintain_abnormal_record类型的json
    }
]
     */
    public String getMaintainInfo() {
        return maintainInfo;
    }

    /**
     * 设置保养json 举例:
[
  {
        "maintain_lib_name":"一期",
        "maintain_type":"注油润滑",
        "maintain_content":"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。" 
		"maintain_value":"1"			//1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"1.检查中间脚盘是否正常" 
		"maintain_value":"1" //1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；" 
		"maintain_value":"0"  //0代表异常  
		"maintain_abnormal_record": {}  //maintain_abnormal_record类型的json
    }
]
     *
     * @param maintainInfo 保养json 举例:
[
  {
        "maintain_lib_name":"一期",
        "maintain_type":"注油润滑",
        "maintain_content":"给针杆、主动轴、一号销、旋梭轴、旋梭加注适量白油；给Y前后、X驱动导轨及滑车轴承加注适量润滑脂。" 
		"maintain_value":"1"			//1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"1.检查中间脚盘是否正常" 
		"maintain_value":"1" //1代表确认OK
    },
    {
        "maintain_lib_name":"一期",
        "maintain_type":"检查修理",
        "maintain_content":"2. 检查主轴皮带、上下轴同步带的张力和位置，带轮螺丝是否有松动；" 
		"maintain_value":"0"  //0代表异常  
		"maintain_abnormal_record": {}  //maintain_abnormal_record类型的json
    }
]
     */
    public void setMaintainInfo(String maintainInfo) {
        this.maintainInfo = maintainInfo;
    }
}