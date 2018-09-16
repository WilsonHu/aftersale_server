package com.eservice.api.model.repair_actual_info;

import com.eservice.api.model.parts_info.PartsInfo;

import javax.persistence.*;
import java.util.List;

/**
 * repair/actual/info/add()时，实际维修信息和配件信息作为一个requestBody里的对象整体一起上传，配件信息可以有1个或多个。
 */
public class RepairActualInfoWithPartsInfo// extends RepairActualInfo {
 {

    private List<PartsInfo> partsInfoList;

    public List<PartsInfo> getPartsInfoList() {
        return partsInfoList;
    }

    public void setPartsInfoList(List<PartsInfo> partsInfoList) {
        this.partsInfoList = partsInfoList;
    }

    private RepairActualInfo repairActualInfo;

     public RepairActualInfo getRepairActualInfo() {
         return repairActualInfo;
     }

     public void setRepairActualInfo(RepairActualInfo repairActualInfo) {
         this.repairActualInfo = repairActualInfo;
     }
 }