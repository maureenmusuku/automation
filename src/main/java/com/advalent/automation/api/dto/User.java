package com.advalent.automation.api.dto;

import com.advalent.automation.api.annotations.documentation.Immutable;

import java.util.*;

/**
 * @author sshrestha
 */
@Immutable
public class User {
    private String automationId;
    private String userId;
    private String password;
    private String email;
    private String emailPassword;
    private List<String> roles;


    public User(String automationId, String userId, String password) {
        this.automationId = automationId;
        this.userId = userId;
        this.password = password;
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
        this.automationId = null;
    }

    public User() {
        this.userId = null;
        this.password = null;
        this.automationId = null;
    }


    public String getAutomationId() {
        return automationId;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setAutomationId(String automationId) {
        this.automationId = automationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User {" +
                "automationId='" + automationId + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", emailPassword='" + emailPassword + '\'' +
                ", roles=" + roles +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmailPassword(String password) {
        this.emailPassword = password;
    }
}
