package com.eservice.api.service.common;
import java.util.List;

public class MaintainData {
    public int getMaintainType() {
        return maintainType;
    }

    public void setMaintainType(int maintainType) {
        this.maintainType = maintainType;
    }

    public List<MaintainContentData> getContentList() {
        return contentList;
    }

    public void setContentList(List<MaintainContentData> contentList) {
        this.contentList = contentList;
    }

    private int maintainType;
    private List<MaintainContentData> contentList;
}
