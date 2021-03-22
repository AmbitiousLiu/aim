package com.jleo.jcontrol.session.security;

import org.springframework.stereotype.Component;

/**
 * @author jleo
 * @date 2021/1/20
 */
public interface Token {

    String createToken(String jsonString);

    String ParseToken(String token);
}
