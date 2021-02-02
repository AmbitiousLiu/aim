package com.jleo.jcontrol.session;

import com.google.gson.Gson;
import com.jleo.jcontrol.bean.Exception.JControlException;
import com.jleo.jcontrol.boot.JControlConstant;
import com.jleo.jcontrol.boot.JControlProperties;
import com.jleo.jcontrol.session.cookie.JCookie;
import com.jleo.jcontrol.session.security.Token;
import com.jleo.jcontrol.session.security.TokenObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jleo
 * @date 2021/1/25
 */
@Component("conversation")
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
        // check parse
        TokenObject tokenObject = null;
        try {
            tokenObject = gson.fromJson(token.ParseToken(cookie.getValue()), TokenObject.class);
            if (tokenObject == null) {
                throw new JControlException("token解析失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        // check time
        if (System.currentTimeMillis() - tokenObject.getTime() > JControlConstant.COOKIE_EXPIRATION) {
            return false;
        }
        // others

        return true;
    }

    @Override
    public boolean signIn(HttpServletRequest request, HttpServletResponse response, String userId, Map<String, String> userInfo) {
        try {
            TokenObject tokenObject = new TokenObject();
            tokenObject.setTime(System.currentTimeMillis());
            tokenObject.setUserId(userId);
            tokenObject.setMessage(userInfo);
            Cookie cookie = new Cookie(JControlConstant.COOKIE_TOKEN_NAME, token.createToken(gson.toJson(tokenObject)));
            cookie.setPath("/");
            //cookie.setDomain(request.getServerName() + ":" + request.getServerPort());
            cookie.setMaxAge(JControlConstant.COOKIE_EXPIRATION.intValue());
            response.addCookie(cookie);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean signOut(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = JCookie.getCookieByName(request.getCookies(), JControlConstant.COOKIE_TOKEN_NAME);
        if (cookie == null) {
            return true;
        }
        cookie.setMaxAge(0);
        return true;
    }

    public <T> T getObjectFromToken(HttpServletRequest request, String name, Class<T> clazz) {
        Cookie cookie = JCookie.getCookieByName(request.getCookies(), JControlConstant.COOKIE_TOKEN_NAME);
        if (cookie == null) {
            return null;
        }
        TokenObject tokenObject = null;
        try {
            tokenObject = gson.fromJson(token.ParseToken(cookie.getValue()), TokenObject.class);
            if (tokenObject == null) {
                throw new JControlException("token解析失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return gson.fromJson(tokenObject.getMessage().get(name), clazz);
    }

    public String getStringFromToken(HttpServletRequest request, String name) {
        Cookie cookie = JCookie.getCookieByName(request.getCookies(), JControlConstant.COOKIE_TOKEN_NAME);
        if (cookie == null) {
            return null;
        }
        TokenObject tokenObject = null;
        try {
            tokenObject = gson.fromJson(token.ParseToken(cookie.getValue()), TokenObject.class);
            if (tokenObject == null) {
                throw new JControlException("token解析失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return tokenObject.getMessage().get(name);
    }

    public String getUserId(HttpServletRequest request) {
        Cookie cookie = JCookie.getCookieByName(request.getCookies(), JControlConstant.COOKIE_TOKEN_NAME);
        if (cookie == null) {
            return null;
        }
        TokenObject tokenObject = null;
        try {
            tokenObject = gson.fromJson(token.ParseToken(cookie.getValue()), TokenObject.class);
            if (tokenObject == null) {
                throw new JControlException("token解析失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return tokenObject.getUserId();
    }
}
