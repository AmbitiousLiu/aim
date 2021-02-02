package com.jleo.jcontrol.session.cookie;

import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;

/**
 * @author jleo
 * @date 2021/1/25
 */
public interface JCookie {

    static Cookie getCookieByName(Cookie[] cookies, String name) {
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName() != null && cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }
}
