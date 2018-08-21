package com.eservice.api.service.common;

public class MaintainContentData {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(int checkValue) {
        this.checkValue = checkValue;
    }

    private int checkValue = 0;
}
