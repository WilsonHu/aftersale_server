package com.eservice.api.model.user;

public class UserInfo extends User {
    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    private String agentName;
}
