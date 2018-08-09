package com.eservice.api.model.parts_info;

import javax.persistence.*;
import java.util.Date;

public class PartsAllInfo extends PartsInfo {

    private String sendbackConfirmedPersonName;

    public void setSendbackConfirmedPersonName(String sendbackConfirmedPersonName) {
        this.sendbackConfirmedPersonName = sendbackConfirmedPersonName;
    }

    public String getSendbackConfirmedPersonName() {
        return sendbackConfirmedPersonName;
    }
}