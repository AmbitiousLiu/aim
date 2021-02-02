package com.jleo.jcontrol.boot;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jleo.jcontrol")
public class JControlProperties {

    private String interceptUrl = "/**";

    private String loginUrl = "/login";

    private String securityKey = "JohnnyLeoStudio";

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getSecurityKey() {
        return securityKey;
    }

    public void setSecurityKey(String securityKey) {
        this.securityKey = securityKey;
    }

    public String getInterceptUrl() {
        return interceptUrl;
    }

    public void setInterceptUrl(String interceptUrl) {
        this.interceptUrl = interceptUrl;
    }
}
