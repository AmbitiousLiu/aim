package com.jleo.jcontrol.session.security;

import com.jleo.jcontrol.boot.JControlProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jleo
 * @date 2021/1/20
 */
@Component
public class DefaultToken implements Token {

    @Autowired
    private JControlProperties jControlProperties;

    @Override
    public String createToken(String jsonString) {
        String key = jControlProperties.getSecurityKey();
        return AESUtil.encrypt(jsonString, key);
    }

    @Override
    public String ParseToken(String token) {
        return AESUtil.decrypt(token, jControlProperties.getSecurityKey());
    }
}
