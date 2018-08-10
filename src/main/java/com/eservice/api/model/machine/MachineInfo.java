package com.eservice.api.model.machine;

import java.util.Date;

public class MachineInfo extends Machine {

    /**
     * customer索引转换实际名字,来自 machine.customer-->user.name
     */
    private String customerName;

    /**
     * 索引转换实际名字,来自 machine.customer-->user-->agent.name
     */
    private String agent;

    /**
     * 索引转换实际名字,来自 machine.nameplate-->install_record.installChargePerson-->user.name
     */
//    private String installChargePerson;

     /**
     * 实际完成安装时间
     */
    private Date installActualTime;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
     }

//    public String getInstallChargePerson(){
//        return this.installChargePerson;
//     }
//
//    public void setInstallChargePerson(String installChargePerson){
//         this.installChargePerson = installChargePerson;
//     }

    public Date getInstallActualTime() {
        return installActualTime;
    }

    public void setInstallActualTime(Date installActualTime){
         this.installActualTime = installActualTime;
    }
 }