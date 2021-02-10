package com.eservice.api.service.common;

import java.util.Date;

public class WxMessageTemplateJsonData {

    /**
     * common 接受消息的客户名字
     */
    private String customerName;//
    private String customerAddress;
    private String customerPhone;

    /**
     * 1 绑定时发的消息
     */
    private String messageOfMachineBind;//
    private String machineNameplate;//
    private String machineType;//
    private Date factoryDate;

    /**
     * 2 安装派单时发的消息
     */
    private String installChargePerson;
    private String installTaskMessage;
    private Date installPlanDate;

    /**
     * 3 保养派单时发的消息
     */
    private String maintainChargePerson;
    private String maintainTaskMessage;
    private Date maintainPlanDate;

    /**
     * 4 维修派单时发的消息
     */
    private String repairChargePerson;
    private String repairTaskMessage;
    private Date repairPlanDate;

    /**
     * 5  安装任务接单时发的消息
     */
    private String installChargePersonPhone;
    private String installTaskAcceptedMessage;

    /**
     * 6  保养任务接单时发的消息
     */
    private String maintainChargePersonPhone;
    private String maintainTaskAcceptedMessage;

    /**
     * 7  维修任务接单时发的消息
     */
    private String repairChargePersonPhone;
    private String repairTaskAcceptedMessage;

    /**
     * 8 安装任务完成待评价时发的消息 WX_TEMPLATE_NO8_INSTALL_DONE_TO_CUSTOMER
     */
    private String installTaskName;
    private String installTaskDoneMessage;
    private Date installActualTime;

    /**
     * 9 保养任务完成待评价时发的消息
     */
    private String maintainTaskName;
    private String maintainTaskDoneMessage;
    private Date maintainActualTime;

    /**
     * 10 维修任务完成待评价时发的消息
     */
    private String repairTaskName;
    private String repairTaskDoneMessage;
    private Date repairActualTime;

    /**
     * 11 收到报修时 通知管理员发的消息
     */
    private String repairRequestGot;
    private String repairRequestBornMessage;

    /**
     * 12 轮到订单/联系单签核了 通知对应人员发的消息
     */
    private String signType; //联系单 or 订单
    private String signPerson; //轮到谁签核
    private String machineOrderNumber; //订单号
    private String lxdNumber; //联系单号
    private String msgInfo; //比如 轮到签核、签核已完成、签核被拒绝

    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getSignPerson() {
        return signPerson;
    }

    public void setSignPerson(String signPerson) {
        this.signPerson = signPerson;
    }

    public String getMachineOrderNumber() {
        return machineOrderNumber;
    }

    public void setMachineOrderNumber(String machineOrderNumber) {
        this.machineOrderNumber = machineOrderNumber;
    }

    public String getLxdNumber() {
        return lxdNumber;
    }

    public void setLxdNumber(String lxdNumber) {
        this.lxdNumber = lxdNumber;
    }

    public String getRepairRequestGot() {
        return repairRequestGot;
    }

    public void setRepairRequestGot(String repairRequestGot) {
        this.repairRequestGot = repairRequestGot;
    }

    public String getRepairRequestBornMessage() {
        return repairRequestBornMessage;
    }

    public void setRepairRequestBornMessage(String repairRequestBornMessage) {
        this.repairRequestBornMessage = repairRequestBornMessage;
    }

    public Date getRepairActualTime() {
        return repairActualTime;
    }

    public void setRepairActualTime(Date repairActualTime) {
        this.repairActualTime = repairActualTime;
    }

    public String getRepairTaskDoneMessage() {
        return repairTaskDoneMessage;
    }

    public void setRepairTaskDoneMessage(String repairTaskDoneMessage) {
        this.repairTaskDoneMessage = repairTaskDoneMessage;
    }

    public String getRepairTaskName() {
        return repairTaskName;
    }

    public void setRepairTaskName(String repairTaskName) {
        this.repairTaskName = repairTaskName;
    }

    public Date getMaintainActualTime() {
        return maintainActualTime;
    }

    public void setMaintainActualTime(Date maintainActualTime) {
        this.maintainActualTime = maintainActualTime;
    }

    public String getMaintainTaskDoneMessage() {
        return maintainTaskDoneMessage;
    }

    public void setMaintainTaskDoneMessage(String maintainTaskDoneMessage) {
        this.maintainTaskDoneMessage = maintainTaskDoneMessage;
    }

    public String getMaintainTaskName() {
        return maintainTaskName;
    }

    public void setMaintainTaskName(String maintainTaskName) {
        this.maintainTaskName = maintainTaskName;
    }

    public String getInstallTaskName() {
        return installTaskName;
    }

    public void setInstallTaskName(String installTaskName) {
        this.installTaskName = installTaskName;
    }

    public Date getInstallActualTime() {
        return installActualTime;
    }

    public void setInstallActualTime(Date installActualTime) {
        this.installActualTime = installActualTime;
    }

    public void setInstallTaskDoneMessage(String installTaskDoneMessage) {
        this.installTaskDoneMessage = installTaskDoneMessage;
    }

    public String getInstallTaskDoneMessage() {
        return installTaskDoneMessage;
    }

    public void setRepairChargePersonPhone(String repairChargePersonPhone) {
        this.repairChargePersonPhone = repairChargePersonPhone;
    }

    public String getRepairChargePersonPhone() {
        return repairChargePersonPhone;
    }

    public String getRepairTaskAcceptedMessage() {
        return repairTaskAcceptedMessage;
    }

    public void setRepairTaskAcceptedMessage(String repairTaskAcceptedMessage) {
        this.repairTaskAcceptedMessage = repairTaskAcceptedMessage;
    }

    public void setMaintainChargePersonPhone(String maintainChargePersonPhone) {
        this.maintainChargePersonPhone = maintainChargePersonPhone;
    }

    public String getMaintainChargePersonPhone() {
        return maintainChargePersonPhone;
    }

    public String getMaintainTaskAcceptedMessage() {
        return maintainTaskAcceptedMessage;
    }

    public void setMaintainTaskAcceptedMessage(String maintainTaskAcceptedMessage) {
        this.maintainTaskAcceptedMessage = maintainTaskAcceptedMessage;
    }

    public String getInstallChargePersonPhone() {
        return installChargePersonPhone;
    }

    public void setInstallChargePersonPhone(String installChargePersonPhone) {
        this.installChargePersonPhone = installChargePersonPhone;
    }

    public String getInstallTaskAcceptedMessage() {
        return installTaskAcceptedMessage;
    }

    public void setInstallTaskAcceptedMessage(String installTaskAcceptedMessage) {
        this.installTaskAcceptedMessage = installTaskAcceptedMessage;
    }

    public void setRepairPlanDate(Date repairPlanDate) {
        this.repairPlanDate = repairPlanDate;
    }

    public Date getRepairPlanDate() {
        return repairPlanDate;
    }

    public String getRepairTaskMessage() {
        return repairTaskMessage;
    }

    public void setRepairTaskMessage(String repairTaskMessage) {
        this.repairTaskMessage = repairTaskMessage;
    }

    public String getRepairChargePerson() {
        return repairChargePerson;
    }

    public void setRepairChargePerson(String repairChargePerson) {
        this.repairChargePerson = repairChargePerson;
    }

    public String getMaintainChargePerson() {
        return maintainChargePerson;
    }

    public void setMaintainChargePerson(String maintainChargePerson) {
        this.maintainChargePerson = maintainChargePerson;
    }

    public String getMaintainTaskMessage() {
        return maintainTaskMessage;
    }

    public void setMaintainTaskMessage(String maintainTaskMessage) {
        this.maintainTaskMessage = maintainTaskMessage;
    }

    public Date getMaintainPlanDate() {
        return maintainPlanDate;
    }

    public void setMaintainPlanDate(Date maintainPlanDate) {
        this.maintainPlanDate = maintainPlanDate;
    }

    public Date getInstallPlanDate() {
        return installPlanDate;
    }

    public void setInstallPlanDate(Date installPlanDate) {
        this.installPlanDate = installPlanDate;
    }

    public String getInstallTaskMessage() {
        return installTaskMessage;
    }

    public void setInstallTaskMessage(String installTaskMessage) {
        this.installTaskMessage = installTaskMessage;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }


    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getFactoryDate() {
        return factoryDate;
    }

    public void setFactoryDate(Date factoryDate) {
        this.factoryDate = factoryDate;
    }

    public String getMachineNameplate() {
        return machineNameplate;
    }

    public void setMachineNameplate(String machineNameplate) {
        this.machineNameplate = machineNameplate;
    }

    public String getMessageOfMachineBind() {
        return messageOfMachineBind;
    }

    public void setMessageOfMachineBind(String messageOfMachineBind) {
        this.messageOfMachineBind = messageOfMachineBind;
    }

    public void setInstallChargePerson(String installChargePerson) {
        this.installChargePerson = installChargePerson;
    }

    public String getInstallChargePerson() {
        return installChargePerson;
    }

    //
//    private String templateId;
//        // 模板消息详情链接
//        private String url;
//        // 消息顶部的颜色
//        private String topColor;
//        // 参数列表
//        private List<TemplateParam> templateParamList;
//        //省略getter、setter方法
//
//        //按微信接口要求格式化模板
//        public String toJSON() {
//            StringBuffer buffer = new StringBuffer();
//            buffer.append("{");
//            buffer.append(String.format("\"touser\":\"%s\"", this.toUser)).append(",");
//            buffer.append(String.format("\"template_id\":\"%s\"", this.templateId)).append(",");
//            buffer.append(String.format("\"url\":\"%s\"", this.url)).append(",");
//            buffer.append(String.format("\"topcolor\":\"%s\"", this.topColor)).append(",");
//            buffer.append("\"data\":{");
//            TemplateParam param = null;
//            for (int i = 0; i < this.templateParamList.size(); i++) {
//                param = templateParamList.get(i);
//                // 判断是否追加逗号
//                if (i < this.templateParamList.size() - 1){
//
//                    buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"},", param.getName(), param.getValue(), param.getColor()));
//                }else{
//                    buffer.append(String.format("\"%s\": {\"value\":\"%s\",\"color\":\"%s\"}", param.getName(), param.getValue(), param.getColor()));
//                }
//
//            }
//            buffer.append("}");
//            buffer.append("}");
//            return buffer.toString();
//        }
//
//    private class TemplateParam {
//        // 参数名称
//        private String name;
//        // 参数值
//        private String value;
//        // 颜色
//        private String color;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getValue() {
//            return value;
//        }
//
//        public void setValue(String value) {
//            this.value = value;
//        }
//
//        public String getColor() {
//            return color;
//        }
//
//        public void setColor(String color) {
//            this.color = color;
//        }
//
//        public TemplateParam(String name,String value,String color){
//            this.name=name;
//            this.value=value;
//            this.color=color;
//        }
//    }
    }

