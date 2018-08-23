package com.eservice.api.model.user;

public class StaffInfo extends UserInfo {
    private int installTaskCount;
    private int maintainTaskCount;
    private int repairTaskCount;
    private int totalTaskCount;

    public int getInstallTaskCount() {
        return installTaskCount;
    }

    public void setInstallTaskCount(int installTaskCount) {
        this.installTaskCount = installTaskCount;
    }

    public int getMaintainTaskCount() {
        return maintainTaskCount;
    }

    public void setMaintainTaskCount(int maintainTaskCount) {
        this.maintainTaskCount = maintainTaskCount;
    }

    public int getRepairTaskCount() {
        return repairTaskCount;
    }

    public void setRepairTaskCount(int repairTaskCount) {
        this.repairTaskCount = repairTaskCount;
    }

    public int getTotalTaskCount() {
        return totalTaskCount;
    }

    public void setTotalTaskCount(int totalTaskCount) {
        this.totalTaskCount = totalTaskCount;
    }
}
