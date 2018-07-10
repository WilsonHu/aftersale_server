package com.eservice.api.model.repair_record;

import java.util.Date;
import javax.persistence.*;

@Table(name = "repair_record")
public class RepairRecord {
    /**
     * ID号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 维修单编号，备用
     */
    @Column(name = "repair_record_num")
    private String repairRecordNum;

    /**
     * 联系人,
     */
    private Integer contacter;

    /**
     * 机器编号
     */
    @Column(name = "machine_nameplate")
    private String machineNameplate;

    /**
     * 用户发起报修信息，一次报修可以有多个维修记录。
     */
    @Column(name = "repair_request_info")
    private Integer repairRequestInfo;

    /**
     * 1：在保修期内，0：保修期已过， 在派单时指定。
     */
    @Column(name = "in_warranty_period")
    private String inWarrantyPeriod;

    /**
     * 维修内容 
     */
    @Column(name = "repair_actual_info")
    private Integer repairActualInfo;

    /**
     * 维修人员
     */
    @Column(name = "repair_charge_person")
    private Integer repairChargePerson;

    /**
     * 维修工时
     */
    @Column(name = "repair_start_time")
    private Date repairStartTime;

    @Column(name = "repair_end_time")
    private Date repairEndTime;

    /**
     * 改善建议
     */
    @Column(name = "customer_feedback")
    private Integer customerFeedback;

    /**
     * 维修状态 0：未派单， 1：已派单（但未接单）, 2： 已接受任务， 3：维修成功(客户未确认)，4：无法维修，维修被转派（不需要客户确认），5.客户已确认（维修成功）。转派后，前面的维修记录要保留，但是客户只需要看到成功的最后那次记录。
     */
    private String status;

    /**
     * 该条记录的创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 该条记录更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 转派信息，如果空表示没有转派。有则记录了来自哪个代理商的转派以及时间。在派单时可以转派给原厂信胜; 有转派则表示是信胜维修。
     */
    @Column(name = "forward_info")
    private Integer forwardInfo;

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
     * 获取维修单编号，备用
     *
     * @return repair_record_num - 维修单编号，备用
     */
    public String getRepairRecordNum() {
        return repairRecordNum;
    }

    /**
     * 设置维修单编号，备用
     *
     * @param repairRecordNum 维修单编号，备用
     */
    public void setRepairRecordNum(String repairRecordNum) {
        this.repairRecordNum = repairRecordNum;
    }

    /**
     * 获取联系人,
     *
     * @return contacter - 联系人,
     */
    public Integer getContacter() {
        return contacter;
    }

    /**
     * 设置联系人,
     *
     * @param contacter 联系人,
     */
    public void setContacter(Integer contacter) {
        this.contacter = contacter;
    }

    /**
     * 获取机器编号
     *
     * @return machine_nameplate - 机器编号
     */
    public String getMachineNameplate() {
        return machineNameplate;
    }

    /**
     * 设置机器编号
     *
     * @param machineNameplate 机器编号
     */
    public void setMachineNameplate(String machineNameplate) {
        this.machineNameplate = machineNameplate;
    }

    /**
     * 获取用户发起报修信息，一次报修可以有多个维修记录。
     *
     * @return repair_request_info - 用户发起报修信息，一次报修可以有多个维修记录。
     */
    public Integer getRepairRequestInfo() {
        return repairRequestInfo;
    }

    /**
     * 设置用户发起报修信息，一次报修可以有多个维修记录。
     *
     * @param repairRequestInfo 用户发起报修信息，一次报修可以有多个维修记录。
     */
    public void setRepairRequestInfo(Integer repairRequestInfo) {
        this.repairRequestInfo = repairRequestInfo;
    }

    /**
     * 获取1：在保修期内，0：保修期已过， 在派单时指定。
     *
     * @return in_warranty_period - 1：在保修期内，0：保修期已过， 在派单时指定。
     */
    public String getInWarrantyPeriod() {
        return inWarrantyPeriod;
    }

    /**
     * 设置1：在保修期内，0：保修期已过， 在派单时指定。
     *
     * @param inWarrantyPeriod 1：在保修期内，0：保修期已过， 在派单时指定。
     */
    public void setInWarrantyPeriod(String inWarrantyPeriod) {
        this.inWarrantyPeriod = inWarrantyPeriod;
    }

    /**
     * 获取维修内容 
     *
     * @return repair_actual_info - 维修内容 
     */
    public Integer getRepairActualInfo() {
        return repairActualInfo;
    }

    /**
     * 设置维修内容 
     *
     * @param repairActualInfo 维修内容 
     */
    public void setRepairActualInfo(Integer repairActualInfo) {
        this.repairActualInfo = repairActualInfo;
    }

    /**
     * 获取维修人员
     *
     * @return repair_charge_person - 维修人员
     */
    public Integer getRepairChargePerson() {
        return repairChargePerson;
    }

    /**
     * 设置维修人员
     *
     * @param repairChargePerson 维修人员
     */
    public void setRepairChargePerson(Integer repairChargePerson) {
        this.repairChargePerson = repairChargePerson;
    }

    /**
     * 获取维修工时
     *
     * @return repair_start_time - 维修工时
     */
    public Date getRepairStartTime() {
        return repairStartTime;
    }

    /**
     * 设置维修工时
     *
     * @param repairStartTime 维修工时
     */
    public void setRepairStartTime(Date repairStartTime) {
        this.repairStartTime = repairStartTime;
    }

    /**
     * @return repair_end_time
     */
    public Date getRepairEndTime() {
        return repairEndTime;
    }

    /**
     * @param repairEndTime
     */
    public void setRepairEndTime(Date repairEndTime) {
        this.repairEndTime = repairEndTime;
    }

    /**
     * 获取改善建议
     *
     * @return customer_feedback - 改善建议
     */
    public Integer getCustomerFeedback() {
        return customerFeedback;
    }

    /**
     * 设置改善建议
     *
     * @param customerFeedback 改善建议
     */
    public void setCustomerFeedback(Integer customerFeedback) {
        this.customerFeedback = customerFeedback;
    }

    /**
     * 获取维修状态 0：未派单， 1：已派单（但未接单）, 2： 已接受任务， 3：维修成功(客户未确认)，4：无法维修，维修被转派（不需要客户确认），5.客户已确认（维修成功）。转派后，前面的维修记录要保留，但是客户只需要看到成功的最后那次记录。
     *
     * @return status - 维修状态 0：未派单， 1：已派单（但未接单）, 2： 已接受任务， 3：维修成功(客户未确认)，4：无法维修，维修被转派（不需要客户确认），5.客户已确认（维修成功）。转派后，前面的维修记录要保留，但是客户只需要看到成功的最后那次记录。
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置维修状态 0：未派单， 1：已派单（但未接单）, 2： 已接受任务， 3：维修成功(客户未确认)，4：无法维修，维修被转派（不需要客户确认），5.客户已确认（维修成功）。转派后，前面的维修记录要保留，但是客户只需要看到成功的最后那次记录。
     *
     * @param status 维修状态 0：未派单， 1：已派单（但未接单）, 2： 已接受任务， 3：维修成功(客户未确认)，4：无法维修，维修被转派（不需要客户确认），5.客户已确认（维修成功）。转派后，前面的维修记录要保留，但是客户只需要看到成功的最后那次记录。
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取该条记录的创建时间
     *
     * @return create_time - 该条记录的创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置该条记录的创建时间
     *
     * @param createTime 该条记录的创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取该条记录更新时间
     *
     * @return update_time - 该条记录更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置该条记录更新时间
     *
     * @param updateTime 该条记录更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取转派信息，如果空表示没有转派。有则记录了来自哪个代理商的转派以及时间。在派单时可以转派给原厂信胜; 有转派则表示是信胜维修。
     *
     * @return forward_info - 转派信息，如果空表示没有转派。有则记录了来自哪个代理商的转派以及时间。在派单时可以转派给原厂信胜; 有转派则表示是信胜维修。
     */
    public Integer getForwardInfo() {
        return forwardInfo;
    }

    /**
     * 设置转派信息，如果空表示没有转派。有则记录了来自哪个代理商的转派以及时间。在派单时可以转派给原厂信胜; 有转派则表示是信胜维修。
     *
     * @param forwardInfo 转派信息，如果空表示没有转派。有则记录了来自哪个代理商的转派以及时间。在派单时可以转派给原厂信胜; 有转派则表示是信胜维修。
     */
    public void setForwardInfo(Integer forwardInfo) {
        this.forwardInfo = forwardInfo;
    }
}