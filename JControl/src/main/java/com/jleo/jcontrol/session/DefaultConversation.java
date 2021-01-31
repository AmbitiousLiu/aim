package com.jleo.jcontrol.session;

import com.google.gson.Gson;
import com.jleo.jcontrol.boot.JControlConstant;
import com.jleo.jcontrol.session.cookie.JCookie;
import com.jleo.jcontrol.session.security.Token;
import com.jleo.jcontrol.session.security.TokenObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jleo
 * @date 2021/1/25
 */
@Component
public class DefaultConversation implements Conversation {

    @Autowired
    private Token token;

    @Autowired
    private Gson gson;

    @Override
    public boolean isLogin(HttpServletRequest request) {
        // get cookie
        Cookie cookie = JCookie.getCookieByName(request.getCookies(), JControlConstant.COOKIE_TOKEN_NAME);
        if (cookie == null) {
            return false;
        }
        TokenObject tokenObject = gson.fromJson(token.ParseToken(cookie.getValue()), TokenObject.class);
        // check parse

        // check time
        if (System.currentTimeMillis() - tokenObject.getTime() > JControlConstant.COOKIE_EXPIRATION) {
            return false;
        }
        // others
        return false;
    }

    @Override
    public boolean signIn(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }

    @Override
    public boolean signOut(HttpServletRequest request, HttpServletResponse response) {
        return false;
    }
}
