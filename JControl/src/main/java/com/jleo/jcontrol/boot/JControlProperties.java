package com.jleo.jcontrol.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "jleo.jcontrol")
public class JControlProperties {

    private String interceptUrl;

    private String loginPageUrl;

    private String successUrl;

    private String errorUrl;

    private String adminPassword;

    public String getInterceptUrl() {
        return interceptUrl;
    }

    public void setInterceptUrl(String interceptUrl) {
        this.interceptUrl = interceptUrl;
    }

    public String getPageUrl() {
        return loginPageUrl;
    }

    public void setPageUrl(String loginPageUrl) {
        this.loginPageUrl = loginPageUrl;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getErrorUrl() {
        return errorUrl;
    }

    public void setErrorUrl(String errorUrl) {
        this.errorUrl = errorUrl;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
