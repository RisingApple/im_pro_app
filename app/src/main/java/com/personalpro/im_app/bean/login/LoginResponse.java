package com.personalpro.im_app.bean.login;

public class LoginResponse {

    private String sessionId;

    private String username;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "sessionId='" + sessionId + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
