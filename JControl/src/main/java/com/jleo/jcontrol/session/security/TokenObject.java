package com.jleo.jcontrol.session.security;

import java.util.Map;

/**
 * @author jleo
 * @date 2021/1/25
 */
public class TokenObject {

    private Map<String, String> message;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, String> getMessage() {
        return message;
    }

    public void setMessage(Map<String, String> message) {
        this.message = message;
    }
}
