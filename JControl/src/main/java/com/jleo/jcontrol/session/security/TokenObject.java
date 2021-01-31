package com.jleo.jcontrol.session.security;

import java.util.Map;

/**
 * @author jleo
 * @date 2021/1/25
 */
public class TokenObject {

    private Map<String, Object> message;

    private Long time;

    public Map<String, Object> getMessage() {
        return message;
    }

    public void setMessage(Map<String, Object> message) {
        this.message = message;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
